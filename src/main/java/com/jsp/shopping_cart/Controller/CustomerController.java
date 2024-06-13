package com.jsp.shopping_cart.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dao.CustomerDao;
import com.jsp.shopping_cart.dto.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerDao dao;
	
	@RequestMapping("/addcustomer")
	public ModelAndView addCustomer() {
		Customer c = new Customer();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj", c);
		mav.setViewName("customerform");
		
		return mav;
	}
	
	@RequestMapping("/saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute("customerobj") Customer m) {
		dao.saveCustomer(m);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message","ADD DATA SUCCESSFULLY...");
		mav.setViewName("customermenu");
		
		return mav;	
	}
	
	@RequestMapping("/customerloginvalidation")
	public ModelAndView login(ServletRequest req, HttpSession session) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Customer m = dao.login(email, password);
		ModelAndView mav = new ModelAndView();
		
		if(m!=null) {
			mav.addObject("msg","SUCCESSFULLY LOGED IN...");
			mav.setViewName("customeroptions");
			session.setAttribute("customerinfo", m);
			return mav;
		}
		else {
			mav.addObject("msg","INVALID CREDENTIALS...");
			mav.setViewName("customerloginform");
			return mav;
		}
	}

}


