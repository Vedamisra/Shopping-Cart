package com.jsp.shopping_cart.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dao.CartDao;
import com.jsp.shopping_cart.dao.CustomerDao;
import com.jsp.shopping_cart.dao.OrdersDao;
import com.jsp.shopping_cart.dao.ProductDao;
import com.jsp.shopping_cart.dto.Cart;
import com.jsp.shopping_cart.dto.Customer;
import com.jsp.shopping_cart.dto.Item;
import com.jsp.shopping_cart.dto.Orders;
import com.jsp.shopping_cart.dto.Product;

@Controller
public class OrdersController {

	
	@Autowired
	OrdersDao dao;
	
	@Autowired
	CustomerDao cdao;
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	CartDao cadao;
	
	
	@RequestMapping("/addorder")
	public ModelAndView addOrder() {
		Orders o = new Orders();
		ModelAndView mav  = new ModelAndView();
		mav.addObject("ordersobj",o);
		mav.setViewName("ordersform");
		
		return mav;
	}
	
	@RequestMapping("/saveorder")
	public ModelAndView saveorder(@ModelAttribute("ordersobj") Orders o,HttpSession session) {
		Customer c = (Customer) session.getAttribute("customerinfo");
		Customer customer = cdao.findCustomerById(c.getId());
		Cart cart = customer.getCart();
		
		List<Item> items = cart.getItems();
		
//		o.setTotalprice(cart.getTotalprice());

		List<Item> itemsList = new ArrayList<Item>();
		
		List<Item> itemswitchGreaterQuantity = new ArrayList<Item>();
		
		for(Item i : items) {
			 Product p = pdao.findProductById(i.getP_id());
			 if(i.getQuantity() < p.getStock()) {
				 itemsList.add(i);
				 p.setStock(p.getStock() - i.getQuantity());
				 
				 pdao.updateProduct(p);				 
			 }
			 else {
				 itemswitchGreaterQuantity.add(i);
			 }
		}
		
		
//		Cart updatedCart = cadao.removeAllItemsFromCart(cart.getId());
//		cart.setTotalprice(itemswitchGreaterQuantity.stream().map(i->i.getPrice()).mapToDouble(Double :: parseDouble));
		
		o.setItems(itemsList);
		double totalpriceoforder = 0;
		for(Item i : itemsList) {
			totalpriceoforder = totalpriceoforder + i.getPrice();
		}
		
		o.setTotalprice(totalpriceoforder);
		
		cart.setItems(itemswitchGreaterQuantity);
		
		double totalprice = 0;
		for(Item i : itemswitchGreaterQuantity) {
			totalprice = totalprice + i.getPrice();
		}
		
		cart.setTotalprice(totalprice);
		
		List<Orders> orders = customer.getOrders();
		if(orders.size() > 0) {
			orders.add(o);
			customer.setOrders(orders);
		}
		else {
			List<Orders> orders1 = new ArrayList<>();
			orders1.add(o);
			customer.setOrders(orders1);
		}
		
		customer.setCart(cart);
		cadao.updateCart(cart);
		dao.saveOrder(o);
		cdao.updateCustomer(customer);
		
		ModelAndView mav =  new ModelAndView();
		mav.addObject("msg", "ORDER PLACED SUCCESSFULLY...");
		mav.addObject("orderdetails", o);
		mav.setViewName("customerbill");
		
		return mav;
	}
	
	
}
