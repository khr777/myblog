package com.sbs.java.blog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	//똑같은 메서드는 여러개가 있어도 상관없다. 
	public static boolean empty(HttpServletRequest req, String paramName) {
		 String paramValue = req.getParameter(paramName);
		return empty(paramValue);
	}
	public static  boolean empty(Object obj) {
		 if ( obj == null ) {
			 return true;
		 }
		 if ( obj instanceof String) {
			 return ((String)obj).trim().length() == 0 ;
		 }
		return true;
	}
	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);
		return isNum(paramValue);
	}
	public static boolean isNum(Object obj) {
		if ( obj == null ) {
			return false;
		}
		if ( obj instanceof Long ) {
			return true;
		}
		else if ( obj instanceof Integer ) {
			return true;
		}
		else if ( obj instanceof String ) {
			try {
				Integer.parseInt((String)obj);
				return true;
			}
			catch ( NumberFormatException e ) {
				return false;
			}
		}
		return false;
	}
	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}
	public static void printEx(String errName, HttpServletResponse resp, Exception e) { // DispatcherServlet의 SQLException과 Exception의 메서드가 똑같음.
		try {																	// 둘다 수용하기 위해서는 예외 타입을  Exception이라고 해주어야 한다.  
			resp.getWriter().append("<h1 style='color:red; font-weight:bold; text-align:left; '>[에러 : " + errName + "]</h1>");   // -> 얘는 try/catch 문으로 감싸주어야 한다.
			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem; '>");   
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) { 
			e1.printStackTrace();  // 얘는 에러가 날일이 없다. 신경쓰지 않아도 된다. 
		}
	}
	public static String getString(HttpServletRequest req, String paramName) {
		
		return req.getParameter(paramName);
	}
	public static String getUriEncoded(String str) { // Uri 인코딩을 해야할 때가 있기에 미리 해놓는 작업.(2020-07-22)
		try { // 문제가 없으면 인코딩된 Uri을 return 하고, 문제가 있으면 원문을 리턴한다.
			return  URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	public static String getString(HttpServletRequest req, String paramName, String elseValue) {
		if ( req.getParameter(paramName) == null ) { // 아예 없거나
			return elseValue;
		}
		
		if ( req.getParameter(paramName).trim().length() == 0 ) { // 공백이 입력되었거나
			return elseValue;
		}
		
		return getString(req, paramName);
	}
	public static boolean isSuccess(Map<String, Object> rs) {
		
		return ((String)rs.get("resultCode")).startsWith("S-");
	}
	public static String getNewUriRemoved(String Uri, String paramName) {
		String deleteStrStarts = paramName + "=";
		int delStartPos = Uri.indexOf(deleteStrStarts);

		if (delStartPos != -1) {
			int delEndPos = Uri.indexOf("&", delStartPos);

			if (delEndPos != -1) {
				delEndPos++;
				Uri = Uri.substring(0, delStartPos) + Uri.substring(delEndPos, Uri.length());
			} else {
				Uri = Uri.substring(0, delStartPos);
			}
		}

		if (Uri.charAt(Uri.length() - 1) == '?') {
			Uri = Uri.substring(0, Uri.length() - 1);
		}

		if (Uri.charAt(Uri.length() - 1) == '&') {
			Uri = Uri.substring(0, Uri.length() - 1);
		}

		return Uri;
		
	}
	

	public static String getNewUri(String Uri, String paramName, String paramValue) {
		Uri = getNewUriRemoved(Uri, paramName);

		if (Uri.contains("?")) {
			Uri += "&" + paramName + "=" + paramValue;
		} else {
			Uri += "?" + paramName + "=" + paramValue;
		}
		

		Uri = Uri.replace("?&", "?");

		return Uri;
	}
	public static String getNewUriAndEncoded(String Uri, String paramName, String paramValue) {
		if ( Uri.contains("?") == false ) {
			Uri += "?dummy=dummy";  //아무거나 붙여주어도 상관없다. ?가 없다면 붙여주기만을 위한 용도이므로.
		}
		
		if ( Uri.indexOf(paramName + "=") != -1 ) {
			int startPos =  Uri.indexOf(paramName + "=");
		}
		
		Uri += "&" + paramName + "=" + paramValue;
		
		
		return getUriEncoded(getNewUri(Uri, paramName, paramValue));
	}
	
	public static int sendMail(String smtpServerId, String smtpServerPw, String from, String fromName, String to,
			String title, String body) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth(smtpServerId, smtpServerPw);

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress(from, fromName));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(title, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setContent(body, "text/html; charset=UTF-8");  // 혜련 추가 한 것.  "text/html; 은 html을 인식하겠다라는 의미. char~~는 한글깨짐 방지! 
			
			
			
			
			Transport.send(msg);

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
			return -1;
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
			return -2;
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
			return -3;
		}
		
		return 1;
	}
	public static String getTempPassword(int length) {
		int index = 0; 
		char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 
		'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 
		'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
		'w', 'x', 'y', 'z' }; 

		StringBuffer sb = new StringBuffer(); 

		for (int i = 0; i < length; i++) { 
		index = (int) (charArr.length * Math.random()); 
		sb.append(charArr[index]); 
		} 

		return sb.toString(); 
	}
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for ( int i = 0; i < hash.length; i++ ) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if ( hex.length() == 1 ) 
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			return "";
		}
	}
}
