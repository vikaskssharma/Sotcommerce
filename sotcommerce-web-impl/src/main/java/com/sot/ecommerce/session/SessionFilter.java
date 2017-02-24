package com.sot.ecommerce.session;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SessionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	private ArrayList<String> urlList;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) res;
		httpServletResponse.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		httpServletResponse.setDateHeader("Expires", 0); // Proxies.

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		BigDecimal role=(BigDecimal) request.getSession().getAttribute("RoleId");
		boolean allowedRequest = false;
		if (!(url.endsWith(".htm"))) {
			allowedRequest = true;

		}

		if (urlList.contains(url)) {
			allowedRequest = true;
		}

		if (!allowedRequest) {
			HttpSession session = request.getSession(false);
			if (null == session) {
				response.sendRedirect("./login.htm");
			} else {
				String email = (String) session.getAttribute("email");

				if (null == email) {
					response.sendRedirect("./login.htm");
				} else {

					chain.doFilter(req, res);
				}
			}

		}
		else
		{
			chain.doFilter(req, res);
			
		}

	}

	public void init(FilterConfig config) throws ServletException {
		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());

		}
	}

}
