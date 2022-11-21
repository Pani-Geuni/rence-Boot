package com.rence.user.repository;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.user.model.UserVO;

public interface UserFAO {

	
	public UserVO FileuploadOK(UserVO vo, MultipartHttpServletRequest mtfRequest, MultipartFile multipartFile_user);
}//end class
