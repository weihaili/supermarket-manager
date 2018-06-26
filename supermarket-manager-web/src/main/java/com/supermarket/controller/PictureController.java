package com.supermarket.controller;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supermarket.common.utils.FastDFSClient;
import com.supermarket.common.utils.JsonUtils;

/**
 * @author Admin
 * picture upload controller
 */
@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		Map result = new HashMap<>();
		try {
			//obtain file extension
			String originalFilename = uploadFile.getOriginalFilename();
			String extensionName=originalFilename.substring(originalFilename.lastIndexOf('.')+1);
			
			//upload file to image server who chose .get file storage path,do not contain image server ip
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			String storagePath = fastDFSClient.uploadFile(uploadFile.getBytes(),extensionName);
			//make this file path complete url 
			String url=IMAGE_SERVER_URL+storagePath;
			result.put("error", 0);
			result.put("url", url);
			//package to map return
		} catch (Exception e) {
			result.put("error", 1);
			result.put("message", "fileUpload fail please contact develop check image server");
			System.out.println("file uplead exception please check image server");
			e.printStackTrace();
			return JsonUtils.objectToJson(result);
		}
		return JsonUtils.objectToJson(result); 
	}
	
}
