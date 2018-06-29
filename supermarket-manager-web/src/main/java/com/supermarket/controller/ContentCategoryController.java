package com.supermarket.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataTreeNode;
import com.supermarket.common.utils.KklResult;
import com.supermarket.service.ContentCategoryService;

/**
 * @author Admin
 * content category manager controller
 */
@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService service;

	 
	/**
	 * show content category
	 * url:/content/category/list
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EUDataTreeNode> showContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUDataTreeNode> categoryList = service.getContentCategoryList(parentId);
		return categoryList;
	}
	
	/**
	 * add content category
	 * url:/content/category/create
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public KklResult createCategoryFolder( String parentId,String name) {
		System.out.println(parentId);
		KklResult result=service.addCategoryFolder(Long.parseLong(parentId),name);
		return result;
	}
	
	/** 
	 * update content category
	 * url:/content/category/update
	 * @return
	 */
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public KklResult updateCategory(String id,String name) {
		KklResult result=service.updateContentCategoryById(Long.parseLong(id),name);
		return result;
	}
	
	/**
	 * url:/content/category/delete
	 * parentId,id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/content/category/delete",method=RequestMethod.POST)
	@ResponseBody
	public KklResult deleteContentCategory(@RequestParam Long id) {
		System.out.println("id="+id);
		KklResult result = service.deleteContentCategoryById(id);
		return result;
	}
}
