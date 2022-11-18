/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.service.BackOfficeFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BackOfficeFAOImpl implements BackOfficeFAO {

	@Autowired
	ServletContext context;

	private String S3Bucket = "rence/space"; // Bucket 이름

	@Autowired
	AmazonS3Client amazonS3Client;

	@Override
	public BackOfficeVO backoffice_fileupload(BackOfficeVO vo, MultipartHttpServletRequest mtfRequest,
			MultipartFile multipartFile_room) {
		log.info("{} byte", multipartFile_room.getSize());

		if (multipartFile_room.getSize() > 0) {
			log.info("{} byte", multipartFile_room.getOriginalFilename());
			List<MultipartFile> imgs = mtfRequest.getFiles("multipartFile_room");

			List<String> img_list = new ArrayList<String>();
			for (MultipartFile mf : imgs) {
				img_list.add(UUID.randomUUID() + "-" + mf.getOriginalFilename()); // vo에 저장

				String originFileName = UUID.randomUUID() + "-" + mf.getOriginalFilename();
				long fileSize = mf.getSize();

				System.out.println("originFileName : " + originFileName);
				System.out.println("fileSize : " + fileSize);


				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(mf.getContentType());
				objectMetaData.setContentLength(fileSize);
				try {

					amazonS3Client.putObject(
							new PutObjectRequest(S3Bucket, originFileName, mf.getInputStream(), objectMetaData)
									.withCannedAcl(CannedAccessControlList.PublicRead));

					String imagePath = amazonS3Client.getUrl(S3Bucket, originFileName).toString(); // 접근가능한 URL 가져오기
					log.info("이미지 링크 : {}",imagePath);
//					img_list.add(imagePath);

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
//			String dir_path = context.getRealPath("resources/upload");
//			log.info(dir_path);
//
//			File saveFile = new File(dir_path + "/", vo.getBackoffice_image());
//			try {
//				multipartFile_room.transferTo(saveFile);
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}

		return vo;
	}

	@Override
	public BackOfficeVO host_fileupload(BackOfficeVO vo, MultipartFile multipartFile_host) {
//		log.info("{} byte", multipartFile_host.getSize());
//
//		if (multipartFile_host.getSize() > 0) {
//			log.info("{} byte", multipartFile_host.getOriginalFilename());
//
//			vo.setHost_image(multipartFile_host.getOriginalFilename());
//		} else {
//			if (vo.getHost_image() == null) {
				vo.setHost_image("img_host_001.jpg");
//			}
//			String dir_path = context.getRealPath("resources/upload");
//			log.info(dir_path);
//
//			File saveFile = new File(dir_path + "/", vo.getHost_image());
//
//			try {
//				multipartFile_host.transferTo(saveFile);
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
		return vo;
	}
}
