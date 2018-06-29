package com.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbContent;
import com.supermarket.service.ContentService;

/**
 * @author Admin
 * content manager controller
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService service;
	
	/**
	 * url:/content/query/list?categoryId=92&page=1&rows=20
	 * get content list by category id
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDataGridResult queryContentList(Long categoryId,int page,int rows) {
		EUDataGridResult result=service.getContentListByCategoryId(categoryId,page,rows);
		return result;
	}
	
	/**
	 * save new content
	 * url:/content/save
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public KklResult saveContent(TbContent content) {
		KklResult result = service.saveNewContent(content);
		return result;
	}
	
	/**
	 * update selected content
	 * url:/rest/content/edit
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public KklResult updateContent(TbContent content) {
		KklResult result=service.updateCurrentContent(content);
		return result;
	}
	
	/**
	 * delete selected content
	 * url:/content/delete
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/content/delete",method=RequestMethod.POST)
	@ResponseBody
	public KklResult deleteContent(String[] ids) {
		KklResult result=null;
		if (ids!=null && ids.length>0) {
			result=service.deleteContentByIds(ids);
		}
		return result;
	}

}
