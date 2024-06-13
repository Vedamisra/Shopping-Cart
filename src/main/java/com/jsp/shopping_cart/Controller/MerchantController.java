package com.jsp.shopping_cart.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dao.MerchantDao;
import com.jsp.shopping_cart.dto.Merchant;

@Controller
public class MerchantController {
	
	@Autowired
	MerchantDao dao;
	
	@RequestMapping("/addmerchant")
	public ModelAndView addMerchant() {
		Merchant m = new Merchant();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("merchantobj", m);
		mav.setViewName("merchantform");
		
		return mav;
	}
	
	@RequestMapping("/savemerchant")
	public ModelAndView saveMerchant(@ModelAttribute("merchantobj") Merchant m) {
		dao.saveMerchant(m);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message","ADD DATA SUCCESSFULLY...");
		mav.setViewName("merchantmenu");
		
		return mav;	
	}
	
	@RequestMapping("/loginvalidation")
	public ModelAndView login(ServletRequest req, HttpSession session) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Merchant m = dao.login(email, password);
		ModelAndView mav = new ModelAndView();
		
		if(m!=null) {
			mav.addObject("msg","SUCCESSFULLY LOGED IN...");
			mav.setViewName("merchantoptions");
			session.setAttribute("merchantinfo", m);
			return mav;
		}
		else {
			mav.addObject("msg","INVALID CREDENTIALS...");
			mav.setViewName("merchantloginform");
			return mav;
		}
	}

}

