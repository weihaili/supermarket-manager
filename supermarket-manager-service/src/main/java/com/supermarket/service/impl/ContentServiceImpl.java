package com.supermarket.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermarket.common.pojo.EUDataGridResult;
import com.supermarket.common.utils.ExceptionUtil;
import com.supermarket.common.utils.HttpClientUtil;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbContentMapper;
import com.supermarket.pojo.TbContent;
import com.supermarket.pojo.TbContentExample;
import com.supermarket.pojo.TbContentExample.Criteria;
import com.supermarket.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public EUDataGridResult getContentListByCategoryId(Long categoryId, int page, int rows) {
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public KklResult saveNewContent(TbContent content) {
		Date date=new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insertSelective(content);
		
		//add cache synchronized
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+"/"+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
			return KklResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return KklResult.ok();
	}

	@Override
	public KklResult updateCurrentContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeyWithBLOBs(content);
		return null;
	}

	@Override
	public KklResult deleteContentByIds(String[] ids) {
		for (String idStr : ids) {
			long id=Long.parseLong(idStr);
			contentMapper.deleteByPrimaryKey(id);
		}
		return KklResult.ok();
	}
	
	
	
	
	
	

}
