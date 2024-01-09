package com.project.daily_writing.files.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Controller
@RequestMapping("/api/files")
public class FileController {
   
   private static final String UPLOAD_DIR ="/home/test/images/";
   private static final String localFilePath= "C:\\Users\\hr\\Desktop\\Nginx-Logo-02.png";
   
   @PostMapping("/upload")
   public ResponseEntity<String> imageUpload(@RequestBody Map<String, String> data) throws JSchException{
      
      String host = "192.168.67.128";
        String user = "test";
        String password = "test1234";
        int port = 22; // SFTP 포트는 일반적으로 22
      
      JSch jsch = new JSch();
      Session session = jsch.getSession(user, host, port);;
        ChannelSftp channelSftp = null;
      try {
         
         String imageInfo = data.get("imageInfo");

            // 이미지 정보를 Base64 디코딩
            byte[] imageBytes = Base64.getDecoder().decode(imageInfo);

            // 임시로 파일 저장
            String uploadDir = "C:\\Users\\hr\\Desktop\\";
            String fileName = data.get("imageName");

            File localFile = new File(uploadDir + fileName);
            FileUtils.writeByteArrayToFile(localFile, imageBytes);

            // Linux 서버로 업로드
            uploadToLinuxServer(localFile, "/home/test/images/", host, user, password);
   
//         
//         session = jsch.getSession(user, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//           
//           
//           channelSftp = (ChannelSftp) session.openChannel("sftp");
//            channelSftp.connect();
//
//            channelSftp.cd(UPLOAD_DIR); // 원격 디렉토리로 이동
//            Resource f =file.getResource();
//            String filePath = file.getResource().getFile().getPath().toString();
//            channelSftp.put(filePath, channelSftp.pwd()); // 파일 업로드
//      
//         byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOAD_DIR + "/" + file.getOriginalFilename());
//            Files.write(path, bytes);
         
         return ResponseEntity.ok("File uploaded successfully.");
      }catch(Exception e) {
         e.printStackTrace();
         return ResponseEntity.ok("Failed");
      }
   }
   
   private void uploadToLinuxServer(File localFile, String remoteDir, String host, String username, String password) throws JSchException, SftpException, FileNotFoundException {
        JSch jsch = new JSch();

        // SSH 연결
        Session session = jsch.getSession(username, host, 22);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        // SFTP 채널 열기
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;

        // 리모트 디렉터리로 파일 전송
        sftpChannel.put(new FileInputStream(localFile), remoteDir + localFile.getName());

        // 연결 해제
        sftpChannel.exit();
        channel.disconnect();
        session.disconnect();
    }
 }