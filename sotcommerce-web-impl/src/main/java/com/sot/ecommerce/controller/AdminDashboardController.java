package com.sot.ecommerce.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.um.web.form.ChangePasswordForm;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.validator.AdminPasswordValidator;
import com.sbsc.fos.um.web.form.validator.ProfileValidator;
import com.sbsc.fos.um.web.handler.AdminDashboardHandler;
import com.sbsc.fos.utils.DashboardCommonUtil;
import com.sbsc.fos.utils.EncryptionAndDecryption;




@Controller
public class AdminDashboardController {

	private static final Logger logger = Logger
			.getLogger(AdminDashboardController.class);

	@Autowired
	ProfileForm adminProfileForm;

	@Autowired
	ChangePasswordForm changePasswordForm;

	@Autowired
	private ProfileValidator profileValidator;
	@Autowired
	private AdminPasswordValidator adminPasswordValidator;

	@Autowired
	private AdminDashboardHandler adminHandler;

	@Autowired
	private MessageSource messageSource;

	private UmTbUser userInfo;

	private List<UmTbCntryMstr> countryList;

	
	/**
	 * This takes you to the admin profile Page which shows the profile
	 * of the admin.
	 
	 */
	
	
	
	
	@RequestMapping(value = "admin/adminprofile.htm", method = RequestMethod.GET)
	public ModelAndView adminProfile(
			@ModelAttribute("adminProfileForm") ProfileForm adminProfileForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		try {

			getUserInfo(request);
			getCountryList();
			DashboardCommonUtil dashboardutil = new DashboardCommonUtil();
			adminProfileForm = dashboardutil.setAdminProfileDataInForm(
					userInfo, adminProfileForm);
			model.addAttribute("adminProfileForm", adminProfileForm);
			model.addAttribute("countryList", countryList);
			dashboardutil = null;
			return new ModelAndView("/usermanagement/adminprofile");
		} catch (BusinessFailureException bfe) {

			logger.error("admin profile  has BusinessFailureException ."
					+ adminProfileForm);
			bindingResult.rejectValue("errorMessage",
					String.valueOf(bfe.getErrorID()));
			return new ModelAndView("/usermanagement/adminprofile");

		} catch (GenericFailureException gfe) {
			throw gfe;
		}

	}
	
	
	/**
	 * This action gets call while admin update his profile.
	 
	 */
	
	

	@RequestMapping(value = "admin/updateAdminProfile.htm", method = RequestMethod.POST)
	public ModelAndView updateAdminProfile(
			@ModelAttribute("adminProfileForm") ProfileForm adminProfileForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		profileValidator.validate(adminProfileForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("admin profile updation has validation errors."
					+ adminProfileForm);

			model.addAttribute("userSignInForm", adminProfileForm);
			getCountryList();
			model.addAttribute("countryList", countryList);
			return new ModelAndView("/usermanagement/adminprofile");

		} else {
			try {
				DashboardCommonUtil dashboardutil = new DashboardCommonUtil();
				getUserInfo(request);
				userInfo = dashboardutil.editAdminProfileData(adminProfileForm,
						userInfo);
				setEditedCountryInforamtion(userInfo,adminHandler,adminProfileForm);
				userInfo = adminHandler.updateAdminProfile(userInfo);
				dashboardutil.setAdminProfileDataInForm(userInfo,
						adminProfileForm);
				model.addAttribute("adminProfileForm", adminProfileForm);
				getCountryList();
				model.addAttribute("countryList", countryList);
				String successMessage = messageSource.getMessage(
						"profileupdate.successful", null,
						"updated successfully", request.getLocale());

				redirectAttributes.addFlashAttribute("message", successMessage);
				return new ModelAndView("redirect:/admin/adminprofile.htm");

			} catch (BusinessFailureException bfe) {

				logger.error("admin profile updation has BusinessFailureException ."
						+ adminProfileForm);
				bindingResult.rejectValue("errorMessage",
						String.valueOf(bfe.getErrorID()));
				return new ModelAndView("/usermanagement/adminprofile");

			} catch (GenericFailureException gfe) {
				logger.error("admin profile updation has GenericFailureException ."
						+ adminProfileForm);
				throw gfe;
			}
		}

	}

	private void setEditedCountryInforamtion(UmTbUser userInfo,
			AdminDashboardHandler adminHandler, ProfileForm adminProfileForm) {
		
		userInfo.setUmTbCntryMstr(adminHandler.getCountryById(adminProfileForm.getCountryId()));
		
		
	}
	
	/**
	 * This action gets call while admin update his password.
	 
	 */
	

	@RequestMapping(value = "admin/updatepassword.htm", method = RequestMethod.POST)
	public ModelAndView adminUpdatePassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		try {
			DashboardCommonUtil dashboardutil = new DashboardCommonUtil();
			getUserInfo(request);
			EncryptionAndDecryption decrypt = new EncryptionAndDecryption("");
			String oldPassword = decrypt.decrypt(userInfo.getVcPswrd());
			decrypt = null;
			changePasswordForm.setUserPassword(oldPassword);
			adminPasswordValidator.validate(changePasswordForm, bindingResult);

			if (bindingResult.hasErrors()) {

				logger.error("password change updation has validation errors."
						+ changePasswordForm);

				model.addAttribute("changePasswordForm", changePasswordForm);

				dashboardutil = null;
				return new ModelAndView("/usermanagement/changepassword");

			} else {

				String encryptedPassword = dashboardutil
						.encryptPassword(changePasswordForm.getPassword());
				userInfo.setVcPswrd(encryptedPassword);

				adminHandler.updateAdminPassword(userInfo);
				changePasswordForm.clear();
				redirectAttributes.addFlashAttribute("message",
						"Updated successfully.");
				dashboardutil = null;
				return new ModelAndView("redirect:/admin/changePassword.htm");
			}
		} catch (BusinessFailureException bfe) {

			logger.error("admin profile updation has BusinessFailureException ."
					+ changePasswordForm);
			bindingResult.rejectValue("errorMessage",
					String.valueOf(bfe.getErrorID()));
			return new ModelAndView("/usermanagement/changepassword");

		} catch (GenericFailureException gfe) {
			logger.error("admin profile updation has GenericFailureException."
					+ changePasswordForm);
			throw gfe;
		}

	}
	
	/**
	 * This will take you to change password screen.
	 
	 */
	
	

	@RequestMapping(value = "admin/changePassword.htm", method = RequestMethod.GET)
	public ModelAndView changePassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		model.addAttribute("changePasswordForm", changePasswordForm);

		return new ModelAndView("/usermanagement/changepassword");

	}

	private void getUserInfo(HttpServletRequest request)
			throws BusinessFailureException {
		
			userInfo = adminHandler.getUserInfo((long) request
					.getSession().getAttribute("userId"));
			setUserInfo(userInfo);
		
	}

	public void setUserInfo(UmTbUser userInfo) {
		this.userInfo = userInfo;
	}

	private void getCountryList() {
		if (countryList==null) {
			countryList = adminHandler.getCountryList();
			setCountryList(countryList);
		}
	}

	public void setCountryList(List<UmTbCntryMstr> countryList) {
		this.countryList = countryList;
	}

}
