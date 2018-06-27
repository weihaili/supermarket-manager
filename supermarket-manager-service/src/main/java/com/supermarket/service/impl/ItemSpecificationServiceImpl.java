package com.supermarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbItemParamMapper;
import com.supermarket.pojo.TbItemParam;
import com.supermarket.pojo.TbItemParamExample;
import com.supermarket.service.ItemSpecificationService;

/**
 * @author Admin
 * commodity specification manager 
 */
@Service
public class ItemSpecificationServiceImpl implements ItemSpecificationService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public EUDataGridResult getItemSpecificationList(int page, int rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public KklResult deleteSpecificationTemplateByIds(String[] ids) {
		for (String id : ids) {
			Long templateId=Long.parseLong(id);
			itemParamMapper.deleteByPrimaryKey(templateId);
		}
		return KklResult.ok();
	}

	
}
