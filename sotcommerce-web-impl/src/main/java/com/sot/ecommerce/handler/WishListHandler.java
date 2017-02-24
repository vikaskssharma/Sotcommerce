package com.sot.ecommerce.handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.wishlist.common.WishListVO;
import com.sbsc.fos.wishlist.service.WishListService;

/**
 *  Handler for Wish List
 * 
 * 
 * @author Abhishek.Vishnoi
 * 
 */
@Component
public class WishListHandler {

	@Autowired
	private WishListService wishListService;


	/**
	 * 
	 * Handler Method to get the WishList on basis of userID and storeID
	 *  
	 * @param userMap
	 * @return returns a List<WishListVO>  fetched from Service
	 * @throws ParseException 
	 * 
	 * 
	 */
	public List<WishListVO> getwishListbyID(SessionInfo sessionInfo) throws ParseException {

		List<WishListVO> wishList = wishListService
				.findWishListByID(sessionInfo);
		return wishList;
	}

	
	public boolean addWish(Map prodAndVaraintIds ,SessionInfo sessionInfo)
	{
		boolean checkAdd = wishListService.addWishList(prodAndVaraintIds, sessionInfo);
		
		return checkAdd;
	}
	
	
	/**
	 * 
	 * Handler Method to delete a Wish from the Wish List
	 * 
	 * @param wishID & sessionInfo
	 * @return a WishList with one deleted Wish
	 * @throws BusinessFailureException 
	 * @throws ParseException 
	 * 
	 */
	public List<WishListVO> deleteWish(long wishID, SessionInfo sessionInfo) 
			throws BusinessFailureException, ParseException {

		List<WishListVO> wishList = wishListService.deleteById(wishID , sessionInfo);
	
		return wishList;
	}
	

	/**
	 * 
	 * Handler Method to find Wish List for Filters
	 * 
	 * @param filter_criteria
	 * @return a List containing filtered WishList
	 * @throws Exception 
	 */
	public List<WishListVO> findAllWishListByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo) 
			throws Exception {
		
		List<WishListVO> wishList = new ArrayList<WishListVO>();
		wishList = wishListService.findAllWishListByFilters(filter_criteria,
				sessionInfo);
	
		return wishList;
	}
}
