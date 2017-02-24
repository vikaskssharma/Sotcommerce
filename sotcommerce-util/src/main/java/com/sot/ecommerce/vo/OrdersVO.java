package com.sot.ecommerce.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersVO {
	
	private ShippingVO shippingVO;
	
	private BillingVO billingVO;
	
	private String orderId;
	
	private String orderNumber;
	
	private String storeName;
	
	private long storeId;
	 
	private Date orderDate;
	
	private long orderCreatedBy;
	
	private BigDecimal totalCost;
	
	private BigDecimal totalPromoCost;

	private String orderStatus;
	
	private String paymentStatus;
	
	
	//for orders' history
	private String productName;
	
	private String productDescription;
	
	private String orderHistoryDate;
	
	private BigDecimal quantity;
		
	private BigDecimal gvDiscount;	
	
	private BigDecimal totalOrderCost;
	
	private String paymentMethod;
	
	private String gvCode;
	
	//for orders' history ends
	
	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalPromoCost() {
		return totalPromoCost;
	}

	public void setTotalPromoCost(BigDecimal totalPromoCost) {
		this.totalPromoCost = totalPromoCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public long getOrderCreatedBy() {
		return orderCreatedBy;
	}

	public void setOrderCreatedBy(long orderCreatedBy) {
		this.orderCreatedBy = orderCreatedBy;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getOrderHistoryDate() {
		return orderHistoryDate;
	}

	public void setOrderHistoryDate(String orderHistoryDate) {
		this.orderHistoryDate = orderHistoryDate;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getGvDiscount() {
		return gvDiscount;
	}

	public void setGvDiscount(BigDecimal gvDiscount) {
		this.gvDiscount = gvDiscount;
	}

	public BigDecimal getTotalOrderCost() {
		return totalOrderCost;
	}

	public void setTotalOrderCost(BigDecimal totalOrderCost) {
		this.totalOrderCost = totalOrderCost;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getGvCode() {
		return gvCode;
	}

	public void setGvCode(String gvCode) {
		this.gvCode = gvCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ShippingVO getShippingVO() {
		return shippingVO;
	}

	public void setShippingVO(ShippingVO shippingVO) {
		this.shippingVO = shippingVO;
	}

	public BillingVO getBillingVO() {
		return billingVO;
	}

	public void setBillingVO(BillingVO billingVO) {
		this.billingVO = billingVO;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
