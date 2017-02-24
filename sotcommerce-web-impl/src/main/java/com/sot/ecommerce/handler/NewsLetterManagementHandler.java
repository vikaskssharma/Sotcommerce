package com.sot.ecommerce.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.nls.common.NewsLetterManagementVO;
import com.sbsc.fos.nls.service.NewsLetterManagementService;
import com.sbsc.fos.nls.web.controller.NewsLetterManagementController;
import com.sbsc.fos.nls.web.form.AddNewsLetterForm;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.SBSConstants;

/**
 * 
 * 
 * Handler For NewsLetter Management
 * 
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Component
public class NewsLetterManagementHandler {

	@Autowired
	NewsLetterManagementService newsLetternManagementService;

	/** Logger instance. */
	private static final Logger logger = Logger
			.getLogger(NewsLetterManagementController.class);

	SimpleDateFormat view_date_format = new SimpleDateFormat(
			SBSConstants.DATE_MM_DD_YYYY);

	/**
	 * Handler Method Find newsletter list
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<NewsLetterManagementVO> findSubscriptionList(
			SessionInfo sessionInfo) {
		List<NewsLetterManagementVO> subList = new ArrayList<NewsLetterManagementVO>();

		subList = newsLetternManagementService.findNewsLetterList(sessionInfo);

		return subList;
	}

	/**
	 * 
	 * Handler method to Find news letter by filters
	 * 
	 * @param filter_criteria
	 * @param sessionInfo
	 * @return
	 * @throws Exception 
	 */
	public List<NewsLetterManagementVO> findAllNewsLetterFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws Exception {

		List<NewsLetterManagementVO> subList = new ArrayList<NewsLetterManagementVO>();
		subList = newsLetternManagementService.findNewsLetterByFilters(
				filter_criteria, sessionInfo);

		return subList;
	}

	/**
	 * 
	 * 
	 * Handler Method to add a news letter
	 * 
	 * @param sessionInfo
	 * @param addNewsLetterForm
	 * @return
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	public boolean addNewsLetter(SessionInfo sessionInfo,
			AddNewsLetterForm addNewsLetterForm)
			throws BusinessFailureException, ParseException {

		boolean check;
		Integer mode = addNewsLetterForm.getMode();

		AddNewsLetterForm newsLetterForm = publishImmidiateCheck(addNewsLetterForm);

		if (mode == 0) {
			check = newsLetternManagementService.addnewsLetter(sessionInfo,
					newsLetterForm);
		} else {
			check = newsLetternManagementService.editnewsLetter(sessionInfo,
					newsLetterForm);
		}

		return check;
	}

	/**
	 * Handler method to upload image File to Server
	 * 
	 * 
	 * @param addNewsLetterForm
	 * @return
	 * @throws IOException
	 */
	public String uploadPdfNewsLetter(AddNewsLetterForm addNewsLetterForm,
			HttpServletRequest request, SessionInfo sessionInfo)
			throws IOException {

		InputStream inputStreamForproperties = this.getClass().getClassLoader()
				.getResourceAsStream("sbsc.properties");

		MultipartFile fileData = addNewsLetterForm.getFileData();
		Properties properties = new Properties();
		properties.load(inputStreamForproperties);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		long storeId = sessionInfo.getStoreId();
		
		//Calculate directory path.
		String directoryPath = request.getServletContext().getRealPath(
				properties.getProperty("image.directory") + "/" + storeId
						+ "/newsletter");
		//Create a filename.
		String fileName = directoryPath + "\\"
				+ addNewsLetterForm.getFileData().getOriginalFilename();

		File dir = new File(directoryPath);
		if (dir.exists()) {
			logger.info("A folder with name "
					+ properties.getProperty("image.directory")
					+ " is already exist in the path " + directoryPath);
			System.out.println("A folder with name "
					+ properties.getProperty("image.directory")
					+ " is already exist in the path " + directoryPath);
		} else {
			//Make the directory if it doesn't exist.
			dir.mkdirs();
		}
		// Uploading image File.
		if (fileData.getSize() > 0) {
			inputStream = fileData.getInputStream();
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[8192];

			logger.info("writing file to output Folder : ");
			while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();

		}
		// Getting the final path of uploaded image for db.
		String db_path;
		if (StringUtils.isNotBlank(fileData.getOriginalFilename()))

		{
			db_path = properties.getProperty("image.directory") + "/" + storeId
					+ "/newsletter" + "/" + fileData.getOriginalFilename();
		} else {
			db_path = properties.getProperty("image.directory") + "/" + storeId
					+ "/newsletter" + "/" + addNewsLetterForm.getFilename();
		}
		
		return db_path;
	}

	/**
	 * Handler method to find VO by Id
	 * 
	 * @param lttrId
	 * @return
	 */
	public NewsLetterManagementVO findVOById(long lttrId) {
		NewsLetterManagementVO newsLttr = newsLetternManagementService
				.findVOById(lttrId);

		return (newsLttr);

	}

	/**
	 * Handler Method to delete newsletter
	 * 
	 * @param lttrId
	 * @return
	 */
	public List<NewsLetterManagementVO> deleteNewsLetter(long lttrId,
			SessionInfo sessionInfo) {

		List<NewsLetterManagementVO> nwsLttrVO = newsLetternManagementService
				.deleteNewsletter(lttrId, sessionInfo);

		return nwsLttrVO;
	}

	/**
	 * Handler Method to find Form by Id
	 * 
	 * 
	 * @param lttrId
	 * @return
	 */
	public AddNewsLetterForm findById(long lttrId) {
		AddNewsLetterForm newsLttr = newsLetternManagementService
				.findFormById(lttrId);

		return (newsLttr);

	}

	
	/**
	 * Handler Metho to publish a newsletter immediately.
	 * 
	 * @param addNewsLetterForm
	 * @return
	 */
	public AddNewsLetterForm publishImmidiateCheck(
			AddNewsLetterForm addNewsLetterForm) {

		String publishDate = addNewsLetterForm.getPublishDate();

		if (publishDate.equals(DateUtil.getCurrentDate())) {
			addNewsLetterForm.setStatus("Published");

		}

		else
			addNewsLetterForm.setStatus("Pending");

		return addNewsLetterForm;
	}
	
	
	

}
