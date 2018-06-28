package com.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supermarket.service.ItemSpecificationValueService;

/**
 * @author Admin
 * commodity specification value manager
 */
@Controller
public class SpecificationsValueController {
	
	@Autowired
	private ItemSpecificationValueService itemSpecificationValueService;
	
	// /item/params/values/{itemId}
	@RequestMapping(value="/item/params/values/{itemId}")
	public String showItemParamItemValue(@PathVariable Long itemId,Model model) {
		String itemParam="";
		if (itemId!=null) {
			itemParam = itemSpecificationValueService.getItemSpecificationValueByItemId(itemId);
		}
		model.addAttribute("itemParam", itemParam);
		return "item";
	}

}
