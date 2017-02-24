package com.sot.ecommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.util.StringUtils;

/**
 * Servlet implementation class UploadFileServlet
 */

@WebServlet("/UploadFileServletBanner")
public class UploadFileServlet extends HttpServlet {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(UploadFileServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InputStream inputStreamForproperties = this.getClass().getClassLoader()
				.getResourceAsStream("sbsc.properties");

		Properties properties = new Properties();
		properties.load(inputStreamForproperties);

		String attributeName = null;
		// String productId = null;
		String storeId = null;
		String fileName = null;
		String filePrintName = null;
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);

			for (FileItem item : items) {

				InputStream inputStream = null;
				OutputStream outputStream = null;

				if (item.isFormField()) {

					String strArr[] = item.getString().split("#");
					attributeName = strArr[0];
					// productId = strArr[1];
					storeId = strArr[1];

					if (StringUtils.endsWithIgnoreCase(strArr[0], "Delete")) {

						String deletePath = request.getServletContext()
								.getRealPath(strArr[1]);

						File file = new File(deletePath);

						if (file.delete()) {
							attributeName = "Delete";
							logger.info(file.getName() + " is deleted!");
							System.out.println(file.getName() + " is deleted!");
						} else {
							new Exception(
									"File Can not be deleted , Network is not available , Please try later.");
							logger.info("Delete operation is failed.");
							System.out.println("Delete operation is failed.");
						}
					}

				} else {
					long imageSize = Long.parseLong(properties
							.getProperty("image.size"));
					if (item.getSize() > 0 && item.getSize() <= imageSize) {
						inputStream = item.getInputStream();
						fileName = item.getName();
						String directoryPath = request.getServletContext()
								.getRealPath(
										properties
												.getProperty("image.directory")
												+ "/" + storeId + "/banner");
						File dir = new File(directoryPath);
						if (dir.exists()) {
							logger.info("A folder with name "
									+ properties.getProperty("image.directory")
									+ " is already exist in the path "
									+ directoryPath);
							System.out.println("A folder with name "
									+ properties.getProperty("image.directory")
									+ " is already exist in the path "
									+ directoryPath);
						} else {
							dir.mkdirs();
						}

						logger.info("size::" + item.getSize());
						/*
						 * filePrintName = productId + "_" + attributeName + "_"
						 * + fileName;
						 */
						filePrintName = attributeName + "_" + fileName;
						fileName = directoryPath + "/" + filePrintName;
						outputStream = new FileOutputStream(fileName);
						logger.info("fileName:" + fileName);
						int readBytes = 0;
						byte[] buffer = new byte[10000];
						while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
							outputStream.write(buffer, 0, readBytes);
						}
						outputStream.close();
						inputStream.close();
						attributeName = attributeName + "*"
								+ properties.getProperty("image.directory")
								+ "/" + storeId + "/banner/" + filePrintName;
					} else {

						attributeName = "Size";
					}
				}

			}

			response.setContentType("text/plain");

			response.setCharacterEncoding("UTF-8");

		}

		catch (Exception e) {

			logger.info("Error while uploading image from UploadFileServlet :"
					+ e.getMessage());
			e.printStackTrace();
		}

		response.getWriter().print(attributeName);
	}

	// DUPLICATE
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * InputStream inputStreamForproperties = this.getClass().getClassLoader()
	 * .getResourceAsStream("sbsc.properties");
	 * 
	 * Properties properties = new Properties();
	 * properties.load(inputStreamForproperties);
	 * 
	 * String attributeName = null; String productId = null; String storeId =
	 * null; String fileName = null; String filePrintName = null; String module
	 * = null; try {
	 * 
	 * @SuppressWarnings("unchecked") List<FileItem> items = new
	 * ServletFileUpload( new DiskFileItemFactory()).parseRequest(request);
	 * 
	 * for (FileItem item : items) { String strArr[] =
	 * item.getString().split("#"); InputStream inputStream = null; OutputStream
	 * outputStream = null; attributeName = strArr[0]; productId = strArr[1];
	 * storeId = strArr[2]; module = strArr[3]; if (item.isFormField()) {
	 * 
	 * 
	 * 
	 * 
	 * if (StringUtils.endsWithIgnoreCase(strArr[0], "Delete")) {
	 * 
	 * String deletePath = request.getServletContext() .getRealPath(strArr[1]);
	 * 
	 * File file = new File(deletePath);
	 * 
	 * if (file.delete()) { attributeName = "Delete"; logger.info(file.getName()
	 * + " is deleted!"); System.out.println(file.getName() + " is deleted!"); }
	 * else { new Exception(
	 * "File Can not be deleted , Network is not available , Please try later."
	 * ); logger.info("Delete operation is failed.");
	 * System.out.println("Delete operation is failed."); } }
	 * 
	 * } else { long imageSize = Long.parseLong(properties
	 * .getProperty("image.size")); if (item.getSize() > 0 && item.getSize() <=
	 * imageSize) { inputStream = item.getInputStream(); fileName =
	 * item.getName(); String directoryPath = request.getServletContext()
	 * .getRealPath( properties .getProperty("image.directory") + "/" + storeId
	 * + "/" + module); File dir = new File(directoryPath); if (dir.exists()) {
	 * logger.info("A folder with name " +
	 * properties.getProperty("image.directory") +
	 * " is already exist in the path " + directoryPath);
	 * System.out.println("A folder with name " +
	 * properties.getProperty("image.directory") +
	 * " is already exist in the path " + directoryPath); } else { dir.mkdirs();
	 * }
	 * 
	 * logger.info("size::" + item.getSize()); filePrintName = productId + "_" +
	 * attributeName + "_" + fileName; fileName = directoryPath + "/" +
	 * filePrintName; outputStream = new FileOutputStream(fileName);
	 * logger.info("fileName:" + fileName); int readBytes = 0; byte[] buffer =
	 * new byte[10000]; while ((readBytes = inputStream.read(buffer, 0, 10000))
	 * != -1) { outputStream.write(buffer, 0, readBytes); }
	 * outputStream.close(); inputStream.close(); attributeName = attributeName
	 * + "*" + properties.getProperty("image.directory") + "/" + storeId + "/" +
	 * module + "/" + filePrintName; } else {
	 * 
	 * attributeName = "Size"; } }
	 * 
	 * }
	 * 
	 * response.setContentType("text/plain");
	 * 
	 * response.setCharacterEncoding("UTF-8");
	 * 
	 * }
	 * 
	 * catch (Exception e) {
	 * 
	 * logger.info("Error while uploading image from UploadFileServlet :" +
	 * e.getMessage()); e.printStackTrace(); }
	 * 
	 * response.getWriter().print(attributeName); }
	 */
}
