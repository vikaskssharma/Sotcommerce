package com.sot.ecommerce.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.nls.common.NewsLetterManagementVO;
import com.sbsc.fos.nls.web.form.AddNewsLetterForm;

/**
 * Service for NewsLetterManagement
 * 
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
public interface NewsLetterManagementService {

	/**
	 * Service Method to find Newsletter List
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<NewsLetterManagementVO> findNewsLetterList(
			SessionInfo sessionInfo);

	/**
	 * 
	 * Serivce Method to find NewsLetter List by Filters
	 * 
	 * @param filter_criteria
	 * @param sessionInfo
	 * @return
	 * @throws ParseException
	 */
	public List<NewsLetterManagementVO> findNewsLetterByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws Exception;

	/**
	 * 
	 * Service method to Edit a NewsLetter
	 * 
	 * @param sessionInfo
	 * @param editNewsLetterForm
	 * @return
	 * @throws ParseException
	 */
	public boolean editnewsLetter(SessionInfo sessionInfo,
			AddNewsLetterForm editNewsLetterForm) throws ParseException;

	/**
	 * Servie method to Add a newsLetter
	 * 
	 * 
	 * @param sessionInfo
	 * @param addNewsLetterForm
	 * @return
	 * @throws ParseException
	 */
	public boolean addnewsLetter(SessionInfo sessionInfo,
			AddNewsLetterForm addNewsLetterForm) throws ParseException;

	/**
	 * Service Metod to find a newsletter on basis of ID
	 * 
	 * @param lttrId
	 * @return
	 */
	public NewsLetterManagementVO findVOById(long lttrId);

	/**
	 * Service method to find a from by ID
	 * 
	 * @param lttrId
	 * @return
	 */
	public AddNewsLetterForm findFormById(long lttrId);

	/**
	 * 
	 * Service Method to delete a newsLetter
	 * 
	 * @param lttrId
	 * @param sessionInfo
	 * @return
	 */
	public List<NewsLetterManagementVO> deleteNewsletter(long lttrId,
			SessionInfo sessionInfo);
}
