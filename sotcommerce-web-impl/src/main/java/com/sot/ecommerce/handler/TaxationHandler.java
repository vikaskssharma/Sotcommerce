package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.TaxationTbDetail;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.rr.service.ReviewService;
import com.sbsc.fos.taxation.commons.TaxationVO;
import com.sbsc.fos.taxation.service.TaxationService;
import com.sbsc.fos.um.service.CountryService;

/**
 * 
 * @author gaurav.kumar
 */

@Component
public class TaxationHandler {

	@Autowired
	private TaxationService taxationService;


	
	@Autowired
	private CountryService countryService;
	
	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}


	public List<UmTbCntryMstr> getCountryList() {

		List<UmTbCntryMstr> countryList = countryService.findAll();

		return countryList;
	}

	
	public String getTax(long categoryId, long countryId) {
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("categoryTbMaster.nmCtgryId", categoryId);
		propertiesMap.put("umTbCntryMstr.nmCntryId", countryId);
		List<TaxationTbDetail> taxationList = taxationService.findAllByProperty(propertiesMap);	
		String noTaxForCountry ="";
		if(taxationList.size()>0){
			return taxationList.get(0).getNmTxPrcntg().toString();
		}else{
			return noTaxForCountry;
		}
		 
	}
	
	public List<TaxationVO> getTaxForCountries(long categoryId) {
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("categoryTbMaster.nmCtgryId", categoryId);
		
		List<TaxationTbDetail> taxationList = taxationService.findAllByProperty(propertiesMap);	
		
		List<TaxationVO> tvoList= new ArrayList<TaxationVO>();
		
		for (int i = 0; i < taxationList.size(); i++) {
			TaxationVO tvoObj = new TaxationVO();
			tvoObj.setCountryName(taxationList.get(i).getUmTbCntryMstr().getVcCntryNm());
			if(taxationList.get(i).getNmTxPrcntg()!=null)
				tvoObj.setCountryTax(taxationList.get(i).getNmTxPrcntg().toString());
			else
				tvoObj.setCountryTax("");
			tvoList.add(tvoObj);
		}
		
		return tvoList;
		 
	}
	
	public boolean updateTaxation(long categoryId, BigDecimal taxPercentage, long countryId, long storeId) {
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("categoryTbMaster.nmCtgryId", categoryId);
		propertiesMap.put("umTbCntryMstr.nmCntryId", countryId);
		propertiesMap.put("storeTbMaster.nmStrId", storeId);
		List<TaxationTbDetail> taxationList = taxationService.findAllByProperty(propertiesMap);
		TaxationTbDetail txn = null;
		if(taxationList.size()==0){
			txn = new TaxationTbDetail();
			CategoryTbMaster ctg = new CategoryTbMaster();
			ctg.setNmCtgryId(categoryId);
			txn.setCategoryTbMaster(ctg);
			UmTbCntryMstr ctry = new UmTbCntryMstr();
			ctry.setNmCntryId(countryId);
			txn.setUmTbCntryMstr(ctry);
			txn.setIsDltd(BigDecimal.valueOf(0));
			StoreTbMaster str = new StoreTbMaster();
			str.setNmStrId(storeId);
			txn.setStoreTbMaster(str);
		}else{
			txn = taxationList.get(0);
			
		}
		txn.setNmTxPrcntg(taxPercentage);
		return taxationService.updateTaxation(txn);
		 
	}
	

}
