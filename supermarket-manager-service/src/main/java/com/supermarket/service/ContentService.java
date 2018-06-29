package com.supermarket.service;

import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.pojo.TbContent;

public interface ContentService {

	EUDataGridResult getContentListByCategoryId(Long categoryId, int page, int rows);

	KklResult saveNewContent(TbContent content);

	KklResult updateCurrentContent(TbContent content);

	KklResult deleteContentByIds(String[] ids);

}
