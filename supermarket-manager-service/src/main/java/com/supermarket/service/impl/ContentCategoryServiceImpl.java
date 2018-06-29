package com.supermarket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.common.pojo.CommonParamter;
import com.supermarket.common.pojo.EUDataTreeNode;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbContentCategoryMapper;
import com.supermarket.pojo.TbContentCategory;
import com.supermarket.pojo.TbContentCategoryExample;
import com.supermarket.pojo.TbContentCategoryExample.Criteria;
import com.supermarket.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUDataTreeNode> getContentCategoryList(long parentId) {
		List<EUDataTreeNode> resuList = new ArrayList<EUDataTreeNode>();
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		for (TbContentCategory tbContentCategory : list) {
			EUDataTreeNode node=new EUDataTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resuList.add(node);
		}
		return resuList;
	}

	@Override
	public KklResult addCategoryFolder(Long parentId, String name) {
		if (isRepeatContentCategoty(parentId, name)) {
			return KklResult.repeat();
		}
		TbContentCategory contentCategory=new TbContentCategory();
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setIsParent(CommonParamter.ONE.getCodeBoolean());
		contentCategory.setName(name);
		contentCategory.setUpdated(date);
		contentCategory.setStatus(CommonParamter.ONE.getCode());
		contentCategory.setSortOrder(CommonParamter.ONE.getCode());
		contentCategory.setParentId(parentId);
		contentCategoryMapper.insertSelective(contentCategory);
		return KklResult.ok();
	}

	@Override
	public KklResult updateContentCategoryById(long parseLong, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parseLong);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return KklResult.ok();
	}
	
	private boolean isRepeatContentCategoty(long parentid,String name) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		criteria.andNameEqualTo(name);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public KklResult deleteContentCategoryById(Long id) {
		contentCategoryMapper.deleteByPrimaryKey(id);
		return KklResult.ok();
	}
	
	
	
	

}
