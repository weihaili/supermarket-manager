package com.supermarket.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermarket.mapper.TbItemMapper;
import com.supermarket.pojo.TbItem;
import com.supermarket.pojo.TbItemExample;

public class TestPageHelper {
	
	@Test
	public void testPageHelper() {
		//initialization spring container
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//get mapper proxy object from spring container
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//execute query and page
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(2, 10);
		List<TbItem> list = itemMapper.selectByExample(example);
		//goods list
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		
		//get page info
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		long total=pageInfo.getTotal();
		System.out.println("total goods count :"+total);
		
	}

}
