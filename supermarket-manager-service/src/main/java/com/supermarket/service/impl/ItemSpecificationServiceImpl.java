package com.supermarket.service.impl;

import java.util.Date;
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
import com.supermarket.pojo.TbItemParamExample.Criteria;
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

	@Override
	public KklResult queryItemSpecificationTemplateByCatId(Long itemCatId) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>0) {
			return KklResult.ok(list.get(0));
		}
		return KklResult.ok();
	}

	@Override
	public KklResult insertSpecificationTemplate(TbItemParam itemParam) {
		Date date=new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParamMapper.insertSelective(itemParam);
		return KklResult.ok();
	}

	
	
}
