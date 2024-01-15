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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.project.daily_writing.files.service.FileService;

@Controller
@RequestMapping("/api/files")
public class FileController {
  
   
   private FileService fileService;
   
   String host = "192.168.75.128";
   String user = "user";
   String password = "12345678";
   
   public FileController(FileService fileService) {
	   this.fileService = fileService;
   }
   
   @PostMapping("/upload")
   public ResponseEntity<String> imageUpload(@RequestBody Map<String, String> data) throws JSchException{
      
      try {
         
         String imageInfo = data.get("imageInfo");

            byte[] imageBytes = Base64.getDecoder().decode(imageInfo);

            String uploadDir = "C:\\Users\\USER\\";
            String fileName = data.get("imageName");

            File localFile = new File(uploadDir + fileName);
            FileUtils.writeByteArrayToFile(localFile, imageBytes);

            fileService.uploadToLinuxServer(localFile, "/home/user/images/", host, user, password);
         
         return ResponseEntity.ok("File uploaded successfully.");
      }catch(Exception e) {
         e.printStackTrace();
         
         return ResponseEntity.ok(e.getMessage());
      }
   }
   @PostMapping("/upload-by-id")
   public ResponseEntity<String> imageUploadById(@RequestBody Map<String, String> data) throws JSchException{
      
      try {
         
         String imageInfo = data.get("imageInfo");
         String id		  = data.get("id");
            byte[] imageBytes = Base64.getDecoder().decode(imageInfo);

            String uploadDir = "C:\\Users\\USER\\Desktop\\temp\\";
            String fileName = data.get("imageName");

            File localFile = new File(uploadDir + fileName);
            FileUtils.writeByteArrayToFile(localFile, imageBytes);

            fileService.uploadToLinuxServerIdFolder(localFile, "/home/user/images/", host, user, password,id);
         
         return ResponseEntity.ok("File uploaded successfully.");
      }catch(Exception e) {
         e.printStackTrace();
         
         return ResponseEntity.ok(e.getMessage());
      }
   }
   
   @PostMapping("/delete")
   public ResponseEntity<String> imageDelete(@RequestBody List<String> filePathList) throws JSchException{   
    
      try {
         
            fileService.deleteFile(filePathList, host, user, password);
         
         return ResponseEntity.ok("File deleted successfully.");
      }catch(Exception e) {
         e.printStackTrace();
         
         return ResponseEntity.ok(e.getMessage());
      }
   }
   
   @PostMapping("/create-temp-folder")
   public ResponseEntity<?> createTempFolder(){
	   try {
	         
		   fileService.makeFolderInLinux("/home/user/images/", host, user, password, host);
        
        return new ResponseEntity<>("File deleted successfully.",HttpStatus.OK);
           
     }catch(Exception e) {
        e.printStackTrace();
        
        return ResponseEntity.ok(e.getMessage());
     }
	   
   }
   
   @PutMapping("/update-folder-name")
   public ResponseEntity<?> updateFolderName(@RequestBody Map<String, String> data){
	   try {
		   
		   String id = data.get("id");
	         
		   fileService.changeFolderName("/home/user/images/temp/", host, user, password, id);
        
        return new ResponseEntity<>("File deleted successfully.",HttpStatus.OK);
           
     }catch(Exception e) {
        e.printStackTrace();
        
        return ResponseEntity.ok(e.getMessage());
     }
	   
   }
   
 }