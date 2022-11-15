package com.rence.backoffice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.backoffice.model.BackOfficeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BackOfficeFileService {

	 @Autowired
	   ServletContext context;

	   public BackOfficeVO backoffice_fileupload(BackOfficeVO vo, MultipartHttpServletRequest mtfRequest,MultipartFile multipartFile_room) {
	      log.info("{} byte", multipartFile_room.getSize());

	      if (multipartFile_room.getSize() > 0) {
	         log.info("{} byte", multipartFile_room.getOriginalFilename());
	         List<MultipartFile> imgs = mtfRequest.getFiles("multipartFile_room");

	         String dir_path = context.getRealPath("resources/upload");
	         log.info(dir_path);
	         
	         List<String> img_list = new ArrayList<String>();
	         for (MultipartFile mf : imgs) {
	            img_list.add(mf.getOriginalFilename());

	            String originFileName = mf.getOriginalFilename(); 
	            long fileSize = mf.getSize(); 

	            System.out.println("originFileName : " + originFileName);
	            System.out.println("fileSize : " + fileSize);

	            String saveFile = dir_path +"/"+ originFileName;
	            try {
	               mf.transferTo(new File(saveFile));
	               String img_vo = img_list.stream().collect(Collectors.joining(", "));
	               vo.setBackoffice_image(img_vo);
	            } catch (IllegalStateException e) {
	               e.printStackTrace();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	         

	      } else if (vo.getBackoffice_image() == null) {
	            vo.setBackoffice_image("img_room_001.jpg");
	            String dir_path = context.getRealPath("resources/upload");
	            log.info(dir_path);

	            File saveFile = new File(dir_path + "/", vo.getBackoffice_image());
	            try {
	            	multipartFile_room.transferTo(saveFile);
	            } catch (IllegalStateException e) {
	               e.printStackTrace();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         
	      }
	      return vo;
	   }

	   public BackOfficeVO host_fileupload(BackOfficeVO vo, MultipartFile multipartFile_host) {
	      log.info("{} byte", multipartFile_host.getSize());

	      if (multipartFile_host.getSize() > 0) {
	         log.info("{} byte", multipartFile_host.getOriginalFilename());

	         vo.setHost_image(multipartFile_host.getOriginalFilename());
	      } else {
	         if (vo.getHost_image() == null) {
	            vo.setHost_image("img_host_001.jpg");
	         }
	         String dir_path = context.getRealPath("resources/upload");
	         log.info(dir_path);

	         File saveFile = new File(dir_path + "/", vo.getHost_image());

//	         String formmatName = vo.getBackoffice_image().substring(vo.getBackoffice_image().lastIndexOf(".") + 1);
//	         log.info("formmatName:{}", formmatName);

	         try {
	        	 multipartFile_host.transferTo(saveFile);
	         } catch (IllegalStateException e) {
	            e.printStackTrace();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }

	      }
	      return vo;
	   }
}
