package com.supermarket.controller;




import java.util.Arrays;

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
		KklResult result = itemService.addItem(item, desc);
		return result;
	}
	
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public KklResult updateItemInfo(TbItem item,String desc) {
		KklResult result = itemService.updateItem(item, desc);
		return result;
	}
	
	@RequestMapping(value="/rest/item/query/item/desc/{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public KklResult resetItemQueryDesc(@PathVariable Long itemId) {
		System.out.println("commodity id=" +itemId);
		KklResult result=itemService.resetItemQueryDescById(itemId);
		return result;
	}
	
	@RequestMapping(value="/rest/item/param/item/query/{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public KklResult resetItemParamQuery(@PathVariable Long itemId) {
		KklResult result=itemService.resetItemParamQueryById(itemId);
		return result;
	}
	
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public KklResult resetItemDelete(String[]ids) {
		System.out.println(Arrays.toString(ids));
		KklResult result=null;
		if(ids!=null && ids.length>0) {
			result=itemService.resetItemDeleteByIds(ids);
		}
		return result;
	}
	
	@RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public KklResult resetItemInstock(String[] ids) {
		System.out.println(Arrays.toString(ids));
		KklResult result=null;
		if(ids!=null && ids.length>0) {
			result=itemService.resetItemInstockByIds(ids);
		}
		return result;
	}
	
	@RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public KklResult resetItemReshelf(String[] ids) {
		KklResult result=null;
		if(ids!=null && ids.length>0) {
			result=itemService.resetItemReshelfByIds(ids);
		}
		return result;
	}
}
