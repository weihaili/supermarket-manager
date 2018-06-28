package com.supermarket.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.pojo.ItemStatus;
import com.supermarket.common.utils.IDUtils;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbItemDescMapper;
import com.supermarket.mapper.TbItemMapper;
import com.supermarket.mapper.TbItemParamItemMapper;
import com.supermarket.pojo.TbItem;
import com.supermarket.pojo.TbItemDesc;
import com.supermarket.pojo.TbItemExample;
import com.supermarket.pojo.TbItemExample.Criteria;
import com.supermarket.pojo.TbItemParamItem;
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
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

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

	/* insert complete product information into table tb_item and tb_item_desc
	 * @see com.supermarket.service.ItemService#addItem(com.supermarket.pojo.TbItem, java.lang.String)
	 */
	@Override
	public KklResult addItem(TbItem item, String desc,String itemParams) {
		//generate product id
		long itemId = IDUtils.genItemId();
		Date date = new Date();
		//completion product attributes
		item.setId(itemId);
		//1:normal  2:drop off   3:delete
		item.setStatus(ItemStatus.NORMAL.getStatusCode());
		item.setCreated(date);
		item.setUpdated(date);
		//insert table tb_item
		itemMapper.insert(item);
		
		//create production description table pojo object
		TbItemDesc itemDesc = new TbItemDesc();
		//completion product attributes
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//insert table tb_item_desc
		itemDescMapper.insert(itemDesc);
		
		//create commodity specification table pojo objec
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItem.setItemId(itemId);
		itemParamItemMapper.insertSelective(itemParamItem);
		
		//return success
		return KklResult.ok();
	}

	/* before reset commodity information query description by item id
	 * @see com.supermarket.service.ItemService#resetItemQueryDescById(long)
	 */
	@Override
	public KklResult resetItemQueryDescById(long itemId) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		String desc=itemDesc.getItemDesc();
		return KklResult.ok(desc);
	}

	/* before reset commodity information query item attributes by item id
	 * @see com.supermarket.service.ItemService#resetItemParamQueryById(long)
	 */
	@Override
	public KklResult resetItemParamQueryById(long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return KklResult.ok(tbItem);
	}

	/* update commodity information
	 * @see com.supermarket.service.ItemService#updateItem(com.supermarket.pojo.TbItem, java.lang.String)
	 */
	@Override
	public KklResult updateItem(TbItem item, String desc) {
		Date date=new Date();
		Long itemId = item.getId();
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		tbItem.setBarcode(item.getBarcode());
		tbItem.setCid(item.getCid());
		tbItem.setImage(item.getImage());
		tbItem.setNum(item.getNum());
		tbItem.setPrice(item.getPrice());
		tbItem.setSellPoint(item.getSellPoint());
		tbItem.setStatus(ItemStatus.NORMAL.getStatusCode());
		tbItem.setTitle(item.getTitle());
		tbItem.setUpdated(date);
		itemMapper.updateByPrimaryKey(tbItem);
		
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setUpdated(date);
		itemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);
		
		return KklResult.ok();
	}

	@Override
	public KklResult resetItemDeleteByIds(String[] ids) {
		for (String id : ids) {
			Long itemId = Long.parseLong(id);
			itemMapper.deleteByPrimaryKey(itemId);
			itemDescMapper.deleteByPrimaryKey(itemId);
		}
		return KklResult.ok();
	}

	@Override
	public KklResult resetItemInstockByIds(String[] ids) {
		for (String id : ids) {
			Long itemId = Long.parseLong(id);
			TbItem item = itemMapper.selectByPrimaryKey(itemId);
			item.setStatus(ItemStatus.DROPOFF.getStatusCode());
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return KklResult.ok();
	}

	@Override
	public KklResult resetItemReshelfByIds(String[] ids) {
		for (String id : ids) {
			Long itemId=Long.parseLong(id);
			TbItem item = itemMapper.selectByPrimaryKey(itemId);
			item.setStatus(ItemStatus.NORMAL.getStatusCode());
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return KklResult.ok();
	}
}
