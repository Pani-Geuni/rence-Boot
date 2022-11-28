package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.ReserveUpdateVO;

public interface ReserveAutoUpdateRepository extends JpaRepository<ReserveUpdateVO, Object>{

	@Query(nativeQuery = true, value = "select * from reserveinfo where reserve_state != 'false' and reserve_state != 'cancel'")
	public List<ReserveUpdateVO> selectAll_reserve();

			
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update reserveinfo set reserve_state = 'begin' where reserve_no=?1 and reserve_state != 'false' and reserve_state != 'cancel'")
	public void update_reserve_state_begin(String reserve_no);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update reserveinfo set reserve_state = 'in_use' where reserve_no=?1 and reserve_state != 'false' and reserve_state != 'cancel'")
	public void update_reserve_state_inuse(String reserve_no);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update reserveinfo set reserve_state = 'end' where reserve_no=?1 and reserve_state != 'false' and reserve_state != 'cancel'")
	public void update_reserve_state_end(String reserve_no);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update reserveinfo set reserve_state = 'cancel' where reserve_no=?1")
	public int update_reserve_state_cancel(String reserve_no);

}
