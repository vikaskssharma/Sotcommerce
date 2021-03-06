package com.sot.ecommerce.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sot.ecommerce.dao.GenericDAO;
import com.sot.ecommerce.entitities.UmTbUser;
import com.sot.ecommerce.entitities.UmTbUserRole;
import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.exception.GenericFailureException;
import com.sot.ecommerce.service.UserService;
import com.sot.ecommerce.util.EncryptionAndDecryption;

@Service
public class UserServiceImpl implements UserService {

	/** Logger instance. **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private static final String USER_EMAIL_FIELD_NAME = "vcEml";
	private static final String Store = "storeTbMaster";
	private GenericDAO<UmTbUser> dao;

	@Autowired
	public void setDAO(GenericDAO<UmTbUser> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(UmTbUser.class);
	}

	@Transactional
	public void create(UmTbUser user) {
		dao.create(user);
	}

	@Transactional
	public List<UmTbUser> findAll() {
		return dao.findAll();
	}

	@Transactional
	public void update(UmTbUser user) {

		dao.saveOrUpdate(user);
	}

	@Transactional
	public void delete(UmTbUser user) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void deleteById(long userId) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public List<UmTbUser> findAllByProperty(String propertyName, Object value) {
		return dao.findAllByProperty(propertyName, value);
	}

	@Transactional
	public List<UmTbUser> findAllByProperty(
			HashMap<String, Object> propertiesMap) {
		return dao.findAllByProperty(propertiesMap);
	}

	@Transactional
	public UmTbUser signIn(UmTbUser signInUser, String usrRoleName)
			throws BusinessFailureException, GenericFailureException,
			NoSuchAlgorithmException, NoSuchPaddingException {

		HashMap<String, Object> searchcriteria = new HashMap<String, Object>();
		searchcriteria.put(Store, signInUser.getStoreTbMaster());
		searchcriteria.put(USER_EMAIL_FIELD_NAME, signInUser.getVcEml());

		List<UmTbUser> dbUsers = this.findAllByProperty(searchcriteria);
		String decryptedPassword = null;
		if (dbUsers.size() > 0) {
			EncryptionAndDecryption decrypt = new EncryptionAndDecryption("");
			for (UmTbUser usr : dbUsers) {

				decryptedPassword = decrypt.decrypt(usr.getVcPswrd());
				// String decryptedPassword=
				// encrpt.decrypt(usr.getUserPassword().trim());
				if (!signInUser.getVcPswrd().equals(decryptedPassword)) {

					logger.warn("Sign-In failed, UmTbUser Name or Password mismatch."
							+ signInUser.getVcEml());

					throw new BusinessFailureException(203,
							"Sign-In failed, UmTbUser Name or Password mismatch.");
				}
				decrypt = null;

				// check for role
				List usrRoles = usr.getUmTbUserRoles();
				// signInUser.get
				boolean hasAccess = false;
				for (Object usrroles : usrRoles) {
					UmTbUserRole usrrole = (UmTbUserRole) usrroles;
					if (usrrole.getUmTbRole().getVcRlNm().equals(usrRoleName)) {
						hasAccess = true;
						break;
					}

				}
				if (!hasAccess) {

					logger.warn("Sign-In failed, You do not have permission to access : "
							+ signInUser.getVcEml());

					throw new BusinessFailureException(207,
							"Sign-In failed,You do not have permission to access.");

				}

			}

		} else {

			logger.warn("Sign-In failed, No user exist with this Email-ID : "
					+ signInUser.getVcEml());

			throw new BusinessFailureException(204,
					"Sign-In failed, No user exist with this Email-ID.");
		}
		return signInUser;
	}

	@Transactional
	public void signUp(UmTbUser newUser, String urlRole)
			throws BusinessFailureException, GenericFailureException {
		HashMap<String, Object> searchcriteria = new HashMap<String, Object>();
		searchcriteria.put(Store, newUser.getStoreTbMaster());
		searchcriteria.put(USER_EMAIL_FIELD_NAME, newUser.getVcEml());
		List<UmTbUser> dbUsers = findAllByProperty(searchcriteria);

		if (dbUsers.size() > 0) {

			logger.warn("UmTbUser already Exists with the same Email-ID");

			throw new BusinessFailureException(206,
					"UmTbUser already Exists with same Email-ID");

		} else {

			logger.warn("Creating user with Email-ID " + newUser.getVcEml());

			this.create(newUser);

		}

	}

	@Transactional
	public UmTbUser findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	
}
