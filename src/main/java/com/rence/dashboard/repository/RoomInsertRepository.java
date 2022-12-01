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

import com.rence.dashboard.model.RoomInsertVO;
import com.rence.dashboard.model.RoomVO;


public interface RoomInsertRepository extends JpaRepository<RoomInsertVO, Object>{

	//공간 관리 - 추가	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO roominfo(room_no, room_name, room_type, backoffice_no, room_price) VALUES('RM'||SEQ_ROOM.NEXTVAL, :#{#rvo?.room_name}, :#{#rvo?.room_type}, :#{#rvo?.backoffice_no}, :room_price)")
	public int backoffice_insertOK_room(@Param("rvo") RoomInsertVO rvo, @Param("room_price") Integer room_price);

	//공간 관리 - 수정
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update roominfo set room_name=:#{#rvo?.room_name}, room_type=:#{#rvo?.room_type}, room_price=:room_price where backoffice_no=:#{#rvo?.backoffice_no} and room_no=:#{#rvo?.room_no}")
	public int backoffice_updateOK_room(@Param("rvo") RoomInsertVO rvo, @Param("room_price") Integer room_price);

	// 휴무 일정 - 공간 이름
	@Query(nativeQuery = true, value = "select * from roominfo where room_no=?1")
	public RoomInsertVO backoffice_schedule_calendar_room_name(String room_no);


	
	
	  		
	
}
