/**
 * 
 */
package com.sot.ecommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.product.web.form.UploadForm;

/**
 * @author diksha.rattan
 * 
 */
@Controller
public class UploadFormController {

	final static String IMAGE_RESOURCE_PATH = "/uploadimages";

	@RequestMapping(value = "/FileUploadForm", method = RequestMethod.GET)
	public ModelAndView showForm(ModelMap model, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		UploadForm uploadform = new UploadForm();

		modelAndView = new ModelAndView("uploadfilepage");
		modelAndView.addObject("uploadForm", uploadform);
		modelAndView.addObject("imageAttribute",
				request.getParameter("imageAttribute"));

		return modelAndView;

	}

	@RequestMapping(value = "/imageUpload.htm", method = RequestMethod.POST)
	public String create(UploadForm uploadForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

			String attribute = request.getParameter("hiddenData");
		try {
			
			session.removeAttribute("uploadFilePath");
			session.removeAttribute("uploadFile");
			MultipartFile file = uploadForm.getFileData();
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			if (file.getSize() > 0) {
				inputStream = file.getInputStream();

				String directoryPath = request.getServletContext().getRealPath(
						IMAGE_RESOURCE_PATH);
				File dir = new File(directoryPath);
				if (dir.exists()) {
					System.out.println("A folder with name "
							+ IMAGE_RESOURCE_PATH
							+ " is already exist in the path " + directoryPath);
				} else {
					dir.mkdir();
				}

				System.out.println("size::" + file.getSize());
				fileName = directoryPath + "/" + attribute + "_" + file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + attribute);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();

					
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
	    
		return "redirect:"+ referer;
	}

}
