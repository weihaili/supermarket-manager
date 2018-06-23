package com.supermarket.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.mapper.TbItemMapper;
import com.supermarket.pojo.TbItem;
import com.supermarket.pojo.TbItemExample;
import com.supermarket.pojo.TbItemExample.Criteria;
import com.supermarket.service.ItemService;

/**
 * @author Admin
 * goods manager service
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;

	/* according to primaryKey query goods information
	 * @see com.supermarket.service.ItemService#getItemById(long)
	 */
	@Override
	public TbItem getItemById(long itemId) {
		//according to primaryKey query
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		//according to criteria (query conditions) query
		TbItemExample example=new TbItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/* goods list query
	 * @see com.supermarket.service.ItemService#getItemList(long, long)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(page, rows);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
