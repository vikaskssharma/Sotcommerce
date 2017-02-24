/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.dao.SQLDAO;
import com.sbsc.fos.persistance.BannerLinkDetail;
import com.sbsc.fos.persistance.BannerTbMaster;

/**
 * @author vaibhav.jain
 *
 */
@Service
public class BannerLinkServiceImpl implements BannerLinkService {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(BannerLinkServiceImpl.class);

	/** The Generic DAO instantiated for BannerTbMaster */
	private GenericDAO<BannerLinkDetail> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	/** Instantiating a copy of Generic DAO for BannerTbMaster */
	@Autowired
	public void setDAO(GenericDAO<BannerLinkDetail> daoToSet) {
		dao = daoToSet;
		dao.setClazz(BannerLinkDetail.class);
	}


	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#create(com.sbsc.fos.persistance.BannerTbMaster)
	 */
	@Transactional
	@Override
	public Long create(BannerLinkDetail bannerLinkDetail) {
		try {
			return dao.create(bannerLinkDetail);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Banner Link with name - %s",
							bannerLinkDetail.getVcBnrImgPath(), hexp));
		} finally {

		}
		System.out.println("success banner");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#findAll()
	 */
	@Transactional
	@Override
	public List<BannerLinkDetail> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all Banner Link from System ",
					hexp);
		} finally {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#findByID(long)
	 */
	@Transactional
	@Override
	public BannerLinkDetail findByID(long bannerLinkId) {
		try {
			return dao.findByID(bannerLinkId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner Link with id - %s",
					String.valueOf(bannerLinkId)), hexp);
		} finally {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#findAllByProperty(java.lang.String, java.lang.Object)
	 */
	@Transactional
	@Override
	public List<BannerLinkDetail> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner Link having"
							+ " property name- %s and property value - %s",
					propertyName, value.toString()), hexp);
		} finally {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#findAllByProperty(java.util.HashMap)
	 */
	@Transactional
	@Override
	public List<BannerLinkDetail> findAllByProperty(
			Map<String, Object> propertiesMap) {
		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Banner Link having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#update(com.sbsc.fos.persistance.BannerTbMaster)
	 */
	@Transactional
	@Override
	public BannerLinkDetail update(BannerLinkDetail bannerLinkDetail) {
		try {
			return dao.update(bannerLinkDetail);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while updating a Banner Link with"
							+ " name - %s, Id - %s",
							bannerLinkDetail.getVcBnrImgPath(), bannerLinkDetail
							.getNmBnrLnkId(), hexp));
		} finally {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#delete(com.sbsc.fos.persistance.BannerTbMaster)
	 */
	@Transactional
	@Override
	public void delete(BannerLinkDetail bannerLinkDetail) {
		try {
			BannerLinkDetail bannerLinkTbMasterToDelete = dao
					.findByID(bannerLinkDetail.getNmBnrLnkId());

			bannerLinkTbMasterToDelete.setIsDltd(new BigDecimal("1"));

			dao.update(bannerLinkTbMasterToDelete);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while deleting a Banner Link with id - %s",
					String.valueOf(bannerLinkDetail.getNmBnrLnkId()), hexp));
		} finally {

		}	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#deleteById(long)
	 */
	@Transactional
	@Override
	public void deleteById(long bannerLinkDetailId) {
		BannerLinkDetail bannerLinkTbMasterToDelete = null;
		try {
			bannerLinkTbMasterToDelete = dao.findByID(bannerLinkDetailId);

			bannerLinkTbMasterToDelete.setIsDltd(new BigDecimal("1"));

			// dao.update(categoryTbMasterToDelete);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while deleting a Banner Link with id - %s",
					String.valueOf(bannerLinkDetailId)), hexp);
		} finally {

		}	}

	/* (non-Javadoc)
	 * @see com.sbsc.fos.banner.web.service.BannerLinkService#saveOrUpdate(com.sbsc.fos.persistance.BannerTbMaster)
	 */
	@Transactional
	@Override
	public void saveOrUpdate(BannerLinkDetail bannerLinkDetail) {
		try {
			dao.saveOrUpdate(bannerLinkDetail);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a Banner Link having"
							+ " name - %s", bannerLinkDetail
							.getVcBnrImgPath(), hexp));
		} finally {

		}	}

}
