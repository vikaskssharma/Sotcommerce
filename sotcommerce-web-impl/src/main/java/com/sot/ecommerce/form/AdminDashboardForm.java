package com.sot.ecommerce.form;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class AdminDashboardForm {
	
	
	@NotNull
	@Max(32)
	private List leftMenuList;

	public List getLeftMenuList() {
		return leftMenuList;
	}

	public void setLeftMenuList(List leftMenuList) {
		this.leftMenuList = leftMenuList;
	}


}
