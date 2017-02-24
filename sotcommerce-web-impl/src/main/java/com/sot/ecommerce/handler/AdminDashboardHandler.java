package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.service.OrderService;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.PromotionTbMaster;
import com.sbsc.fos.persistance.ReviewNRatingTbDetail;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.persistance.UmTbUserRole;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.promotion.commons.PromotionVO;
import com.sbsc.fos.promotion.service.PromotionService;
import com.sbsc.fos.rr.service.ReviewService;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.um.common.OrderVO;
import com.sbsc.fos.um.common.UserAuthVO;
import com.sbsc.fos.um.common.UserInfoVO;
import com.sbsc.fos.um.service.CountryService;
import com.sbsc.fos.um.service.MenuServices;
import com.sbsc.fos.um.service.UserService;
import com.sbsc.fos.um.web.form.OrderForm;

@Component
public class AdminDashboardHandler {

	@Autowired
	private MenuServices menuService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CountryService countryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private  StoreService storeService;
	@Autowired
	private  OrderService orderService;
	@Autowired
	private  PromotionService promotionService;

	public UserService getUserService() {
		return userService;
	}




	public void setUserService(UserService userService) {
		this.userService = userService;
	}




	public ReviewService getReviewService() {
		return reviewService;
	}




	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}




	public CountryService getCountryService() {
		return countryService;
	}




	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}




	public ProductService getProductService() {
		return productService;
	}




	public void setProductService(ProductService productService) {
		this.productService = productService;
	}




	public StoreService getStoreService() {
		return storeService;
	}




	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}




	public List<UserInfoVO> getLeftMenuContent(UserAuthVO userAuthVO)
			throws BusinessFailureException, GenericFailureException {
		UmTbUser user = new UmTbUser();
		user.setVcEml(userAuthVO.getUserEmail());
		user.setVcPswrd(userAuthVO.getUserPassword());
		List<UserInfoVO> userData = menuService.getLeftMenuContent(user);

		return userData;
	}
	
	
	

	public UmTbUser getUserInfo(long userId) throws BusinessFailureException,
			GenericFailureException {
		UmTbUser user=null;
		
		 user = userService.findByID(userId);
		
		return user;
	}
	
	
	public UmTbUser isUserExist(String email,String role) throws BusinessFailureException,
	GenericFailureException {
List<UmTbUser> userList=null;

 userList = userService.findAll();

 Iterator<UmTbUser> userItr=userList.iterator();
 
 while(userItr.hasNext())
 {
	 UmTbUser user =userItr.next();
	 
	if( user.getVcEml().equals(email))
	{
		
		List<UmTbUserRole> userRoles=user.getUmTbUserRoles();
		UmTbUserRole userRole=userRoles.get(0);
		String roleName=userRole.getUmTbRole().getVcRlNm();
		
		if(role.equalsIgnoreCase(roleName))
		{
			return user;
		}
		
	
		
	}
	 
 }
 

return null;
}
	

	public List<UmTbCntryMstr> getCountryList() {

		List<UmTbCntryMstr> countryList = countryService.findAll();

		return countryList;
	}

	public UmTbCntryMstr getCountryById(long id) {

		UmTbCntryMstr country = countryService.findByID(id);

		return country;
	}

	public UmTbUser updateAdminProfile(UmTbUser user) throws BusinessFailureException,
			GenericFailureException {
		System.out.println("user::::::"+user.getNmUsrId());
		userService.update(user);
		UmTbUser updateduser = userService.findByID(user.getNmUsrId());
		System.out.println("user=" + user);
		
		// added to update records in ReviewnRatingTbDetail while updating profile
		try {
			List<ReviewNRatingTbDetail> reviewsList = reviewService.findAllByProperty("nmUsrId", BigDecimal.valueOf(user.getNmUsrId()));
			for (ReviewNRatingTbDetail reviewNRatingTbDetail : reviewsList) {
				reviewNRatingTbDetail.setVcUsrNm(user.getVcFrstNm()+" "+user.getVcLstNm());
				reviewService.update(reviewNRatingTbDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// added to update records in ReviewnRatingTbDetail while updating profile-- ends
		
		
		return updateduser;
	}

	public void updateAdminPassword(UmTbUser user) {

		userService.update(user);
	}

	public MenuServices getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuServices menuService) {
		this.menuService = menuService;
	}
	public List<ProductBasicForm> listRecentlyAddedProduct(List<ProductBasicForm> productBasicFormlist ,Long storeId){
		HashMap<String,Object> properties=new HashMap<String,Object>();
		StoreTbMaster storeTbMaster=storeService.findByID(storeId);
		properties.put("storeTbMaster",storeTbMaster);
		properties.put("desc", "dtCrtdAt");
		
	    properties.put("count", 10);
		List<ProductTbMaster> productList=productService.findAllByProperty(properties);
		for(ProductTbMaster productTbMaster:productList){

			ProductBasicForm productBasicForm=new ProductBasicForm();
			productBasicForm.setProductName(productTbMaster.getVcPrdNm());
			
			productBasicFormlist.add(productBasicForm);
			
			
		}
		return productBasicFormlist;
		
	}
	
	public  List<OrdersVO> todayOrderCount(Long storeId) throws ParseException{
		
		
		HashMap<String,Object> properties=new HashMap<String,Object>();
		StoreTbMaster storeTbMaster=storeService.findByID(storeId);
		properties.put("storeTbMaster",storeTbMaster);
		properties.put("Cal_dtCrtdAt", new Date());
	    properties.put("desc", "dtCrtdAt");
	    properties.put("count",10);
	    List<OrdersVO> orderlist=new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList=orderService.findAllByPropertyDateandCount(properties);
		for(OrderTbMaster orderTbMaster:orderList){
			OrdersVO orderform=new OrdersVO();
			orderform.setOrderNumber(orderTbMaster.getVcOrdrNmbr());
			orderform.setOrderStatus(orderTbMaster.getOrderStatusTbMaster().getVcSttsDesc());
			//orderform.setPaymentStatus(orderTbMaster.getPaymentTbMaster().getVcPymntDscr());
			orderlist.add(orderform);
		}
		System.out.println("order list:::::"+orderlist.size());
		return orderlist;
		
	}
	public List<PromotionVO> todayPramotions(Long storeId){
		List<PromotionVO> promotionList=new ArrayList<PromotionVO>();
		
	
		HashMap<String,Object> properties=new HashMap<String,Object>();
		StoreTbMaster storeTbMaster=storeService.findByID(storeId);
		properties.put("storeTbMaster",storeTbMaster);
		properties.put("Cal_dtCrtdAt", new Date());
	    properties.put("desc", "dtCrtdAt");
	    properties.put("count",10);
		List<PromotionTbMaster> promotionlist=promotionService.findAllByPropertyDateandCount(properties);
		for(PromotionTbMaster promotionTbMaster:promotionlist){
	
			PromotionVO promotionVO=new PromotionVO();
			promotionVO.setPromotionRuleCode(promotionTbMaster.getVcRlCd());
			promotionVO.setPromotionRuleName(promotionTbMaster.getVcRlNm());
			promotionVO.setStatus(promotionTbMaster.getVcStts());
			promotionList.add(promotionVO);
		 }
        System.out.println("promotionList::::"+promotionList.size());
		return promotionList;
		
	}
	public List<ProductBasicForm> productoutofstock(Long storeId){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		HashMap<String,Object> properties=new HashMap<String,Object>();
		StoreTbMaster storeTbMaster=storeService.findByID(storeId);
		properties.put("storeTbMaster",storeTbMaster);
		properties.put("nmQnty", new BigDecimal(0));
		 properties.put("count",10);
	    List<ProductBasicForm> productBasicFormlist=new ArrayList<ProductBasicForm>();
		List<ProductTbMaster> productlist=productService.findAllByProperty(properties);
		for(ProductTbMaster productTbMaster:productlist){
		
			ProductBasicForm productBasicForm=new ProductBasicForm();
			productBasicForm.setProductName(productTbMaster.getVcPrdNm());
			productBasicForm.setStatus(productTbMaster.getVcStts());
			productBasicForm.setQuantity(productTbMaster.getNmQnty().toString());
			productBasicFormlist.add(productBasicForm);
			
			
		}
		System.out.println("productlist size for out of stock::::"+productlist.size());
		return productBasicFormlist;
	
	}

}
