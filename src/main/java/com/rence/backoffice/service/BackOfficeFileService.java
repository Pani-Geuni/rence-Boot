/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.repository.BackOfficeFAO;


@Service
public class BackOfficeFileService {
	
	@Autowired
	BackOfficeFAO fao;

	public BackOfficeVO backoffice_image_upload(BackOfficeVO vo, MultipartHttpServletRequest mtfRequest,MultipartFile multipartFile_room) {
		return fao.backoffice_fileupload(vo,mtfRequest,multipartFile_room);
	}

	public BackOfficeVO host_image_upload(BackOfficeVO vo, MultipartFile multipartFile_host) {
		return fao.host_fileupload(vo,multipartFile_host);
	}
	
}
