/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ScheduleListView;

public interface ScheduleListRepository extends JpaRepository<ScheduleListView, Object> {

	// 일정 관리 - 리스트
	@Query(nativeQuery = true, value = "select * from schedule_list_view where backoffice_no=?1 and ((reserve_stime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))or(reserve_etime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS')))")
	public List<ScheduleListView> backoffice_scheduke_list(String backoffice_no, String reserve_stime, String reserve_etime);

	

}
