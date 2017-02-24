package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.UmTbMenu;
import com.sbsc.fos.persistance.UmTbUser;
import com.sot.ecommerce.um.common.UserInfoVO;
import com.sot.ecommerce.um.service.MenuServices;

@Service
public class MenuServiceImpl implements MenuServices {

	private GenericDAO<UmTbMenu> dao;

	@Autowired
	public void setDAO(GenericDAO<UmTbMenu> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(UmTbMenu.class);
	}

	@Transactional
	public List<UserInfoVO> getLeftMenuContent(UmTbUser signInUser) {
		try {

			String userEmail = signInUser.getVcEml();

			String query = "SELECT USR.NM_USR_ID,RL.NM_RL_ID, RL.VC_RL_NM, MNU.NM_MN_ID, MNU.VC_MN_NM, MNU.VC_MN_LNK,USR.VC_EML"
					+ " FROM   UM_TB_USER USR "
					+ "INNER JOIN UM_TB_USER_ROLE UR "
					+ "ON  USR.NM_USR_ID = UR.NM_USR_ID "
					+ "INNER JOIN UM_TB_ROLE RL "
					+ "ON UR.NM_RL_ID = RL.NM_RL_ID "
					+ "INNER JOIN   UM_TB_ROLE_PERMISSION RPM "
					+ "ON RL.NM_RL_ID = RPM.NM_RL_ID "
					+ "INNER JOIN UM_TB_MENU  MNU "
					+ "ON RPM.NM_MN_ID = MNU.NM_MN_ID "				
					+ "WHERE USR.VC_EML ='%s' and 	IS_DLTD = 0 ORDER BY MNU.VC_MN_NM";

			// brijesh25.kumar@rsystems.com

			String formatedQuery = String.format(query, userEmail);

			HashMap<String, Long> idNameMap = new HashMap<String, Long>();

			LinkedHashMap<String, Type> scalarMapping = new LinkedHashMap<String, Type>();
			scalarMapping.put("NM_USR_ID", StandardBasicTypes.LONG);
		//	scalarMapping.put("USR.VC_FRST_NM", StandardBasicTypes.STRING);
			//scalarMapping.put("USR.VC_LST_NM", StandardBasicTypes.STRING);
			//scalarMapping.put("VC_LST_NM", StandardBasicTypes.STRING);
			
			scalarMapping.put("NM_RL_ID", StandardBasicTypes.LONG);
			scalarMapping.put("VC_RL_NM", StandardBasicTypes.STRING);
			scalarMapping.put("NM_MN_ID", StandardBasicTypes.LONG);
			scalarMapping.put("VC_MN_NM", StandardBasicTypes.STRING);
			scalarMapping.put("VC_MN_LNK", StandardBasicTypes.STRING);
			scalarMapping.put("VC_EML", StandardBasicTypes.STRING);
			
			List list= dao.executeSQLQuery(formatedQuery,
					scalarMapping);

			
			//return idNameMap;

			//List list = dao.executeSQLQuery(formatedQuery);

			List<UserInfoVO> menuList = storeDataInDTO(list);

			return menuList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private List<UserInfoVO> storeDataInDTO(List list) {
		List<UserInfoVO> menuList = new ArrayList<UserInfoVO>();

		Iterator itr = list.iterator();

		while (itr.hasNext()) {
			Object[] rowData = (Object[]) itr.next();
			UserInfoVO usrDto = new UserInfoVO();
			usrDto.setUserId((long)rowData[0]);			
			usrDto.setRoleName((String) rowData[2]);
			usrDto.setMenuId((long) rowData[3]);
			usrDto.setMenuName((String) rowData[4]);
			usrDto.setMenuLink((String) rowData[5]);
			usrDto.setEmail((String) rowData[6]);
			menuList.add(usrDto);

		}
		return menuList;
	}

}
