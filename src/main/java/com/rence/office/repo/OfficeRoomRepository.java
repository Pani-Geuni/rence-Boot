package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeRoomVO;

public interface OfficeRoomRepository extends JpaRepository<OfficeRoomVO, Object> {
	
	
	@Query(nativeQuery = true, value = "select * from roominfo where backoffice_no=?1")
	public List<OfficeRoomVO> select_all_room_info(String backoffice_no);
}
