package com.supermarket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * @author Admin
 *picture upload images server test
 */
public class FTPTest {
	
	@Test
	public void testFtp() throws SocketException, IOException {
		//create ftp client object
		FTPClient ftpClient = new FTPClient();
		//create ftp connection port default 21
		ftpClient.connect("192.168.0.25", 21);
		//login ftp server use username and password
		ftpClient.login("ftpuser", "ftpuser001");
		//set upload road
		ftpClient.changeWorkingDirectory("/home/liweihai/images");
		//read local file in inputStream 
		InputStream inputStream = new FileInputStream(new File("D:"+File.separator+"temp"+File.separator+"test.PNG"));
		//upload file or picture  remote:images server save picture name
		// local: upload file stream
		ftpClient.storeFile("hello1.jpg", inputStream);
		//close connection
		ftpClient.logout();
	}
	
	
	

}
