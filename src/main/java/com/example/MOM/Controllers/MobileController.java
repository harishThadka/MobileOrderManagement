package com.example.MOM.Controllers;


import java.util.Optional;
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
    

    @RequestMapping("viewMobiles")
    public ModelAndView viewMobiles() {
        mv.setViewName("viewMobiles");
        Iterable<Mobile> mobileList = mobileRepo.findAll();
        System.out.println("********************************************");
        System.out.println(mobileList);
        System.out.println("********************************************");
        mv.addObject("mobiles", mobileList);
        return mv;
    }
	
    
    @RequestMapping("orders")
    public ModelAndView orders() {
        mv.setViewName("orders");
        Iterable<OrderInfo> orderList = orderInfoRepo.findAll();
        System.out.println("********************************************");
        System.out.println(orderList);
        System.out.println("********************************************");
        mv.addObject("orders", orderList);
        return mv;
    }
	
    
    @RequestMapping("cancelOrder")
    public ModelAndView cancelOrder(int orderId) {
        Optional<OrderInfo> orderFound = orderInfoRepo.findById(orderId);
        if (orderFound.isPresent()) {
            orderInfoRepo.deleteById(orderId);
        }
        return orders();
    }
 
	
}
