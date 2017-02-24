package com.sot.ecommerce.service;

import java.util.List;

import com.sot.ecommerce.dto.UserInfoDTO;
import com.sot.ecommerce.entities.User;



public interface MenuServices {
	
	public List<UserInfoDTO> getLeftMenuContent(User signInUser);
	
}
