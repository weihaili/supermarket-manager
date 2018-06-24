package com.supermarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.common.pojo.EUDataTreeNode;
import com.supermarket.mapper.TbItemCatMapper;
import com.supermarket.pojo.TbItemCat;
import com.supermarket.pojo.TbItemCatExample;
import com.supermarket.pojo.TbItemCatExample.Criteria;
import com.supermarket.service.ItemCatService;

/**
 * @author Admin
 * goods category manager
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EUDataTreeNode> getItemCatList(long parentId) {
		List<EUDataTreeNode> resultList=new ArrayList<EUDataTreeNode>();
		//according to commodity parent id query subnode list
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//convert the list of child nodes into EDDataTreeNode list
		for (TbItemCat tbItemCat : list) {
			EUDataTreeNode treeNode=new EUDataTreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}
		return resultList;
	}

}
