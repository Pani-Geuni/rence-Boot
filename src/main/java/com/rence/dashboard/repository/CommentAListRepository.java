/**
 * 
 * @author 최진실
 *
 */package com.rence.dashboard.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.CommentListAView;
import com.rence.dashboard.model.CommentListQView;

public interface CommentAListRepository extends JpaRepository<CommentListAView, Object>{

	// 공간 답변
	@Query(nativeQuery = true, value = "SELECT * FROM COMMENTLIST_A_VIEW WHERE BACKOFFICE_NO=?1 AND MOTHER_NO=?2")
	public List<CommentListAView> select_all_a(String backoffice_no, String mother_no);

}
