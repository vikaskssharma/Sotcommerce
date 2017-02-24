package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.activemq.command.SessionInfo;
import org.apache.commons.httpclient.util.DateUtil;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sot.ecommerce.dao.GenericDAO;
import com.sot.ecommerce.entitities.BannerTbMaster;
import com.sot.ecommerce.service.BannerService;
import com.sot.ecommerce.service.CategoryService;
import com.sot.ecommerce.service.StoreService;
import com.sot.ecommerce.vo.CategoryPlaceHolderTreeVO;


@Service
public class BannerServiceImpl implements BannerService {

	/** Logger instance. **/
	private static final Logger logger = LoggerFactory
			.getLogger(BannerServiceImpl.class);

	/** The Generic DAO instantiated for BannerTbMaster */
	private GenericDAO<BannerTbMaster> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	@Autowired
	private StoreService storeService;

	@Autowired
	private CategoryService categoryService;

	private long storeID;

	/** Instantiating a copy of Generic DAO for BannerTbMaster */
	@Autowired
	public void setDAO(GenericDAO<BannerTbMaster> daoToSet) {
		dao = daoToSet;
		dao.setClazz(BannerTbMaster.class);
	}

	@Transactional
	@Override
	public Long create(BannerTbMaster bannerTbMaster) {
		try {
			return dao.create(bannerTbMaster);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Banner with name - %s for Store with Id - %s",
							bannerTbMaster.getVcBnrNm(), bannerTbMaster
									.getStoreTbMaster().getNmStrId()), hexp);
		} finally {

		}
		System.out.println("success banner");
		return null;
	}

	@Transactional
	@Override
	public List<BannerTbMaster> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all Cateegories from System ",
					hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public BannerTbMaster findByID(long bannerId) {
		try {
			return dao.findByID(bannerId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner with id - %s",
					String.valueOf(bannerId)), hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public List<BannerTbMaster> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner having"
							+ " property name- %s and property value - %s",
					propertyName, value.toString()), hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public List<BannerTbMaster> findAllByProperty(
			HashMap<String, Object> propertiesMap) {
		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	@Override
	@Transactional
	public List<BannerVO> findAllBannerByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo) {

		List<BannerVO> detailList = new ArrayList<BannerVO>();
		List<BannerTbMaster> bannerList = null;
		try {
			storeID = sessionInfo.getStoreId();

			filter_criteria.put("filter.BannerTbMaster.storeTbMaster.nmStrId",
					storeID);
			filter_criteria.put("filter.BannerTbMaster.isDltd",
					BigDecimal.valueOf(0));
			bannerList = dao.findAllByFilters(filter_criteria);

			int count = 0;

			for (BannerTbMaster bannerTbMaster : bannerList) {
				BannerVO bannerVO = new BannerVO();

				bannerVO.setBannerId(bannerTbMaster.getNmBnrId());
				
				bannerVO.setBannerActiveFrom(bannerTbMaster.getDtBnrActvtdFrm().getTime());
				bannerVO.setBannerActiveTo(bannerTbMaster.getDtBnrActvtdTo().getTime());
				
				if (bannerTbMaster.getCategoryTbMaster() != null)
					bannerVO.setBannerCategory(bannerTbMaster
							.getCategoryTbMaster().getVcCtgryNm());

				bannerVO.setBannerName(bannerTbMaster.getVcBnrNm());
				bannerVO.setBannerPage(bannerTbMaster.getVcBnrPg());
				bannerVO.setBannerPosition(bannerTbMaster.getVcPgPstn());
				bannerVO.setBannerStatus(bannerTbMaster.getVcStts().toString());
				
				bannerVO.setIsFromDateDisabled(!DateUtil.isGTCurrentDate(bannerTbMaster.getDtBnrActvtdFrm().getTime()));
				bannerVO.setIsToDateDisabled(!DateUtil.isGTCurrentDate(bannerTbMaster.getDtBnrActvtdTo().getTime()));
				
				bannerVO.setBannerCreatedAt(bannerTbMaster.getDtCrtdAt().getTime());

				detailList.add(count, bannerVO);
				count++;
			}

		} catch (Exception e) {
			logger.error(String.format("Error occured while fetching banners "
					+ e.getMessage()));
			e.printStackTrace();
		}

		return detailList;
	}

	@Transactional
	@Override
	public BannerTbMaster update(BannerTbMaster bannerTbMaster) {
		try {
			return dao.update(bannerTbMaster);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while updating a Category with"
							+ " name - %s, Id - %s for Store with Id - %s",
					bannerTbMaster.getVcBnrNm(), bannerTbMaster.getNmBnrId(),
					bannerTbMaster.getStoreTbMaster().getNmStrId()), hexp);
		} finally {

		}
		return null;
	}

	/**
	 * Soft-deletes the Category and its child Elements recursively if there is
	 * no product exists in any level of that Category.
	 * 
	 * @param categoryTbMasterToDelete
	 *            The Category to be deleted.
	 */
	@Transactional
	@Override
	public void delete(BannerTbMaster bannerTbMaster) {
		try {
			BannerTbMaster bannerTbMasterToDelete = dao.findByID(bannerTbMaster
					.getNmBnrId());

			bannerTbMasterToDelete.setIsDltd(new BigDecimal("1"));

			dao.update(bannerTbMasterToDelete);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while deleting a Banner with id - %s",
					String.valueOf(bannerTbMaster.getNmBnrId())), hexp);
		} finally {

		}
	}

	@Transactional
	public boolean deleteById(long bannerId) {

		BannerTbMaster banner = dao.findByID(bannerId);
		banner.setIsDltd(BigDecimal.valueOf(1));
		try {
			dao.saveOrUpdate(banner);
		} catch (HibernateException e) {
			logger.error(String
					.format("Error occured while soft deleting banner. Actual Error :"
							+ e.getMessage()));
			e.printStackTrace();
		}
		return true;
	}

	@Transactional
	@Override
	public void saveOrUpdate(BannerTbMaster bannerTbMaster) {
		try {
			dao.saveOrUpdate(bannerTbMaster);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a Category having"
							+ " name - %s for Store with Id - %s",
					bannerTbMaster.getVcBnrNm(), bannerTbMaster
							.getStoreTbMaster().getNmStrId()), hexp);
		} finally {

		}
	}

	@Transactional
	@Override
	public List<String> getAllPages() {
		return sqlDAO.findAllPages();
	}

	@Transactional
	@Override
	public List<String> getAllPagePosition() {
		return sqlDAO.findAllPagePosition();
	}

	@Override
	@Transactional
	public List<BannerVO> findBanner(SessionInfo sessionInfo) {
		List<BannerVO> detailList = new ArrayList<BannerVO>();
		List<BannerTbMaster> bannerList = null;
		try {
			HashMap<String, Object> propertiesMap = getUserMap(sessionInfo);
			propertiesMap.put("isDltd", BigDecimal.valueOf(0));
			bannerList = dao.findAllByProperty(propertiesMap);
			int count = 0;

			for (BannerTbMaster bannerTbMaster : bannerList) {
				BannerVO bannerVO = new BannerVO();

				bannerVO.setBannerId(bannerTbMaster.getNmBnrId());

				bannerVO.setBannerActiveFrom(bannerTbMaster.getDtBnrActvtdFrm()
						.getTime());

				bannerVO.setBannerActiveTo(bannerTbMaster.getDtBnrActvtdTo()
						.getTime());

				bannerVO.setBannerCreatedAt(bannerTbMaster.getDtCrtdAt()
						.getTime());

				if (bannerTbMaster.getCategoryTbMaster() != null)
					bannerVO.setBannerCategory(bannerTbMaster
							.getCategoryTbMaster().getVcCtgryNm());
				bannerVO.setBannerName(bannerTbMaster.getVcBnrNm());
				bannerVO.setBannerPage(bannerTbMaster.getVcBnrPg());
				bannerVO.setBannerPosition(bannerTbMaster.getVcPgPstn());
				bannerVO.setBannerStatus(bannerTbMaster.getVcStts().toString());
				
				bannerVO.setIsFromDateDisabled(!DateUtil.isGTCurrentDate(bannerTbMaster.getDtBnrActvtdFrm().getTime()));
				bannerVO.setIsToDateDisabled(!DateUtil.isGTCurrentDate(bannerTbMaster.getDtBnrActvtdTo().getTime()));
				
				detailList.add(count, bannerVO);
				count++;
			}

		} catch (Exception e) {
			logger.error(String.format("Error occured while fetching banners "
					+ e.getMessage()));
			e.printStackTrace();
		}

		return detailList;
	}

	public HashMap<String, Object> getUserMap(SessionInfo sessionInfo) {
		storeID = sessionInfo.getStoreId();
		HashMap<String, Object> userMap = new HashMap<>();
		userMap.put("storeTbMaster", storeService.findByID(storeID));
		return userMap;
	}

	@Override
	@Transactional
	public List<CategoryPlaceHolderTreeVO> getAllCategories(long storeId)
			throws ParseException {
		/*HashMap<String, Object> categoryMap = new HashMap<>();
		List<CategoryAjaxForm> categoryList = new ArrayList<>();
		categoryMap.put("storeTbMaster", storeService.findByID(storeId));
		categoryMap.put("isDltd", BigDecimal.valueOf(0L));

		for (CategoryTbMaster categoryTbMaster : categoryService
				.findAllByProperty(categoryMap)) {
			CategoryAjaxForm categoryAjaxForm = new CategoryAjaxForm();
			categoryAjaxForm.setCategoryID(categoryTbMaster.getNmCtgryId());
			categoryAjaxForm.setCategoryName(categoryTbMaster.getVcCtgryNm());
			categoryList.add(categoryAjaxForm);
		}
		return categoryList;
	}*/
		try {
			return sqlDAO.getCategoryHierarchyTree(storeId);
		} catch (HibernateException hexp) {
			logger.error(String.format("Error occured while reading a Category"
					+ " Place holder Tree for Store with Id - %s", storeId),
					hexp);
		} finally {

		}
		return null;
	}
}
