package com.supermarket.service;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
	
	KklResult addItem(TbItem item,String desc);
}
