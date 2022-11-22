/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//import com.rence.dashboard.model.RoomInsertVO;
import com.rence.dashboard.model.RoomVO;

public interface RoomRepository extends JpaRepository<RoomVO, Object> {

	// 공간 관리 - 리스트
	@Query(nativeQuery = true, value = "select room_no, room_type, room_name, TO_CHAR(room_price) as room_price, backoffice_no from roominfo where backoffice_no=?1 order by room_no asc")
	public List<RoomVO> selectAll_room_list(String backoffice_no);

	// 공간 수정 - 팝업
	@Query(nativeQuery = true, value = "select * from roominfo where backoffice_no=?1 and room_no=?2")
	public RoomVO select_one_room_info(String backoffice_no, String room_no);

	//공간 삭제
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM roominfo where backoffice_no=?1 and room_no=?2 and room_no not in (select room_no from reserveinfo where backoffice_no=?1 and room_no=?2 and (reserve_state='begin' or reserve_state='in_use'))")
	public void backoffice_deleteOK_room(String backoffice_no, String room_no);
	

}
