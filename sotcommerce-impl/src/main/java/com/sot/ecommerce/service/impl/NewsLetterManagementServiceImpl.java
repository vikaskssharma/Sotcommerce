package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.nls.common.NewsLetterManagementVO;
import com.sbsc.fos.nls.web.controller.NewsLetterManagementController;
import com.sbsc.fos.nls.web.form.AddNewsLetterForm;
import com.sbsc.fos.persistance.NewsletterMngmntTbDtl;
import com.sot.ecommerce.um.service.StoreService;
import com.sot.ecommerce.um.service.UserService;
import com.sot.ecommerce.utils.DateUtil;
import com.sot.ecommerce.utils.SBSConstants;

/***
 * 
 * Service for newsletter management
 * 
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Service
public class NewsLetterManagementServiceImpl implements
		NewsLetterManagementService {

	private GenericDAO<NewsletterMngmntTbDtl> dao;

	private long usrID;

	private long storeID;

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	/** Logger instance. */
	private static final Logger logger = Logger
			.getLogger(NewsLetterManagementController.class);

	@Autowired
	public void setDAO(GenericDAO<NewsletterMngmntTbDtl> daoToSet) {
		logger.info("daoToSet for NewsLetterManagement" + daoToSet);
		dao = daoToSet;
		dao.setClazz(NewsletterMngmntTbDtl.class);
	}

	/**
	 * Service method to find form by Id
	 * 
	 */
	@Transactional
	public AddNewsLetterForm findFormById(long lttrId) {
		SimpleDateFormat db_date_format = new SimpleDateFormat(
				SBSConstants.DATE_MM_DD_YYYY);
		NewsletterMngmntTbDtl newsLttr = dao.findByID(lttrId);

		AddNewsLetterForm newsLttrVO = new AddNewsLetterForm();

		String path =newsLttr.getVcNwsLttrPth();
		
		String[] parts = path.split("/");

		String name = parts[parts.length - 1];//  parts[4] gives the file name from path . 

		newsLttrVO.setNewsLttrId(newsLttr.getNmNwsLttrId());
		newsLttrVO.setNwsLttrTtl(newsLttr.getNmNwsLttrTtl());
		newsLttrVO.setNwsLttrPath(path);
		newsLttrVO.setPublishDate(DateUtil.formatDate(newsLttr.getDtPblshAt(),
				db_date_format));
		newsLttrVO.setFilename(name);
		newsLttrVO.setNwsLttrDescr(newsLttr.getVcNwsLttrDscr());
		return newsLttrVO;

	}

	/**
	 * 
	 * Service method to find Vo by Id
	 * 
	 * 
	 */
	@Transactional
	public NewsLetterManagementVO findVOById(long lttrId) {

		NewsletterMngmntTbDtl newsLttr = dao.findByID(lttrId);

		NewsLetterManagementVO newsLttrVO = new NewsLetterManagementVO();

		newsLttrVO.setNewsLttrID(newsLttr.getNmNwsLttrId());
		newsLttrVO.setNewsLetterTitle(newsLttr.getNmNwsLttrTtl());
		newsLttrVO.setNewsletterImgPath(newsLttr.getVcNwsLttrPth());
		newsLttrVO.setNewsLetterDescr(newsLttr.getVcNwsLttrDscr());
		newsLttrVO.setPublishDate(newsLttr.getDtPblshAt());

		return newsLttrVO;

	}

	/**
	 * Service method to find News Letter List
	 * 
	 * @return a List<NewsLetterManagementVO>
	 */
	@Transactional
	public List<NewsLetterManagementVO> findNewsLetterList(
			SessionInfo sessionInfo) {

		List<NewsLetterManagementVO> subList = new ArrayList<NewsLetterManagementVO>();
		List<NewsletterMngmntTbDtl> detailList = null;
		

		HashMap<String, Object> userMap = getUserMap(sessionInfo);

		detailList = dao.findAllByProperty(userMap);

		for (int itr = 0; itr < detailList.size(); itr++) {
			NewsLetterManagementVO newsListVO = new NewsLetterManagementVO();

			newsListVO
					.setNewsLetterTitle(detailList.get(itr).getNmNwsLttrTtl());
			newsListVO.setPublishDate(detailList.get(itr).getDtPblshAt());
			newsListVO.setNewsLttrID(detailList.get(itr).getNmNwsLttrId());
			newsListVO.setStatus(detailList.get(itr).getVcStts());
			newsListVO.setCreatedDate(detailList.get(itr).getDtCrtdAt().getTime());
					
			
		
			subList.add(newsListVO);
		}

		return subList;

	}

	/**
	 * Service Method for Filters on News Letter List
	 * 
	 * @return a List<NewsLetterManagementVO>
	 * 
	 */
	@Transactional
	public List<NewsLetterManagementVO> findNewsLetterByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws Exception {
		List<NewsLetterManagementVO> subList = new ArrayList<NewsLetterManagementVO>();
		List<NewsletterMngmntTbDtl> detailList = null;

		storeID = sessionInfo.getStoreId();

		filter_criteria.put("filter.NewsletterMngmntTbDtl.storeTbMaster",
				storeService.findByID(storeID));
		filter_criteria.put("filter.NewsletterMngmntTbDtl.isDltd", BigDecimal.valueOf(0L));
		filter_criteria.put("desc", "dtCrtdAt");
		//filter_criteria.put("filter.NewsletterMngmntTbDtl.dtCrtdAt", BigDecimal.valueOf(0L));

		detailList = dao.findAllByFilters(filter_criteria);

		for (int itr = 0; itr < detailList.size(); itr++) {
			NewsLetterManagementVO newsListVO = new NewsLetterManagementVO();

			newsListVO
					.setNewsLetterTitle(detailList.get(itr).getNmNwsLttrTtl());
			newsListVO.setPublishDate(detailList.get(itr).getDtPblshAt());
			newsListVO.setNewsLttrID(detailList.get(itr).getNmNwsLttrId());
			newsListVO.setStatus(detailList.get(itr).getVcStts());
			newsListVO.setCreatedDate(detailList.get(itr).getDtCrtdAt().getTime());

			subList.add(newsListVO);
		}

		return subList;
	}

	/**
	 * 
	 * Service Method for adding a new NewsLetter
	 * 
	 * @return boolean
	 * @throws ParseException
	 */
	@Transactional
	public boolean addnewsLetter(SessionInfo sessionInfo,
			AddNewsLetterForm addNewsLetterForm) throws ParseException {
		storeID = sessionInfo.getStoreId();
		usrID = sessionInfo.getUserId();
		Calendar crtdAt = DateUtil.getCurrentDateTime();
		SimpleDateFormat db_date_format = new SimpleDateFormat(
				SBSConstants.DATE_MM_DD_YYYY);

		String fileName = addNewsLetterForm.getNwsLttrTtl();

		NewsletterMngmntTbDtl newsLetter = new NewsletterMngmntTbDtl();

		newsLetter.setStoreTbMaster(storeService.findByID(storeID));
		newsLetter.setDtCrtdAt(crtdAt);
		newsLetter.setDtUpdtdAt(crtdAt);
		newsLetter.setNmCrtdBy(new BigDecimal(usrID));
		newsLetter.setNmUpdtdBy(new BigDecimal(usrID));
		newsLetter.setNmNwsLttrTtl(fileName.trim());
		newsLetter.setVcNwsLttrDscr(addNewsLetterForm.getNwsLttrDescr().trim());
		newsLetter.setVcNwsLttrPth(addNewsLetterForm.getNwsLttrPath());
		newsLetter.setDtPblshAt(db_date_format.parse(addNewsLetterForm
				.getPublishDate()));
		newsLetter.setVcStts(addNewsLetterForm.getStatus());
		newsLetter.setIsDltd(new BigDecimal(0));

		dao.saveOrUpdate(newsLetter);

		return true;
	}

	/**
	 * 
	 * Edit News Letter
	 * 
	 * @throws ParseException
	 * 
	 * 
	 */
	@Transactional
	public boolean editnewsLetter(SessionInfo sessionInfo,
			AddNewsLetterForm editNewsLetterForm) throws ParseException {
		storeID = sessionInfo.getStoreId();
		usrID = sessionInfo.getUserId();
		Calendar crtdAt = DateUtil.getCurrentDateTime();
		SimpleDateFormat db_date_format = new SimpleDateFormat(
				SBSConstants.DATE_MM_DD_YYYY);

		String fileName = editNewsLetterForm.getNwsLttrTtl();

		NewsletterMngmntTbDtl newsLetter = new NewsletterMngmntTbDtl();

		newsLetter.setNmNwsLttrId(editNewsLetterForm.getNewsLttrId());
		newsLetter.setStoreTbMaster(storeService.findByID(storeID));
		newsLetter.setDtCrtdAt(crtdAt);
		newsLetter.setDtUpdtdAt(crtdAt);
		newsLetter.setNmCrtdBy(new BigDecimal(usrID));
		newsLetter.setNmUpdtdBy(new BigDecimal(usrID));
		newsLetter.setNmNwsLttrTtl(fileName);
		newsLetter.setVcNwsLttrDscr(editNewsLetterForm.getNwsLttrDescr());
		newsLetter.setVcNwsLttrPth(editNewsLetterForm.getNwsLttrPath());
		newsLetter.setDtPblshAt(db_date_format.parse(editNewsLetterForm
				.getPublishDate()));
		newsLetter.setVcStts(editNewsLetterForm.getStatus());
		newsLetter.setIsDltd(new BigDecimal(0));
		newsLetter.setNmNwsLttrId(editNewsLetterForm.getNewsLttrId());

		dao.saveOrUpdate(newsLetter);

		return true;
	}
	
	

	/**
	 * 
	 * Service Method to delete a Newsletter
	 * 
	 */
	@Transactional
	public List<NewsLetterManagementVO> deleteNewsletter(long lttrId,
			SessionInfo sessionInfo) {

		NewsletterMngmntTbDtl nwsLetterDel = dao.findByID(lttrId);
		nwsLetterDel.setIsDltd(new BigDecimal("1"));
		dao.update(nwsLetterDel);

		List<NewsLetterManagementVO> nwsLttrVO = findNewsLetterList(sessionInfo);
		return nwsLttrVO;
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
		userMap.put("storeTbMaster", storeService.findByID(storeID));
		userMap.put("desc", "dtCrtdAt");
		userMap.put("isDltd", BigDecimal.valueOf(0L));

		return userMap;
	}

}
