/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.CommentVO;

public interface CommentRepository extends JpaRepository<CommentVO, Object>{

	// 공간 문의 - 답변 팝업
	@Query(nativeQuery = true, value = "select comment_no,mother_no,comment_state,room_name,comment_content, TO_CHAR(comment_date, 'YYYY-MM-DD HH24:MI:SS') as comment_date, c.room_no, c.backoffice_no, user_no, host_no, writer  from comments c left outer join roominfo rm on c.room_no=rm.room_no where c.backoffice_no=?1 and c.room_no=?2 and comment_no=?3")
	public CommentVO backoffice_insert_comment(String backoffice_no, String room_no ,String comment_no);

}
