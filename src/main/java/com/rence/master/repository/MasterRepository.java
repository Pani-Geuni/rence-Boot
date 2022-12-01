/**
 * @author 최진실
 */

package com.rence.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.master.model.MasterEntity;
import com.rence.master.model.MasterVO;

public interface MasterRepository extends JpaRepository<MasterEntity, Object> {

	@Query(nativeQuery=true, value="select * from masterinfo where master_id=?1")
	public MasterEntity findByMaster_id(String master_id);
}
