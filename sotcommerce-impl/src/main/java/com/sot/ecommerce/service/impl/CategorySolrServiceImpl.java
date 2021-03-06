/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.Section;

/**
 * @author simanchal.patra
 * 
 */
@Service
public class CategorySolrServiceImpl implements CategorySolrService {

	private SolrDAO<Category> solrDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.store.service.CategorySolrService#setDAO(com.sbsc.fos.store
	 * .solr.data.SolrDAO)
	 */
	@Autowired
	@Override
	public void setDAO(SolrDAO<Category> documentName) {
		solrDAO = documentName;
		solrDAO.setClazz(Category.class);
	}

	@Override
	public List<Category> getPrentCategories(Long categoryId) {

		List<String> categoryNameList = new ArrayList<>();

		Category category = getCategory(categoryId);

		for (String parentCategoryName : category.getVcCtgryPth().split("/")) {
			if (StringUtils.isNotBlank(parentCategoryName)) {
				categoryNameList.add(parentCategoryName);
			}
		}

		LinkedHashMap<String, Object> inCriteria = new LinkedHashMap<>();

		inCriteria.put("id", "Category");

		inCriteria.put("vcCtgryNm", categoryNameList);

		// return solrDAO.searchHybrid(solrFieldAndConditions, null, 0, 100);

		// return solrDAO.searchIn("vcCtgryNm", categoryNameList, 0, 100);

		return solrDAO.searchInAndNotIn(inCriteria, null, 0, 100);
	}

	@Override
	public List<Category> getImmidiateChildCategories(Long categoryId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Category");

		solrFieldAndConditions.put("ctgry_prnt_id", getCategory(categoryId)
				.getCtgry_id());

		return solrDAO.searchHybrid(solrFieldAndConditions, null, 0, 100);
	}

	@Override
	public List<Category> getChildCategories(Long categoryId) {

		LinkedHashMap<String, Object> inCriteria = new LinkedHashMap<>();

		LinkedHashMap<String, Object> inNegateCriteria = new LinkedHashMap<>();

		List<String> nameList = new ArrayList<String>();

		List<Long> parentIdList = new ArrayList<Long>();

		nameList.add(getCategory(categoryId).getVcCtgryNm());

		inCriteria.put("vcCtgryPth", nameList);

		for (Category category : getPrentCategories(categoryId)) {
			parentIdList.add(category.getCtgry_id());
		}

		if (parentIdList.size() > 0) {

			inNegateCriteria.put("ctgry_id", parentIdList);
		}

		return solrDAO.searchInAndNotIn(inCriteria, inNegateCriteria, 0, 100);
	}

	@Override
	public List<Category> getNoNPlaceHolderChildCategories(Long parentCategoryId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Category");

		solrFieldAndConditions.put("isPlchldr", new Integer(0));

		if (parentCategoryId != 0L) {

			solrFieldAndConditions.put("vcCtgryPth",
					getCategory(parentCategoryId).getVcCtgryNm());
		}

		return solrDAO.searchHybrid(solrFieldAndConditions, null, 0, 100);
	}

	@Override
	public Category getCategory(Long categoryId) {

		Category category = null;

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Category");

		solrFieldAndConditions.put("ctgry_id", categoryId);

		List<Category> categoryList = (List<Category>) solrDAO.searchHybrid(
				solrFieldAndConditions, null, 0, 1);

		if (categoryList.size() > 0) {
			category = categoryList.get(0);
		}

		return category;
	}

	@Override
	public List<Category> getAllCategories() throws SolrServerException,
			MalformedURLException {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Category");

		return (List<Category>) solrDAO.searchHybrid(solrFieldAndConditions,
				null, 0, 1000);

	}

}
