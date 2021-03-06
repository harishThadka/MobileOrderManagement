package com.example.MOM.Controllers;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		String alert = "no";
		mv.addObject("alert", alert);
		System.out.println("alert in search"+alert);
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

        return viewMobiles();
    }
    
    @RequestMapping("addNewOrder")
    public ModelAndView addNewMobile(Mobile mobile, Customer customer, OrderInfo orderInfo) {
    	
    	int randCustomer = rand.nextInt(10000)+10001;
    	customer.setCustomerId(randCustomer);
        customerRepo.save(customer); 
        int randOrder = rand.nextInt(20000)+10001;
        

        
        String orderDate = "";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        orderDate = date.format(formatter);
        
        String orderStatus = "Processing";
        
        orderInfo.setOrderId(randOrder);
        orderInfo.setOrderDate(orderDate);
        orderInfo.setOrderStatus(orderStatus);
        orderInfo.setCustomer(customer);
        orderInfo.setMobile(mobile);
        orderInfoRepo.save(orderInfo);
        
        return orderDetails(randOrder);
        
    }
    
    
    @RequestMapping("orderDetails")
    public ModelAndView orderDetails(int orderId) {
        OrderInfo orderFound = orderInfoRepo.findById(orderId).orElse(new OrderInfo());
        
        System.out.println("********************************************");
        System.out.println(orderFound);
        System.out.println("********************************************");
        if(orderFound.getOrderId() == 0 && orderFound.getOrderDate()==null) {
    		String alert = "yes";
    		mv.addObject("alert", alert);
    		System.out.println("alert in orderDetails"+alert);
        	mv.setViewName("SearchOrder"); 
        }
        else {
            mv.addObject(orderFound);
            mv.setViewName("orderDetails");        	
        }

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
    
    @RequestMapping("editMobile")
    public ModelAndView editMobile(int mobileId) {
    	
        Mobile mobile = mobileRepo.findById(mobileId).orElse(new Mobile());
        
        System.out.println("********************************************");
        System.out.println(mobile);
        System.out.println("********************************************");
        
        mv.addObject(mobile);
        mv.setViewName("editMobile");
        return mv;
    }
    
    @RequestMapping("updateMobile")
    public ModelAndView updateMobile(Mobile mobile) {

    	System.out.println("********************************************");
        System.out.println(mobile);
        System.out.println("********************************************");
        
        mobileRepo.save(mobile);
        return viewMobiles();
    }
 

	
}
