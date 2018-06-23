package com.supermarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Admin
 * visit WEB-INF/jsp/index.jsp
 */
@Controller
public class PageController { 
	
	/**
	 * @return
	 * open home page
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * @return
	 * page jump
	 * show other page in the homePage
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
