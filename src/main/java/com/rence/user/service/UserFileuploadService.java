/**
	 * @author 강경석
	 *  이미지 업로드 서비스
	 */

package com.rence.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.user.model.UserVO;
import com.rence.user.repository.UserFAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserFileuploadService {

	@Autowired
	UserFAO fao;


	public UserVO FileuploadOK(UserVO vo, MultipartHttpServletRequest mtfRequest, MultipartFile multipartFile_user) {
		log.info("FileuploadOK");
		log.info("uvo: {}", vo);
		log.info("multipartFile_img: {}", multipartFile_user);
		
	
		return fao.FileuploadOK(vo, mtfRequest, multipartFile_user);
	}	

}//end class
