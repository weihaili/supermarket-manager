package com.supermarket.service;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;

public interface ItemSpecificationService {
	
	EUDataGridResult getItemSpecificationList(int page,int rows);

	KklResult deleteSpecificationTemplateByIds(String[] ids);

}
