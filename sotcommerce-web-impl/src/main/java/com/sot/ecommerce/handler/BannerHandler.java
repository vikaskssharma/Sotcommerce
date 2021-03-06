/**
 * 
 */
package com.sot.ecommerce.handler;

//      com.sbsc.fos.banner.web.handler.BannerHandler

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.banner.common.BannerVO;
import com.sbsc.fos.banner.web.form.AddBannerForm;
import com.sbsc.fos.banner.web.service.BannerLinkService;
import com.sbsc.fos.banner.web.service.BannerService;
import com.sbsc.fos.category.commons.CategoryPlaceHolderTreeVO;
import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.BannerLinkDetail;
import com.sbsc.fos.persistance.BannerTbMaster;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.SBSConstants;

/**
 * @author vaibhav.jain
 * 
 */
@Component
public class BannerHandler {
	/** Logger instance. **/
	private static final Logger logger = Logger.getLogger(BannerHandler.class);

	SessionInfo sessionInfo;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private BannerLinkService bannerLinkService;

	@Autowired
	private CategoryService categoryService;

	public BannerService getBannerService() {
		return bannerService;
	}

	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}

	public List<BannerVO> allBannerHandle(SessionInfo sessionInfo) {
		List<BannerVO> bannerList = bannerService.findBanner(sessionInfo);
		return bannerList;
	}

	public boolean deleteBanner(Long bannerId) {
		if (bannerService.deleteById(bannerId)) {
			return true;
		} else {
			return false;
		}
	}

	public List<BannerVO> findAllBannersByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo) {

		List<BannerVO> bannerList = bannerService.findAllBannerByFilters(
				filter_criteria, sessionInfo);
		return bannerList;
	}

	public List<BannerVO> bannerHandle(SessionInfo sessionInfo) {
		return bannerService.findBanner(sessionInfo);
	}

	/**
	 * Create New Banner.
	 * 
	 * @param AddBannerForm
	 * @param SessionInfo
	 * @return Long
	 * @throws IOException
	 */
	public Long createNewBanner(AddBannerForm addBannerForm,
			SessionInfo sessionInfo, HttpServletRequest request)
			throws BusinessFailureException, GenericFailureException,
			ParseException, IOException {

		InputStream inputStreamForproperties = this.getClass().getClassLoader()
				.getResourceAsStream("sbsc.properties");

		Properties properties = new Properties();

		properties.load(inputStreamForproperties);

		BannerTbMaster newBanner = new BannerTbMaster();

		StoreTbMaster storeTbMaster = new StoreTbMaster();

		storeTbMaster.setNmStrId(sessionInfo.getStoreId());

		newBanner.setStoreTbMaster(storeTbMaster);

		newBanner.setVcBnrNm(addBannerForm.getBannerName());

		newBanner.setVcBnrDscr(addBannerForm.getBannerDescription());

		newBanner.setChAply(addBannerForm.getBannerApplyTo());

		if (addBannerForm.getBannerApplyTo().equals("C"))

			newBanner.setCategoryTbMaster(categoryService.findByID(Long
					.valueOf(addBannerForm.getBannerCategoryId())));

		else {
			newBanner.setVcBnrPg(addBannerForm.getBannerPageName());

			newBanner.setVcPgPstn(addBannerForm.getBannerPagePositionName());
		}

		newBanner.setVcStts(SBSConstants.BANNER_STATUS.Active.name());

		newBanner.setDtBnrActvtdFrm(DateUtil.getDbFormatDateTime(addBannerForm
				.getFromDate()));

		newBanner.setDtBnrActvtdTo(DateUtil.getDbFormatDateTime(addBannerForm
				.getToDate()));
		
		newBanner.setDtCrtdAt(DateUtil.getCurrentDateTime());

		newBanner.setDtUpdtdAt(DateUtil.getCurrentDateTime());

		newBanner.setNmCrtdBy(new BigDecimal(sessionInfo.getUserId()));

		newBanner.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));

		newBanner.setIsDltd(new BigDecimal(0));

		long bannerId = bannerService.create(newBanner);

		// Start Adding Banner links -

		// Image 1
		if (!addBannerForm.getProdImgPath1().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath1();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));

			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg1(), addBannerForm.getProductImg1(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Image 2
		if (!addBannerForm.getProdImgPath2().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath2();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));

			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg2(), addBannerForm.getProductImg2(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Image 3
		if (!addBannerForm.getProdImgPath3().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath3();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));

			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg3(), addBannerForm.getProductImg3(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Image 4
		if (!addBannerForm.getProdImgPath4().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath4();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));

			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg4(), addBannerForm.getProductImg4(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Image 5
		if (!addBannerForm.getProdImgPath5().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath5();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));

			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg5(), addBannerForm.getProductImg5(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		return null;
	}

	/**
	 * populate AddBannerForm from BannerTbMaster object.
	 * 
	 * @param addBannerForm
	 * @return populated addBannerForm from BannerTbMaster object.
	 * @throws ParseException
	 */
	public AddBannerForm setBannerVO(long bannerId, SessionInfo sessionInfo)
			throws BusinessFailureException, GenericFailureException,
			ParseException {
		AddBannerForm addBannerForm = new AddBannerForm();

		BannerTbMaster banner = bannerService.findByID(bannerId);

		addBannerForm.setBannerId(bannerId);

		addBannerForm.setBannerName(banner.getVcBnrNm());

		addBannerForm.setBannerDescription(banner.getVcBnrDscr());

		addBannerForm.setBannerApplyTo(banner.getChAply());

		if (banner.getCategoryTbMaster() != null)
			addBannerForm.setBannerCategoryId(String.valueOf(banner
					.getCategoryTbMaster().getNmCtgryId()));
		else
			addBannerForm.setBannerCategoryId("0"); //For All Category

		addBannerForm.setBannerPageName(banner.getVcBnrPg());

		addBannerForm.setBannerPagePositionName(banner.getVcPgPstn());

		addBannerForm.setStatus(banner.getVcStts());

		addBannerForm.setFromDate(DateUtil.getFormattedDateWithTime(banner
				.getDtBnrActvtdFrm()));

		addBannerForm.setToDate(DateUtil.getFormattedDateWithTime(banner
				.getDtBnrActvtdTo()));
		
		addBannerForm.setIsFromDateDisabled(!DateUtil.isGTCurrentDate(banner.getDtBnrActvtdFrm().getTime()));
		
		addBannerForm.setIsToDateDisabled(!DateUtil.isGTCurrentDate(banner.getDtBnrActvtdTo().getTime()));
		

		Map<String, Object> properties = new HashMap<String, Object>();

		properties.put("bannerTbMaster", bannerService.findByID(bannerId));

		properties.put("isDltd", BigDecimal.valueOf(0));

		List<BannerLinkDetail> bannerLinkDetails = bannerLinkService
				.findAllByProperty(properties);

		BannerLinkDetail bannerLinkDetail1 = null, bannerLinkDetail2 = null, bannerLinkDetail3 = null, bannerLinkDetail4 = null, bannerLinkDetail5 = null;

		if (bannerLinkDetails.size() >= 1) {
			bannerLinkDetail1 = bannerLinkDetails.get(0);
			if (bannerLinkDetail1.getIsDltd().toString().equals("0")) {
				addBannerForm.setProdImgPath1(bannerLinkDetail1
						.getVcBnrImgPath());
				addBannerForm.setCategoryImg1(bannerLinkDetail1
						.getBnnrLnkCtgryId());
				addBannerForm.setProductImg1(bannerLinkDetail1
						.getBnnrLnkPrdId());
				addBannerForm.setBnrLnkId1(bannerLinkDetail1.getNmBnrLnkId());
			}

		}

		if (bannerLinkDetails.size() >= 2) {
			bannerLinkDetail2 = bannerLinkDetails.get(1);
			if (bannerLinkDetail2.getIsDltd().toString().equals("0")) {
				addBannerForm.setProdImgPath2(bannerLinkDetail2
						.getVcBnrImgPath());
				addBannerForm.setCategoryImg2(bannerLinkDetail2
						.getBnnrLnkCtgryId());
				addBannerForm.setProductImg2(bannerLinkDetail2
						.getBnnrLnkPrdId());
				addBannerForm.setBnrLnkId2(bannerLinkDetail2.getNmBnrLnkId());
			}
		}

		if (bannerLinkDetails.size() >= 3) {
			bannerLinkDetail3 = bannerLinkDetails.get(2);
			if (bannerLinkDetail3.getIsDltd().toString().equals("0")) {
				addBannerForm.setProdImgPath3(bannerLinkDetail3
						.getVcBnrImgPath());
				addBannerForm.setCategoryImg3(bannerLinkDetail3
						.getBnnrLnkCtgryId());
				addBannerForm.setProductImg3(bannerLinkDetail3
						.getBnnrLnkPrdId());
				addBannerForm.setBnrLnkId3(bannerLinkDetail3.getNmBnrLnkId());
			}
		}

		if (bannerLinkDetails.size() >= 4) {
			bannerLinkDetail4 = bannerLinkDetails.get(3);
			if (bannerLinkDetail4.getIsDltd().toString().equals("0")) {
				addBannerForm.setProdImgPath4(bannerLinkDetail4
						.getVcBnrImgPath());
				addBannerForm.setCategoryImg4(bannerLinkDetail4
						.getBnnrLnkCtgryId());
				addBannerForm.setProductImg4(bannerLinkDetail4
						.getBnnrLnkPrdId());
				addBannerForm.setBnrLnkId4(bannerLinkDetail4.getNmBnrLnkId());
			}
		}

		if (bannerLinkDetails.size() >= 5) {
			bannerLinkDetail5 = bannerLinkDetails.get(4);
			if (bannerLinkDetail5.getIsDltd().toString().equals("0")) {
				addBannerForm.setProdImgPath5(bannerLinkDetail5
						.getVcBnrImgPath());
				addBannerForm.setCategoryImg5(bannerLinkDetail5
						.getBnnrLnkCtgryId());
				addBannerForm.setProductImg5(bannerLinkDetail5
						.getBnnrLnkPrdId());
				addBannerForm.setBnrLnkId5(bannerLinkDetail5.getNmBnrLnkId());
			}
		}

		return addBannerForm;
	}

	/**
	 * Edit existing Banner.
	 * 
	 * @param addBannerForm
	 * @param SessionInfo
	 * @throws ParseException
	 */
	public void updateBanner(AddBannerForm addBannerForm,
			SessionInfo sessionInfo, HttpServletRequest request)
			throws ParseException {

		BannerTbMaster bannerToUpdate = bannerService.findByID(addBannerForm
				.getBannerId());

		// Update Banner Name
		bannerToUpdate.setVcBnrNm(addBannerForm.getBannerName());

		// Update Banner Description
		bannerToUpdate.setVcBnrDscr(addBannerForm.getBannerDescription());
		
		// Update Banner Apply To
		bannerToUpdate.setChAply(addBannerForm.getBannerApplyTo());

		if (addBannerForm.getBannerApplyTo().equals("C")){
			// Update Banner Category
			bannerToUpdate.setCategoryTbMaster(categoryService.findByID(Long
					.valueOf(addBannerForm.getBannerCategoryId())));
			bannerToUpdate.setVcBnrPg("");
			bannerToUpdate.setVcPgPstn("");
		}
		else {
			// Update Banner page
			bannerToUpdate.setVcBnrPg(addBannerForm.getBannerPageName());

			// Update Banner Page position
			bannerToUpdate.setVcPgPstn(addBannerForm
					.getBannerPagePositionName());
			bannerToUpdate.setCategoryTbMaster(null);
		}

		// Update Status
		bannerToUpdate.setVcStts(addBannerForm.getStatus());

		// Update From Date
		bannerToUpdate.setDtBnrActvtdFrm(DateUtil
				.getDbFormatDateTime(addBannerForm.getFromDate()));

		// Update To Date
		bannerToUpdate.setDtBnrActvtdTo(DateUtil
				.getDbFormatDateTime(addBannerForm.getToDate()));
		
		
		// Update the Updated Date
		bannerToUpdate.setDtUpdtdAt(DateUtil.getCurrentDateTime());

		// Update the User, who updated this promotion
		bannerToUpdate.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));

		bannerService.saveOrUpdate(bannerToUpdate);

		// Start Updating Banner links -

		long bannerId = addBannerForm.getBannerId();

		Map<String, Object> properties = new HashMap<String, Object>();

		properties.put("bannerTbMaster", bannerService.findByID(bannerId));

		properties.put("isDltd", BigDecimal.valueOf(0));

		List<BannerLinkDetail> bannerLinkDetails = bannerLinkService
				.findAllByProperty(properties);

		// Sorting List in reverse Order
		Collections.reverse(bannerLinkDetails);

		BannerLinkDetail bannerLinkDetail1 = null, bannerLinkDetail2 = null, bannerLinkDetail3 = null, bannerLinkDetail4 = null, bannerLinkDetail5 = null;

		if (bannerLinkDetails.size() >= 1)
			bannerLinkDetail1 = bannerLinkService.findAllByProperty(
					"nmBnrLnkId", addBannerForm.getBnrLnkId1()).get(0);

		if (bannerLinkDetails.size() >= 2)
			bannerLinkDetail2 = bannerLinkService.findAllByProperty(
					"nmBnrLnkId", addBannerForm.getBnrLnkId2()).get(0);

		if (bannerLinkDetails.size() >= 3)
			bannerLinkDetail3 = bannerLinkService.findAllByProperty(
					"nmBnrLnkId", addBannerForm.getBnrLnkId3()).get(0);

		if (bannerLinkDetails.size() >= 4)
			bannerLinkDetail4 = bannerLinkService.findAllByProperty(
					"nmBnrLnkId", addBannerForm.getBnrLnkId4()).get(0);

		if (bannerLinkDetails.size() >= 5)
			bannerLinkDetail5 = bannerLinkService.findAllByProperty(
					"nmBnrLnkId", addBannerForm.getBnrLnkId5()).get(0);

		List<String> pathnames = new ArrayList<>();
		for (BannerLinkDetail bannerLinkDetail : bannerLinkDetails) {
			pathnames.add(bannerLinkDetail.getVcBnrImgPath());
		}

		for (BannerLinkDetail bannerLinkDetail : bannerLinkDetails) {
			// Image 1
			if (bannerLinkDetail.getVcBnrImgPath().equals(
					addBannerForm.getProdImgPath1())) {
				// Update banner Image
				bannerLinkDetail = bannerLinkService.findAllByProperty(
						"vcBnrImgPath", addBannerForm.getProdImgPath1()).get(0);
				bannerLinkDetail.setBnnrLnkCtgryId(addBannerForm
						.getCategoryImg1());
				bannerLinkDetail
						.setBnnrLnkPrdId(addBannerForm.getProductImg1());
				bannerLinkDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
				bannerLinkDetail.setIsDltd(new BigDecimal(0));
				bannerLinkDetail.setNmUpdtdBy(new BigDecimal(sessionInfo
						.getUserId()));
				bannerLinkDetail.setVcBnrImgPath(addBannerForm
						.getProdImgPath1());

				bannerLinkService.saveOrUpdate(bannerLinkDetail);
				pathnames.remove(addBannerForm.getProdImgPath1());
				addBannerForm.setProdImgPath1("");
			}

			// Image 2
			if (bannerLinkDetail.getVcBnrImgPath().equals(
					addBannerForm.getProdImgPath2())) {
				// Update banner Image
				bannerLinkDetail = bannerLinkService.findAllByProperty(
						"vcBnrImgPath", addBannerForm.getProdImgPath2()).get(0);
				bannerLinkDetail.setBnnrLnkCtgryId(addBannerForm
						.getCategoryImg2());
				bannerLinkDetail
						.setBnnrLnkPrdId(addBannerForm.getProductImg2());
				bannerLinkDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
				bannerLinkDetail.setIsDltd(new BigDecimal(0));
				bannerLinkDetail.setNmUpdtdBy(new BigDecimal(sessionInfo
						.getUserId()));
				bannerLinkDetail.setVcBnrImgPath(addBannerForm
						.getProdImgPath2());

				bannerLinkService.saveOrUpdate(bannerLinkDetail);
				pathnames.remove(addBannerForm.getProdImgPath2());
				addBannerForm.setProdImgPath2("");
			}

			// Image 3
			if (bannerLinkDetail.getVcBnrImgPath().equals(
					addBannerForm.getProdImgPath3())) {
				// Update banner Image
				bannerLinkDetail = bannerLinkService.findAllByProperty(
						"vcBnrImgPath", addBannerForm.getProdImgPath3()).get(0);
				bannerLinkDetail.setBnnrLnkCtgryId(addBannerForm
						.getCategoryImg3());
				bannerLinkDetail
						.setBnnrLnkPrdId(addBannerForm.getProductImg3());
				bannerLinkDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
				bannerLinkDetail.setIsDltd(new BigDecimal(0));
				bannerLinkDetail.setNmUpdtdBy(new BigDecimal(sessionInfo
						.getUserId()));
				bannerLinkDetail.setVcBnrImgPath(addBannerForm
						.getProdImgPath3());

				bannerLinkService.saveOrUpdate(bannerLinkDetail);
				pathnames.remove(addBannerForm.getProdImgPath3());
				addBannerForm.setProdImgPath3("");
			}

			// Image 4
			if (bannerLinkDetail.getVcBnrImgPath().equals(
					addBannerForm.getProdImgPath4())) {
				// Update banner Image
				bannerLinkDetail = bannerLinkService.findAllByProperty(
						"vcBnrImgPath", addBannerForm.getProdImgPath4()).get(0);
				bannerLinkDetail.setBnnrLnkCtgryId(addBannerForm
						.getCategoryImg4());
				bannerLinkDetail
						.setBnnrLnkPrdId(addBannerForm.getProductImg4());
				bannerLinkDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
				bannerLinkDetail.setIsDltd(new BigDecimal(0));
				bannerLinkDetail.setNmUpdtdBy(new BigDecimal(sessionInfo
						.getUserId()));
				bannerLinkDetail.setVcBnrImgPath(addBannerForm
						.getProdImgPath4());

				bannerLinkService.saveOrUpdate(bannerLinkDetail);
				pathnames.remove(addBannerForm.getProdImgPath4());
				addBannerForm.setProdImgPath4("");
			}

			// Image 5
			if (bannerLinkDetail.getVcBnrImgPath().equals(
					addBannerForm.getProdImgPath5())) {
				// Update banner Image
				bannerLinkDetail = bannerLinkService.findAllByProperty(
						"vcBnrImgPath", addBannerForm.getProdImgPath5()).get(0);
				bannerLinkDetail.setBnnrLnkCtgryId(addBannerForm
						.getCategoryImg5());
				bannerLinkDetail
						.setBnnrLnkPrdId(addBannerForm.getProductImg5());
				bannerLinkDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
				bannerLinkDetail.setIsDltd(new BigDecimal(0));
				bannerLinkDetail.setNmUpdtdBy(new BigDecimal(sessionInfo
						.getUserId()));
				bannerLinkDetail.setVcBnrImgPath(addBannerForm
						.getProdImgPath5());

				bannerLinkService.saveOrUpdate(bannerLinkDetail);
				pathnames.remove(addBannerForm.getProdImgPath5());
				addBannerForm.setProdImgPath5("");
			}
		}

		// Deleting Banner Images
		for (String pathname : pathnames) {
			bannerLinkService.delete(bannerLinkService.findAllByProperty(
					"vcBnrImgPath", pathname).get(0));
		}

		// Add New Image1
		if (!addBannerForm.getProdImgPath1().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath1();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));
			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg1(), addBannerForm.getProductImg1(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Add New Image2
		if (!addBannerForm.getProdImgPath2().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath2();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));
			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg2(), addBannerForm.getProductImg2(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Add New Image3
		if (!addBannerForm.getProdImgPath3().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath3();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));
			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg3(), addBannerForm.getProductImg3(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Add New Image4
		if (!addBannerForm.getProdImgPath4().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath4();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));
			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg4(), addBannerForm.getProductImg4(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}

		// Add New Image5
		if (!addBannerForm.getProdImgPath5().isEmpty()) {
			String origImgpath = addBannerForm.getProdImgPath5();
			String fileimg = request.getServletContext().getRealPath(
					origImgpath);
			File file = new File(fileimg);
			String imgPath = origImgpath.substring(0,
					origImgpath.lastIndexOf("/") + 1)
					+ bannerId
					+ "_"
					+ origImgpath.substring(origImgpath.lastIndexOf("/") + 1,
							origImgpath.length());
			file.renameTo(new File(request.getServletContext().getRealPath(
					imgPath)));
			bannerLinkService.create(new BannerLinkDetail(addBannerForm
					.getCategoryImg5(), addBannerForm.getProductImg5(),
					DateUtil.getCurrentDateTime(), DateUtil
							.getCurrentDateTime(), new BigDecimal(0),
					new BigDecimal(sessionInfo.getUserId()), new BigDecimal(
							sessionInfo.getUserId()), imgPath, bannerService
							.findByID(bannerId)));
		}
	}

	public List<String> getAllPages() throws BusinessFailureException,
			ParseException {
		return bannerService.getAllPages();
	}

	public List<String> getAllPagePostion() throws BusinessFailureException,
			ParseException {
		return bannerService.getAllPagePosition();
	}

	public List<CategoryPlaceHolderTreeVO> getAllCategories(SessionInfo sessionInfo)
			throws BusinessFailureException, ParseException {
		/*List<CategoryAjaxForm> categoriesList = bannerService
				.getAllCategories(sessionInfo.getStoreId());
		return categoriesList;*/
		return bannerService.getAllCategories(sessionInfo
				.getStoreId());
	}

	public void setBannerStatus(long bannerId, String name) {
		BannerTbMaster bannerTbMaster = bannerService.findByID(bannerId);
		if(bannerTbMaster != null){
			bannerTbMaster.setVcStts(name);
			bannerService.update(bannerTbMaster);
		}
	}
}
