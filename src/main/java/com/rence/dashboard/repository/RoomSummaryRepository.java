/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.RoomSummaryView;

public interface RoomSummaryRepository extends JpaRepository<RoomSummaryView, Object>{ // 공간 요약

	@Query(nativeQuery = true, value = "select round(avg(review_point),1),backoffice_no from review group by backoffice_no having backoffice_no=?1")
	public Float select_avg_review_point(String backoffice_no);

   @Query(nativeQuery = true, value = "select count(NVL(comment_no),0),backoffice_no from comments where user_no is not null group by backoffice_no having backoffice_no=?1")
   public Integer select_comment_cnt(String backoffice_no);

   @Query(nativeQuery = true, value = "select count(NVL(review_no,0)),backoffice_no from review group by backoffice_no having backoffice_no=?1")
   public Integer select_review_cnt(String backoffice_no);

   @Query(nativeQuery = true, value = "select count(NVL(reserve_no,0)),backoffice_no from reserveinfo group by backoffice_no having backoffice_no=?1")
   public Integer select_reserve_cnt(String backoffice_no);

}