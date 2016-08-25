package com.coinup.view.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.coinup.model.User;

@WebFilter(urlPatterns={"/pages/authenticated/*"})
public class AuthenticatedFolderFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String page = ( (HttpServletRequest) request  ).getRequestURI();
		String loginPage = "/login.jsf";
		
		boolean isLoginPage = page.lastIndexOf(loginPage) != -1;
		
		HttpSession session = ( (HttpServletRequest) request  ).getSession(true);
		User user = (User) session.getAttribute("user");
		
		if( user == null && !isLoginPage ){
			request.getRequestDispatcher(loginPage).forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}