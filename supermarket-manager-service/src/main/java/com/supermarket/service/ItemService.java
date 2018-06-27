package com.supermarket.service;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
	
	KklResult addItem(TbItem item,String desc);

	KklResult resetItemQueryDescById(long itemId);

	KklResult resetItemParamQueryById(long itemId);

	KklResult updateItem(TbItem item, String desc);

	KklResult resetItemDeleteByIds(String[] ids);

	KklResult resetItemInstockByIds(String[] ids);

	KklResult resetItemReshelfByIds(String[] ids);
}
