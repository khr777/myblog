package com.sbs.java.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.app.App;
import com.sbs.java.mail.service.MailService;

//@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gmailId = getServletConfig().getInitParameter("gmailId");
		String gmailPw = getServletConfig().getInitParameter("gmailPw");

		new App(req, resp).start(gmailId, gmailPw);
		
		// 혜련 테스트용 
		MailService mailService = new MailService(gmailId, gmailPw, gmailId, "관리자");
		String emailTitle = "harry's life 회원가입을 축하드립니다. 이메일 인증 후 활동해주세요.";
		String emailBody = "";
		emailBody += "<h1>환영합니다. 회원님 ^^</h1><br>";
		emailBody += "<h2>테스트 중입니다. 회원님????</h2><br>";
		emailBody += "<html><body><p><a href=" + "https://harry.my.iu.gy/blog/s/member/doAuthMail?code=" + " 인증코드 " + ">인증하기</a></p></body></html>";
		
		//emailBody += "<html><body><a href=" + "localhost:8081/blog/s/member/doAuthMail?code=" + a + "&loginId="+loginId+">인증하기</a></body></html>";
		//emailBody += "<html><body><a href=" + "https://harry.my.iu.gy/blog/s/member/doAuthMail?code=" + " 인증코드 " + ">인증하기</a></body></html>";
		//"<html><body><a href=" + "localhost:8081/blog/s/member/doAuthMail?code=" + a + "&loginId="+loginId+">인증하기</a></body></html>";
		
		boolean sendMailDone = mailService.send("kim5638yw@gmail.com" , emailTitle, emailBody) == 1;
		
		
		resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));

		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}

}
