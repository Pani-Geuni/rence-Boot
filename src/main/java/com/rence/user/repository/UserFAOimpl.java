/**
* @author 강경석
*/
package com.rence.user.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserFAOimpl implements UserFAO {

	@Autowired
	ServletContext context;

	private String S3Bucket = "rence/user";

	@Autowired
	AmazonS3Client amazonS3Client;

	@Override
	public UserVO FileuploadOK(UserVO vo, MultipartHttpServletRequest mtfRequest, MultipartFile multipartFile_user) {
		log.info("FileuploadOK");
		log.info("vo: {}", vo);
		log.info("{} byte", multipartFile_user.getSize());

		if (multipartFile_user.getSize() > 0) {
			log.info("filename : {}", multipartFile_user.getOriginalFilename());
			List<MultipartFile> imgs = mtfRequest.getFiles("multipartFile");
			log.info("imgs : {}", imgs);
			List<String> img_list = new ArrayList<String>();

			for (MultipartFile mf : imgs) {
				log.info("for문 시작");
				String originFileName = UUID.randomUUID() + "."
						+ StringUtils.getFilenameExtension(mf.getOriginalFilename());
				long fileSize = mf.getSize();

				img_list.add(originFileName); // vo에 저장

				log.info("originFileName : " + originFileName);
				log.info("fileSize : " + fileSize);

				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(mf.getContentType());
				objectMetaData.setContentLength(fileSize);
				try {
					// S3에 업로드 하는 부분
					amazonS3Client.putObject(
							new PutObjectRequest(S3Bucket, originFileName, mf.getInputStream(), objectMetaData)
									.withCannedAcl(CannedAccessControlList.PublicRead));

					String imagePath = amazonS3Client.getUrl(S3Bucket, originFileName).toString();
					log.info("이미지 링크 : {}", imagePath);

					String img_vo = img_list.stream().collect(Collectors.joining(", "));
					vo.setUser_image(img_vo);

				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				log.info("for문 끝");
			}
		} else if (vo.getUser_image() == null) {
			// 아무것도 선택하지 않으면 기본이미지 세팅
			vo.setUser_image("img_host_001.jpg");
		}
		return vo;
	}
}// end class
