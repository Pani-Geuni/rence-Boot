package com.rence.dashboard.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.CommentListQView;

public interface CommentQListRepository extends JpaRepository<CommentListQView, Object>{

	// 공간 - 문의 리스트
//	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no);

	@Query(nativeQuery = true, value = "SELECT * FROM COMMENTLIST_Q_VIEW WHERE BACKOFFICE_NO=?1")
	public List<CommentListQView> select_all_q(String backoffice_no);


}
