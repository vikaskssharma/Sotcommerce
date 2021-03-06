package com.sot.ecommerce.vo;

import java.util.List;

public class FeedbackVO {

	private long orderId;

	private String orderNumber;

	private List<FeedbackProductVO> productList;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<FeedbackProductVO> getProductList() {
		return productList;
	}

	public void setProductList(List<FeedbackProductVO> productList) {
		this.productList = productList;
	}

}
