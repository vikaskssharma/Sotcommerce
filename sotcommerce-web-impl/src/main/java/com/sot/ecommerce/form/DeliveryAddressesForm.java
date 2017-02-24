package com.sot.ecommerce.form;

import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressesForm {
	
	
	String shipFirstName;
	String shipLastName;
	String shipAddress;
	String shipcity;
	String shippincode;
	
	String shipstate;
	long shipcountryId;
	String shipphone;
	
	
	String billFirstName;
	String billLastName;
	String billAddress;
	String billcity;
	String  billpincode;
	
	String billstate;
	long billcountryId;
	String billphone;
	
	boolean sameShipAddress;
	boolean sameBillAddress;
	boolean useshippingaddress;
	String guestEmail;
	long storeId=0;	
	private String errorMessage;
	
	public String getShipFirstName() {
		return shipFirstName;
	}
	public void setShipFirstName(String shipFirstName) {
		this.shipFirstName = shipFirstName;
	}
	public String getShipLastName() {
		return shipLastName;
	}
	public void setShipLastName(String shipLastName) {
		this.shipLastName = shipLastName;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public String getShipcity() {
		return shipcity;
	}
	public void setShipcity(String shipcity) {
		this.shipcity = shipcity;
	}
	
	public String getShipstate() {
		return shipstate;
	}
	public void setShipstate(String shipstate) {
		this.shipstate = shipstate;
	}
	public long getShipcountryId() {
		return shipcountryId;
	}
	public void setShipcountryId(long shipcountryId) {
		this.shipcountryId = shipcountryId;
	}
	public String getShipphone() {
		return shipphone;
	}
	public void setShipphone(String shipphone) {
		this.shipphone = shipphone;
	}
	public String getBillFirstName() {
		return billFirstName;
	}
	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}
	public String getBillLastName() {
		return billLastName;
	}
	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}
	public String getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	public String getBillcity() {
		return billcity;
	}
	public void setBillcity(String billcity) {
		this.billcity = billcity;
	}
	
	public String getBillstate() {
		return billstate;
	}
	public void setBillstate(String billstate) {
		this.billstate = billstate;
	}
	public long getBillcountryId() {
		return billcountryId;
	}
	public void setBillcountryId(long billcountryId) {
		this.billcountryId = billcountryId;
	}
	public String getBillphone() {
		return billphone;
	}
	public void setBillphone(String billphone) {
		this.billphone = billphone;
	}
	public String getShippincode() {
		return shippincode;
	}
	public void setShippincode(String shippincode) {
		this.shippincode = shippincode;
	}
	public void setBillpincode(String billpincode) {
		this.billpincode = billpincode;
	}
	public String getBillpincode() {
		return billpincode;
	}
	
	
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isSameShipAddress() {
		return sameShipAddress;
	}
	public void setSameShipAddress(boolean sameShipAddress) {
		this.sameShipAddress = sameShipAddress;
	}
	public boolean isSameBillAddress() {
		return sameBillAddress;
	}
	public void setSameBillAddress(boolean sameBillAddress) {
		this.sameBillAddress = sameBillAddress;
	}
	public boolean isUseshippingaddress() {
		return useshippingaddress;
	}
	public void setUseshippingaddress(boolean useshippingaddress) {
		this.useshippingaddress = useshippingaddress;
	}
	
	public void clear()
	{
		this.shipFirstName="";
		this.shipLastName="";
		this.shipAddress="";
		this.shipcity="";
		this.shippincode="";
		
		this.shipstate="";
		this.shipcountryId=0;
		this.shipphone="";
		
		
		this.billFirstName="";
		this.billLastName="";
		this.billAddress="";
		this.billcity="";
		this.billpincode="";
		
		this.billstate="";
		this.billcountryId=0;
		this.billphone="";
		
		this.sameShipAddress=false;
		this.sameBillAddress=false;
		this.useshippingaddress=false;
		this.guestEmail="";
		this.storeId=0;	
		this.errorMessage="";
		
	}
	
	
	
}
