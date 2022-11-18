/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rence.backoffice.model.BackOfficeVO;

public interface BackOfficeFAO {

	BackOfficeVO backoffice_fileupload(BackOfficeVO vo, MultipartHttpServletRequest mtfRequest,
			MultipartFile multipartFile_room);

	BackOfficeVO host_fileupload(BackOfficeVO vo, MultipartFile multipartFile_host);


}
