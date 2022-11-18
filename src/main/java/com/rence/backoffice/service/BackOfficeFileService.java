/**
 * 
 * @author 최진실
 *
 */
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
import com.rence.backoffice.repository.BackOfficeFAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BackOfficeFileService {
	
	@Autowired
	BackOfficeFAO fao;

	public BackOfficeVO backoffice_image_upload(BackOfficeVO vo, MultipartHttpServletRequest mtfRequest,MultipartFile multipartFile_room) {
		// TODO Auto-generated method stub
		return fao.backoffice_fileupload(vo,mtfRequest,multipartFile_room);
	}

	public BackOfficeVO host_image_upload(BackOfficeVO vo, MultipartFile multipartFile_host) {
		// TODO Auto-generated method stub
		return fao.host_fileupload(vo,multipartFile_host);
	}
	
}
