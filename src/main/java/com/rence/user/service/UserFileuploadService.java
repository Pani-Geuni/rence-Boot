/**
	 * @author 강경석
	 *  이미지 업로드 서비스
	 */

package com.rence.user.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserFileuploadService {
	
	@Autowired
	ServletContext context;


	public UserVO FileuploadOK(UserVO vo, MultipartHttpServletRequest mtfRequest, MultipartFile multipartFile) {
		log.info("uvo: {}", vo);
		log.info("multipartFile_img: {}", multipartFile);
		
		
		if ((multipartFile.getSize() > 0)) {
			log.info("{} byte", multipartFile.getOriginalFilename());
			vo.setUser_image(multipartFile.getOriginalFilename());
			
			String dir_path = context.getRealPath("resources/upload");
			log.info(dir_path);

			File saveFile = new File(dir_path + "/", vo.getUser_image());

			//파일포맷추출 ex)png,jpg
			String formatName = vo.getUser_image().substring(vo.getUser_image().lastIndexOf(".") + 1); 
																							 
			log.info("formatName: {}", formatName);

			try {
				multipartFile.transferTo(saveFile); //원본이미지 저장.

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // 파일이 넘어왔을때만 처리하는 분기.

		else {
			if (vo.getUser_image() == null) {
				vo.setUser_image("img_host_001.jpg");
			}

		}
		return vo;
	
	}

}//end class
