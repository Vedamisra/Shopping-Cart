package com.jsp.shopping_cart.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dao.MerchantDao;
import com.jsp.shopping_cart.dao.ProductDao;
import com.jsp.shopping_cart.dto.Merchant;
import com.jsp.shopping_cart.dto.Product;
import com.mysql.cj.Session;

@Controller
public class ProductController {
	
	@Autowired
	ProductDao dao;
	
	@Autowired
	MerchantDao mdao;
	
	@RequestMapping("/addproduct")
	public ModelAndView addproduct() {
		Product p = new Product();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productobj",p);
		mav.setViewName("productform");
		return mav;
	}
	
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p,HttpSession session) {
		Merchant merchant = (Merchant) session.getAttribute("merchantinfo");
		List<Product> products = merchant.getProducts();
		
		if(products.size()>0) {
			products.add(p);
			merchant.setProducts(products);
		} else {
			List<Product> productslist = new ArrayList<Product>();
			productslist.add(p);
			
			merchant.setProducts(productslist);
			
		}
		
		
		dao.saveProduct(p);
		mdao.updateMerchant(merchant);
		
		ModelAndView mav =  new ModelAndView();
		mav.addObject("msg", "data saved Successfully");
		mav.setViewName("merchantoptions");
		return mav;
	}
	
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id") int id,HttpSession session) {
		Merchant merchant = (Merchant) session.getAttribute("merchantinfo");
		Merchant m = mdao.deleteProductFromMerchant(merchant.getId(), id);
		
		
		mdao.updateMerchant(m);
		dao.deleteProductById(id);
		
		session.setAttribute("merchantinfo",m);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewallproducts");
		
		return mav;
		
		
	}
	
	@RequestMapping("/displayproducts")
	public ModelAndView displayProducts() {
		List<Product> products = dao.fetchAllProducts();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
	
	
	
	@RequestMapping("/displayproductsbybrand")
	public ModelAndView displayProductsByBrand(ServletRequest req) {
		String brand = req.getParameter("brand");
		List<Product> products = dao.findProductByBrand(brand);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewallproductstocustomer");
		
		return mav;
	}
}
