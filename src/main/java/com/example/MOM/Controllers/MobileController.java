package com.example.MOM.Controllers;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.MOM.Models.*;
import com.example.MOM.Repository.*;

@Controller
public class MobileController {


	ModelAndView mv = new ModelAndView();
	
    @Autowired
    MobileRepository mobileRepo;
    
    @Autowired
    CustomerRepository customerRepo;
    
    @Autowired
    OrderInfoRepository orderInfoRepo;
    
	Random rand = new Random();
    
	
	@RequestMapping("")
	public ModelAndView home() {
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("searchOrder")
	public ModelAndView searchOrder() {
		mv.setViewName("searchOrder");
		return mv;
	}
	
	@RequestMapping("buyMobile")
    public ModelAndView buyMobile() {
        mv.setViewName("buyMobile");
        mv.addObject("mobiles",mobileRepo.findAll());
        return mv;
    }
	
	@RequestMapping("addMobile")
    public ModelAndView addMobile() {
        mv.setViewName("addMobile");
        mv.addObject("mobiles",mobileRepo.findAll());
        return mv;
    }
	

    @RequestMapping("addNewMobile")
    public ModelAndView addNewMobile(Mobile mobile) {
    	int randMobile = rand.nextInt(10000)+1;
    	mobile.setMobileId(randMobile);
        mobileRepo.save(mobile);
        mv.setViewName("home");
        return mv;
    }
    
    @RequestMapping("addNewOrder")
    public ModelAndView addNewMobile(Mobile mobile, Customer customer, OrderInfo orderInfo) {
    	int randCustomer = rand.nextInt(10000)+10001;
    	customer.setCustomerId(randCustomer);
        customerRepo.save(customer); 
        int randOrder = rand.nextInt(20000)+10001;
        orderInfo.setOrderId(randOrder);
        orderInfo.setCustomer(customer);
        orderInfo.setMobile(mobile);
        orderInfoRepo.save(orderInfo);
        mv.setViewName("searchOrder");
        return mv;
    }
    
    
    @RequestMapping("orderDetails")
    public ModelAndView search(int orderId) {
        OrderInfo orderFound = orderInfoRepo.findById(orderId).orElse(new OrderInfo());
        
        System.out.println("********************************************");
        System.out.println(orderFound);
        System.out.println("********************************************");
        
        mv.addObject(orderFound);
        mv.setViewName("orderDetails");
        return mv;
    }
	
	

	
}
