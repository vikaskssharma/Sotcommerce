package com.sot.ecommerce.jms;

public class MessageObject {
	  private String mailId;
	  private String message;
	     
	 public MessageObject(){};
	 public MessageObject(String mailId, String message) {
	  super();
	  this.mailId = mailId;
	  this.message = message;
	 }
	 public String getMailId() {
	  return mailId;
	 }
	 public void setMailId(String mailId) {
	  this.mailId = mailId;
	 }
	 public String getMessage() {
	  return message;
	 }
	 public void setMessage(String message) {
	  this.message = message;
	 }
	}
