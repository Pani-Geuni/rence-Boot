/**
 * @author 전판근, 최진실
 */

package com.rence.master.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MasterRepository extends JpaRepository<MasterEntity, Object> {

	
	@Query(nativeQuery=true, value="SELECT * FROM MASTERINFO WHERE master_id=?1 AND master_pw=?2")
	public MasterEntity findByMaster_id(String master_id, String master_pw);
}
