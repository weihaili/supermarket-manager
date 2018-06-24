package com.supermarket.service;

import java.util.List;

import com.supermarket.common.pojo.EUDataTreeNode;

public interface ItemCatService {
	
	List<EUDataTreeNode> getItemCatList(long parentId);

}
