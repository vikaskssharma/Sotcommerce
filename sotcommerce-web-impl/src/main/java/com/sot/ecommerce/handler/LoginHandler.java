package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbRole;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.persistance.UmTbUserRole;
import com.sbsc.fos.um.common.UserAuthVO;
import com.sbsc.fos.um.service.CountryService;
import com.sbsc.fos.um.service.RoleService;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.um.service.UserRoleService;
import com.sbsc.fos.um.service.UserService;
import com.sbsc.fos.um.web.form.DeliveryAddressesForm;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.UserSignUpForm;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.EncryptionAndDecryption;

@Component
public class LoginHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 
	 * @param userSignInForm
	 * @return
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public UmTbUser signIn(UserAuthVO signInInfoVO)
			throws BusinessFailureException, GenericFailureException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		UmTbUser signInUser = new UmTbUser();
		signInUser.setVcEml(signInInfoVO.getUserEmail());
		signInUser.setVcPswrd(signInInfoVO.getUserPassword());
		String usrRoleName = signInInfoVO.getRolename();		
		StoreTbMaster store = storeService.findByID(signInInfoVO
				.getStoreid());
		signInUser.setStoreTbMaster(store);
		signInUser = userService.signIn(signInUser, usrRoleName);
		UmTbCntryMstr country = countryService.findByID(signInInfoVO.getStoreid());
		signInUser.setUmTbCntryMstr(country);
		return signInUser;
	}

	/**
	 * 
	 * @param userSignUpForm
	 * @param request
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public UmTbUser signUp(UserSignUpForm userSignUpForm)
			throws BusinessFailureException, GenericFailureException {
		UmTbUser signUpUser = new UmTbUser();
		signUpUser.setVcFrstNm(userSignUpForm.getFirstName());
		signUpUser.setVcLstNm(userSignUpForm.getLastName());
		signUpUser.setVcEml(userSignUpForm.getEmailID());
		signUpUser.setVcPhn(userSignUpForm.getPhone());

		String password = userSignUpForm.getPassword();
		EncryptionAndDecryption encrypt = new EncryptionAndDecryption("");
		String encryptedPassword = encrypt.encrypt(password);
		signUpUser.setVcPswrd(encryptedPassword);
		String usrRoleName = "Buyer";
		StoreTbMaster store = storeService.findByID(userSignUpForm.getStoreId());
		signUpUser.setStoreTbMaster(store);

		UmTbCntryMstr country = countryService.findByID(userSignUpForm
				.getCountryId());

		signUpUser.setUmTbCntryMstr(country);

		signUpUser.setVcAddr(userSignUpForm.getAddress());
		signUpUser.setVcCty(userSignUpForm.getCity());
		signUpUser.setVcSt(userSignUpForm.getState());
		signUpUser.setVcZpcd(userSignUpForm.getZipcode());

		
		java.util.Date date = new java.util.Date();
		
		
		
		//Calendar calanderDate=DateUtil.
		signUpUser.setDtCrtdAt(DateUtil.getCurrentDateTime());

		signUpUser.setNmCrtdBy(new BigDecimal(0));
		signUpUser.setIsDltd(new BigDecimal(0));
		signUpUser.setNmUpdtdBy(new BigDecimal(0));
		
		signUpUser.setDtUpdtdAt(DateUtil.getCurrentDateTime());

		userService.signUp(signUpUser, usrRoleName);
		long id = signUpUser.getNmUsrId();

		signUpUser.setNmCrtdBy(new BigDecimal(id));
		signUpUser.setNmUpdtdBy(new BigDecimal(id));
		List<UmTbRole> umTbRoles = roleService.findAllByProperty("vcRlNm",
				usrRoleName);

		ArrayList<UmTbUserRole> umtbuser = new ArrayList<UmTbUserRole>();

		UmTbUserRole umtbusrrole = new UmTbUserRole();

		umtbusrrole.setNmUsrRlId(signUpUser.getNmUsrId());
		umtbusrrole.setUmTbRole((UmTbRole) umTbRoles.get(0));
		umtbusrrole.setUmTbUser((UmTbUser) signUpUser);

		userRoleService.create(umtbusrrole);

		umtbuser.add(umtbusrrole);

		signUpUser.setUmTbUserRoles(umtbuser);

		userService.update(signUpUser);

		return signUpUser;

	}
	
	
	
	public UmTbUser guestSignUp(DeliveryAddressesForm deliveryAddressForm) throws GenericFailureException, BusinessFailureException
	{
		
		UmTbUser signUpUser = new UmTbUser();
		
		signUpUser.setVcEml(deliveryAddressForm.getGuestEmail());		
		String password = "rsystems";
		EncryptionAndDecryption encrypt = new EncryptionAndDecryption("");
		String encryptedPassword = encrypt.encrypt(password);
		signUpUser.setVcPswrd(encryptedPassword);
		String usrRoleName = "Buyer";
		//StoreTbMaster store = storeService.findByID(1);//((long)request.getSession().getAttribute("storeId"));
		java.util.Date date = new java.util.Date();
		signUpUser.setDtCrtdAt(DateUtil.getCurrentDateTime());
		signUpUser.setNmCrtdBy(new BigDecimal(0));
		signUpUser.setIsDltd(new BigDecimal(0));
		signUpUser.setNmUpdtdBy(new BigDecimal(0));
		signUpUser.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		signUpUser.setVcFrstNm(deliveryAddressForm.getShipFirstName());
		signUpUser.setVcLstNm(deliveryAddressForm.getShipLastName());
		signUpUser.setVcAddr(deliveryAddressForm.getShipAddress());
		signUpUser.setVcCty(deliveryAddressForm.getShipcity());
		signUpUser.setVcPhn(deliveryAddressForm.getShipphone());
		signUpUser.setVcSt(deliveryAddressForm.getShipstate());
		signUpUser.setVcZpcd(deliveryAddressForm.getShippincode());//guest22@rsys.com
		StoreTbMaster store = storeService.findByID(deliveryAddressForm.getStoreId());
		signUpUser.setStoreTbMaster(store);

		UmTbCntryMstr country = countryService.findByID(deliveryAddressForm.getShipcountryId());

		signUpUser.setUmTbCntryMstr(country);
		userService.signUp(signUpUser, usrRoleName);		
		long id = signUpUser.getNmUsrId();
		signUpUser.setNmCrtdBy(new BigDecimal(id));
		signUpUser.setNmUpdtdBy(new BigDecimal(id));
		List<UmTbRole> umTbRoles = roleService.findAllByProperty("vcRlNm",usrRoleName);
		ArrayList<UmTbUserRole> umtbuser = new ArrayList<UmTbUserRole>();
		UmTbUserRole umtbusrrole = new UmTbUserRole();
		umtbusrrole.setNmUsrRlId(signUpUser.getNmUsrId());
		umtbusrrole.setUmTbRole((UmTbRole) umTbRoles.get(0));
		umtbusrrole.setUmTbUser((UmTbUser) signUpUser);
		userRoleService.create(umtbusrrole);
		umtbuser.add(umtbusrrole);
		signUpUser.setUmTbUserRoles(umtbuser);
		userService.update(signUpUser);
		return signUpUser;
		
		
		
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

}
