/**
 * @author 전판근
 */

package com.rence.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.master.model.MasterEntity;
import com.rence.master.model.MasterRepository;
import com.rence.master.model.MasterVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MasterService {

	@Autowired
	MasterRepository repository;
	
	public MasterService() {
		log.info("MasterService()...");
	}
	
	public MasterEntity master_login(MasterVO vo) {
		
		return repository.findByMaster_id(vo.getMaster_id(), vo.getMaster_pw());
	}
	
	
}
