package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for WishList
 * 
 * 
 * @author Abhishek.Vishnoi
 * 
 */
@Service
public class WishListServiceImpl implements WishListService {

	private GenericDAO<WishlistTbDtl> dao;

	private long usrID;

	private long storeID;

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductVariantService variantService;

	// private SessionInfoConversion sessionInfoConversion;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(WishListController.class);

	@Autowired
	public void setDAO(GenericDAO<WishlistTbDtl> daoToSet) {
		logger.info("daoToSet for WishList" + daoToSet);
		dao = daoToSet;
		dao.setClazz(WishlistTbDtl.class);
	}

	/**
	 * 
	 * Service Method to find WishList
	 * 
	 * Returns a List containing WishList
	 * 
	 * @throws ParseException
	 * 
	 */
	@Transactional
	public List<WishListVO> findWishList() throws ParseException {
		List<WishListVO> wishList = new ArrayList<WishListVO>();
		List<WishlistTbDtl> detailList = null;

		detailList = dao.findAll();
		wishList = getDetailList(detailList);

		return wishList;
	}

	/**
	 * Service to Find all Wish List on basis of Session info
	 * 
	 * @throws ParseException
	 * 
	 */
	@Transactional
	public List<WishListVO> findWishListByID(SessionInfo sessionInfo)
			throws ParseException {
		List<WishListVO> wishList = new ArrayList<WishListVO>();
		List<WishlistTbDtl> detailList = null;

		// HashMap<String, Object> userMap =
		// sessionInfoConversion.UserInfoMap(sessionInfo);
		HashMap<String, Object> userMap = getUserMap(sessionInfo);

		detailList = dao.findAllByProperty(userMap);

		wishList = getDetailList(detailList);
		return wishList;
	}

	/**
	 * Service Method to Delete a single wish on basis of wishID
	 * 
	 * @throws ParseException
	 * 
	 */
	@Transactional
	public List<WishListVO> deleteById(long wishId, SessionInfo sessionInfo)
			throws BusinessFailureException, ParseException {

		WishlistTbDtl wish = dao.findByID(wishId);

		if (wish != null) {

			dao.deleteById(wishId);
			List<WishListVO> wishList = findWishListByID(sessionInfo);
			return wishList;
		} else {
			List<WishListVO> wishList = findWishListByID(sessionInfo);
			return wishList;
		}
	}

	/**
	 * Service Method To find the Wish List for Filters
	 * 
	 * Returns a List of WishList
	 * 
	 * @throws ParseException
	 */
	@Override
	@Transactional
	public List<WishListVO> findAllWishListByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws Exception {
		List<WishListVO> wishList = new ArrayList<WishListVO>();
		List<WishlistTbDtl> detailList = null;

		usrID = sessionInfo.getUserId();
		storeID = sessionInfo.getStoreId();

		filter_criteria.put("filter.WishlistTbDtl.umTbUser",
				userService.findByID(usrID));
		filter_criteria.put("filter.WishlistTbDtl.storeTbMaster",
				storeService.findByID(storeID));

		detailList = dao.findAllByFilters(filter_criteria);
		wishList = getDetailList(detailList);

		return wishList;
	}

	/**
	 * get needed data to be displayed in form of detailList
	 * 
	 * @param wishList
	 * @return
	 * @throws ParseException
	 */
	public List<WishListVO> getDetailList(List<WishlistTbDtl> wishList)
			throws ParseException {

		logger.info("converting wishList to wishListVO");
		List<WishListVO> detailList = new ArrayList<WishListVO>();

		/*
		 * SimpleDateFormat db_date_format = new SimpleDateFormat("MM-dd-yyyy");
		 * // SimpleDateFormat view_date_format = new //
		 * SimpleDateFormat("MM-dd-yyyy"); Date orderDate = null;
		 */
       int i=0;
		for (WishlistTbDtl wishdtl : wishList) {
			WishListVO wishListVO = new WishListVO();           
			wishListVO.setWishListID(wishdtl.getNmWshlstId());
			wishListVO.setProdImgPath(wishdtl.getProductTbMaster()
					.getVcPrdImg1Pth());
			if(wishdtl.getProductTbMaster().getIsVrnt().compareTo(new BigDecimal("0"))==0){
				wishListVO.setProdName(wishdtl.getProductTbMaster().getVcPrdNm());
				wishListVO.setStatus(wishdtl.getProductTbMaster().getVcStts());
				wishListVO.setProductid(wishdtl.getProductTbMaster().getNmPrdId());				
			}else{
				wishListVO.setProdName(wishdtl.getProductVariantTbDtl().getVcVrntNm());
				wishListVO.setStatus(wishdtl.getProductVariantTbDtl().getVcStts());
				wishListVO.setProductid(wishdtl.getProductTbMaster().getNmPrdId());
				wishListVO.setVariantid(wishdtl.getProductVariantTbDtl().getNmVrntId());
			}
			wishListVO.setDateAddWish(wishdtl.getDtWshlstAddDt().getTime());
			wishListVO.setStoreName(wishdtl.getStoreTbMaster().getVcStrNm());

			logger.info("changing date to string");

			

			detailList.add(wishListVO);
			
		}

		return detailList;
	}

	@Transactional
	public boolean addWishList(Map prodAndVaraintIds, SessionInfo sessionInfo) {
		usrID = sessionInfo.getUserId();
		HashMap<String, Object> wishMap = new HashMap<>();
		wishMap.put("productTbMaster", productService.findByID((Long)prodAndVaraintIds.get("ProductId")));
		wishMap.put("umTbUser", userService.findByID(usrID));

		if (dao.findAllByProperty(wishMap).size() < 1) {

			WishlistTbDtl newWish = new WishlistTbDtl();
			newWish.setUmTbUser(userService.findByID(sessionInfo.getUserId()));
			newWish.setStoreTbMaster(storeService.findByID(sessionInfo
					.getStoreId()));
			newWish.setDtCrtdAt(DateUtil.getCurrentDateTime());
			newWish.setDtUpdtdAt(DateUtil.getCurrentDateTime());
			newWish.setDtWshlstAddDt(DateUtil.getCurrentDateTime());
			newWish.setIsDltd(BigDecimal.valueOf(0L));
			newWish.setProductTbMaster(productService.findByID((Long)prodAndVaraintIds.get("ProductId")));
			newWish.setNmCrtdBy(new BigDecimal(usrID));
			newWish.setNmUpdtdBy(new BigDecimal(usrID));
			if((Long)prodAndVaraintIds.get("VariantId")!=null){
			newWish.setProductVariantTbDtl(variantService.findByID((Long)prodAndVaraintIds.get("VariantId")));
			}
			dao.create(newWish);
		}

		return true;
	}
	/**
	 * 
	 * Find product ID for corresponding wish
	 */
	@Transactional
	public long findProductByWishID(long wishID)
	{
		
			WishlistTbDtl wish = dao.findByID(wishID);
			
			 return wish.getProductTbMaster().getNmPrdId();
		
	}

	/**
	 * returns a Map containing user info from session object
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public HashMap<String, Object> getUserMap(SessionInfo sessionInfo) {
		usrID = sessionInfo.getUserId();
		storeID = sessionInfo.getStoreId();
		HashMap<String, Object> userMap = new HashMap<>();
		userMap.put("umTbUser", userService.findByID(usrID));
		userMap.put("storeTbMaster", storeService.findByID(storeID));
		userMap.put("desc", "dtWshlstAddDt");
		return userMap;
	}

}
