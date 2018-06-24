package com.supermarket.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataTreeNode;
import com.supermarket.service.ItemCatService;

/**
 * @author Admin
 * commodity category manager controller
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EUDataTreeNode> getItemCatList(
			@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUDataTreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}
	
	

}
