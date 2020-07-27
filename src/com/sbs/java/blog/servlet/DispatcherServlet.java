package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.app.App;
import com.sbs.java.mail.service.MailService;

//@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

	// 테스트 후 삭제하기 //임시 비밀번호를 받기 위한 SecureRandom 객체를 생성하는 메서드
	public static String generate(String DATA, int length) {
		SecureRandom random = new SecureRandom();

		if (length < 1)
			throw new IllegalArgumentException("length must be a positive number.");
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {	
			sb.append(DATA.charAt(random.nextInt(DATA.length())));
		}
		return sb.toString();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gmailId = getServletConfig().getInitParameter("gmailId");
		String gmailPw = getServletConfig().getInitParameter("gmailPw");

		new App(req, resp).start(gmailId, gmailPw);

		

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

}
