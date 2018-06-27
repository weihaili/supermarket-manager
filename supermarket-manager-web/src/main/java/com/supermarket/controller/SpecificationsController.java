package com.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbItemParam;
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
	
	///item/param/query/itemcatid/{itemCatId}
	@RequestMapping(value="/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public KklResult queryItemSpecificationTemplate(@PathVariable Long itemCatId) {
		KklResult result=null;
		if (itemCatId!=null) {
			result=specificationService.queryItemSpecificationTemplateByCatId(itemCatId);
		}
		return result;
	}
	
	///item/param/save/81
	@RequestMapping(value="/item/param/save/{cid}")
	@ResponseBody
	public KklResult saveSpecificationTemplate(@PathVariable Long cid,String paramData) {
		KklResult result=null;
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		result=specificationService.insertSpecificationTemplate(itemParam);
		return result;
	}

}
