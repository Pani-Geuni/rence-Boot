/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.CommentSummaryView;

public interface CommentSummaryRepository extends JpaRepository<CommentSummaryView, Object>{ // 문의 요약

	@Query(nativeQuery = true, value = "select * from COMMENTS_SUMMARY_VIEW where backoffice_no=?1")
	public List<CommentSummaryView> comment_summary_selectAll(String backoffice_no);

}
