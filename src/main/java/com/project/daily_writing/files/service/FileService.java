package com.project.daily_writing.files.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.project.daily_writing.files.vo.FileDeleteDto;

public interface FileService {
	
	public void uploadToLinuxServer(File localFile, String remoteDir, String host, String username, String password)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void deleteFile(FileDeleteDto fileDeleteDto, String host, String username, String password)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void deleteFileById(FileDeleteDto fileDeleteDto, String host, String username, String password)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void deleteFileAll(FileDeleteDto fileDeleteDto, String host, String username, String password)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void uploadToLinuxServerIdFolder(File localFile, String remoteDir, String host, String username, String password,String id)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void makeFolderInLinux(String remoteDir, String host, String username, String password,String id)
			throws JSchException, SftpException, FileNotFoundException;
	
	public void changeFolderName(String remoteDir, String host, String username, String password,String id) throws JSchException, SftpException, FileNotFoundException;
}
