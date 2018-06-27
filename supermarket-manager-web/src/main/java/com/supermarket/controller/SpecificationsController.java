package com.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.service.ItemSpecificationService;

/**
 * @author Admin
 * commodity specifications manager
 */
@Controller
public class SpecificationsController {
	
	@Autowired
	private ItemSpecificationService specificationService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getSpecificationList(Integer page,Integer rows) {
		EUDataGridResult result = specificationService.getItemSpecificationList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/param/delete",method=RequestMethod.POST)
	@ResponseBody
	public KklResult deleteSpecificationTemplate(String[] ids) {
		KklResult result = null;
		if(ids!=null && ids.length>0) {
			result=specificationService.deleteSpecificationTemplateByIds(ids);
		}
		return result;
	} 

}
