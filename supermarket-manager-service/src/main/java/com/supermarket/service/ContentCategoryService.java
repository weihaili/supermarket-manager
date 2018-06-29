package com.supermarket.service;

import java.util.List;

import com.supermarket.common.pojo.EUDataTreeNode;
import com.supermarket.common.utils.KklResult;

public interface ContentCategoryService {
	
	List<EUDataTreeNode> getContentCategoryList(long parentId);

	KklResult addCategoryFolder(Long parentId, String name);

	KklResult updateContentCategoryById(long parseLong, String name);

	KklResult deleteContentCategoryById(Long id);

}
