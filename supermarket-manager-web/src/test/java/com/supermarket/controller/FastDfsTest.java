package com.supermarket.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.supermarket.common.utils.FastDFSClient;

/**
 * @author Admin
 * FastDfs upload test
 */
public class FastDfsTest {

	@Test
	public void testFastDfs() throws FileNotFoundException, IOException, MyException {
		//create an configuration of tracker server address
		//use global object load this configuration
		ClientGlobal.init("D:"+File.separator+"JAVA"+File.separator+"oxygen-project"+File.separator+"supermarket-manager"+File.separator+"supermarket-manager-web"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"conf"+File.separator+"client.conf");
		//create an tracker object
		TrackerClient trackerClient = new TrackerClient();
		//get trackerServer by trackerClient
		TrackerServer trackerServer = trackerClient.getConnection();
		//create an storageServer reference,can be null
		StorageServer storageServer=null;
		//create storageClient ,need parameter treackerServer reference and storageServer reference
		StorageClient storageClient=new StorageClient(trackerServer, storageServer);
		//use storageClient upload file
		String[] strings = storageClient.upload_file("D:"+File.separator+"temp"+File.separator+"table_tree_thefes_bg5.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception {
		FastDFSClient fastDFSClient=new FastDFSClient("D:"+File.separator+"JAVA"+File.separator+"oxygen-project"+File.separator+"supermarket-manager"+File.separator+"supermarket-manager-web"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"conf"+File.separator+"client.conf");
		String string = fastDFSClient.uploadFile("D:"+File.separator+"temp"+File.separator+"sunset.jpg");
		System.out.println(string);
	}
}
