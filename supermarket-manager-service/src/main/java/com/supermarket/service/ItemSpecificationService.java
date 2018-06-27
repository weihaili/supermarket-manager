package com.supermarket.service;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbItemParam;

public interface ItemSpecificationService {
	
	EUDataGridResult getItemSpecificationList(int page,int rows);

	KklResult deleteSpecificationTemplateByIds(String[] ids);

	KklResult queryItemSpecificationTemplateByCatId(Long itemCatId);

	KklResult insertSpecificationTemplate(TbItemParam itemParam);


}
