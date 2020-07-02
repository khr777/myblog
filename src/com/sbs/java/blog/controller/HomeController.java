package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller {

	public HomeController(Connection dbConn) {
		
	}

	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		switch ( actionMethodName ) {
		case "main":
			return "home/main";
		}
		return "";
		
	}

	
}
