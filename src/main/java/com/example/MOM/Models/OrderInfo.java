package com.example.MOM.Models;


import javax.persistence.*;

@Entity
public class OrderInfo {

	@Id
	private int orderId;
	private String orderDate;
	private String orderStaus;
	
	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Mobile mobile;
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStaus() {
		return orderStaus;
	}

	public void setOrderStaus(String orderStaus) {
		this.orderStaus = orderStaus;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStaus=" + orderStaus
				+ ", customer=" + customer + ", mobile=" + mobile + "]";
	}

		
}
