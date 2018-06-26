package com.supermarket.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbItem;
import com.supermarket.service.ItemService;

/**
 * @author Admin
 * goods manager controller
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	/**
	 * query goods list
	 * @param page:page number
	 * @param rows:records per page
	 * @return easyUI data grid required data type
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	/**
	 * production add function
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public KklResult itemAdd(TbItem item,String desc) {
		return itemService.addItem(item, desc);
	}

}
