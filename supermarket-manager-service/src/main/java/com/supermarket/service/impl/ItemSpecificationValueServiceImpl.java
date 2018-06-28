package com.supermarket.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.common.utils.JsonUtils;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbItemParamItemMapper;
import com.supermarket.pojo.TbItemParamItem;
import com.supermarket.pojo.TbItemParamItemExample;
import com.supermarket.pojo.TbItemParamItemExample.Criteria;
import com.supermarket.service.ItemSpecificationValueService;

@Service
public class ItemSpecificationValueServiceImpl implements ItemSpecificationValueService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public String getItemSpecificationValueByItemId(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>0) {
			TbItemParamItem itemParamItem = list.get(0);
			String paramData = itemParamItem.getParamData();
			//generate html code
			//convert json object to java object
			List<Map> jsonList=JsonUtils.jsonToList(paramData, Map.class);
			StringBuffer sb=new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\"class=\"Ptable\">\n" );
			sb.append("	<tbody>\n" );
			for (Map map : jsonList) {
				sb.append("		<tr>\n" );
				sb.append("			<th class=\"tTitle\" colspan=\"2\">"+map.get("group")+"</th>\n" );
				sb.append("		</tr>\n" );
				List<Map> params= (List<Map>) map.get("params");
				for (Map map2 : params) {
					sb.append("		<tr>\n" );
					sb.append("			<td class=\"tTitle\">"+map2.get("k")+"</td>\n" );
					sb.append("			<td>"+map2.get("v")+"</td>\n" );
					sb.append("		</tr>\n" );
				}
			}
			sb.append("	</tbody>\n" );
			sb.append("</table>");
			return sb.toString();
		}
		return "";
	}

}
