package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.util.SignUtil;

/**
* @Classname: Actionservlet.java
* @author: yue
* @Time: 2018年3月30日--上午10:34:43
* @Todo: TODO
* @version V1.0 
**/
public class Actionservlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String signature = request.getParameter("signature");

		String timestamp = request.getParameter("timestamp");

		String nonce = request.getParameter("nonce");

		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
