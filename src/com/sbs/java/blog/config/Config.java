package com.sbs.java.blog.config;

public class Config {
	// ★ 내부 설정 파일 ★    ( web.xml은 외부 설정 파일 ) 
	public static String mailFrom = "no-reply@no-reply.com";
	public static String mailFromName = "관리자";
	public static String gmailId = "kim5638yw@gmail.com";
	public static String gmailPw;  // web.xml에 자료가 있으면 config 의 loginId, pw는 순위에서 밀린다. (우선순위 : web.xml)
}
