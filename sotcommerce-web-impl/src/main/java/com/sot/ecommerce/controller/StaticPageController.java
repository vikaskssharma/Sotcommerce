package com.sot.ecommerce.controller;



import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticPageController {
	
	
	@Resource(name="propertiesstore")
	private Properties storepropeties;
	
	@RequestMapping(value = "store/static_aboutus.htm", method = RequestMethod.GET)
	public ModelAndView aboutus(Model model,HttpServletRequest request){
		String sitename = storepropeties.getProperty("static.Sitename");
		model.addAttribute("sitename",sitename);
		String companyname = storepropeties.getProperty(
				"static.aboutus.Company");
		model.addAttribute("companyname",companyname);
		String establistat = storepropeties.getProperty(
				"static.aboutus.establistat");
		model.addAttribute("establistat",establistat);
		String noofcities = storepropeties.getProperty("static.aboutus.noofcities");
		model.addAttribute("noofcities",noofcities);
		String district = storepropeties.getProperty("static.about.district");
		model.addAttribute("district",district);
		return new ModelAndView("staticpages/aboutus");
		
		
	}
	
	@RequestMapping(value = "store/static_help.htm", method = RequestMethod.GET)
	private ModelAndView help(Model model,HttpServletRequest request) {
		String sitename = storepropeties.getProperty(
				"static.Sitename");
		model.addAttribute("sitename",sitename);
		
		return new ModelAndView("staticpages/help");
	}
	
	@RequestMapping(value = "store/static_faq.htm", method = RequestMethod.GET)
	private ModelAndView faq(Model model,HttpServletRequest request) {
		String sitenamecom = storepropeties.getProperty(
				"static.Sitename.com");
		model.addAttribute("sitenamecom",sitenamecom);
		String phonenumber = storepropeties.getProperty(
				"static.PhoneNumber");
		model.addAttribute("phonenumber",phonenumber);
		String sitenameemail = storepropeties.getProperty(
				"static.aboutus.sitenameemail");
		model.addAttribute("sitenameemail",sitenameemail);
		return new ModelAndView("staticpages/faq");
	}
	
	@RequestMapping(value = "store/static_tandc.htm", method = RequestMethod.GET)
	private ModelAndView termsandcondition(Model model,HttpServletRequest request) {
		String sitename = storepropeties.getProperty(
				"static.Sitename");
		model.addAttribute("sitename",sitename);
		String sitenamecom = storepropeties.getProperty(
				"static.Sitename.com");
		model.addAttribute("sitenamecom",sitenamecom);
		String websitename = storepropeties.getProperty(
				"static.website.name");
		model.addAttribute("websitename",websitename);
		String rfnumber = storepropeties.getProperty(
				"static.aboutus.rfnumber");
		model.addAttribute("rfnumber",rfnumber);
		String name = storepropeties.getProperty(
				"static.aboutus.name");
		model.addAttribute("name",name);
		String sitenameemail = storepropeties.getProperty(
				"static.aboutus.sitenameemail");
		model.addAttribute("sitenameemail",sitenameemail);
	
		String district = storepropeties.getProperty(
				"static.about.district");
		model.addAttribute("district",district);
		return new ModelAndView("staticpages/tandc");
	}
	@RequestMapping(value = "store/static_disclamer.htm", method = RequestMethod.GET)
	private ModelAndView disclamer(Model model,HttpServletRequest request) {
		String sitename = storepropeties.getProperty(
				"static.Sitename");
		model.addAttribute("sitename",sitename);
		return new ModelAndView("staticpages/disclamer");
	}
	@RequestMapping(value = "store/static_privacypolicy.htm", method = RequestMethod.GET)
	private ModelAndView privacypolicy(Model model,HttpServletRequest request) {
		String sitenamecom = storepropeties.getProperty(
				"static.Sitename.com");
		model.addAttribute("sitenamecom",sitenamecom);
		String sitenameemail = storepropeties.getProperty(
				"static.aboutus.sitenameemail");
		model.addAttribute("sitenameemail",sitenameemail);
		return new ModelAndView("staticpages/privacypolicy");
	}

}
