package com.project.daily_writing.files.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.project.daily_writing.files.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Override
	public void uploadToLinuxServer(File localFile, String remoteDir, String host, String username, String password)
			throws JSchException, SftpException, FileNotFoundException {
		
			JSch jsch = new JSch();

	        Session session = jsch.getSession(username, host, 22);
	        session.setPassword(password);
	        Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        session.connect();

	        Channel channel = session.openChannel("sftp");
	        channel.connect();
	        ChannelSftp sftpChannel = (ChannelSftp) channel;
	        
	        Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(remoteDir);
	        	       
	        String tempDir = remoteDir + "temp/";

	        sftpChannel.put(new FileInputStream(localFile), tempDir + localFile.getName(),ChannelSftp.OVERWRITE | ChannelSftp.RESUME);

	        sftpChannel.exit();
	        channel.disconnect();
	        session.disconnect();
	}
	
	@Override
	public void deleteFile(List<String> filePath, String host, String username, String password) 
			throws JSchException, SftpException, FileNotFoundException  {
		
		JSch jsch = new JSch();
		
		Session session = jsch.getSession(username,host,22);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		
		Channel channel = session.openChannel("sftp");
		channel.connect();
		
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		String folderPath ="/home/test/images/temp";
		
		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(folderPath);
		
		
		for(ChannelSftp.LsEntry entry : fileList) {
			
			if(entry.getAttrs().isDir()) {
				continue;
			}
			
			String fileName = entry.getFilename();
			
			if(!filePath.contains(fileName)) {
				sftpChannel.rm(folderPath + "/" + fileName);
			}
		}
		
		sftpChannel.exit();
		session.disconnect();
		
	}
	
	@Override
	public void uploadToLinuxServerIdFolder(File localFile, String remoteDir, String host, String username,
			String password,String id) throws JSchException, SftpException, FileNotFoundException {
		
		JSch jsch = new JSch();

        Session session = jsch.getSession(username, host, 22);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        
        @SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(remoteDir);
	
		boolean subFolderExists = false;
        for(ChannelSftp.LsEntry entry : fileList) {
			
			if(entry.getAttrs().isDir() && entry.getFilename().equals(id)) {
				subFolderExists = true;
                break;
			}
			
			String fileName = entry.getFilename();
			
			if (!subFolderExists) {
				sftpChannel.mkdir(remoteDir  + id);
                System.out.println("폴더 생성 완료: " + id);
            } else {
                System.out.println("폴더 이미 존재함: " + id);
            }
			
			
		}

//        sftpChannel.put(new FileInputStream(localFile), remoteDir + localFile.getName());

        sftpChannel.exit();
        channel.disconnect();
        session.disconnect();
		
	}
	
	@Override
	public void makeFolderInLinux(String remoteDir, String host, String username, String password, String id)
			throws JSchException, SftpException, FileNotFoundException {
		
		JSch jsch = new JSch();

        Session session = jsch.getSession(username, host, 22);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        
//        @SuppressWarnings("unchecked")
//        Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(remoteDir);
//        
//        boolean subFolderExists = false;
//        for(ChannelSftp.LsEntry entry : fileList) {
//			
//			if(entry.getAttrs().isDir() && entry.getFilename().equals("temp")) {
//				subFolderExists = true;
//				
//				if(subFolderExists) {
//					break;
//				}
//			}
//			
//			if (!subFolderExists) {
//				sftpChannel.mkdir(remoteDir  + "temp");
//            } else {
//                System.out.println("폴더 이미 존재함: " + "temp");
//            }
//			
//		}
        try {
        	SftpATTRS attrs = sftpChannel.stat("/home/user/images/temp");
        	if (attrs != null) {
                System.out.println("Folder already exists");
            } else {
            	sftpChannel.mkdir(remoteDir  + "temp");
            }
        	
        }catch(Exception e){
        	sftpChannel.mkdir(remoteDir  + "temp");
        }
        
        
		
	}
	@Override
	public void changeFolderName(String remoteDir, String host, String username, String password, String id)
			throws JSchException, SftpException, FileNotFoundException {
		
			JSch jsch = new JSch();
	        Session session = jsch.getSession(username, host, 22);
	        session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	
	        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
	        channelSftp.connect();
	
	        // 폴더 이름 변경
	        channelSftp.rename(remoteDir, "/home/user/images/" + id);
	
	        channelSftp.disconnect();
	        session.disconnect();
	
		
	}
}
