/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.CommentInsertVO;
import com.rence.dashboard.model.CommentVO;

public interface CommentInsertRepository extends JpaRepository<CommentInsertVO, Object> {

	// 공간 문의 - 답변 작성 :#{#rvo?.room_name}
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into comments(comment_no, room_no, backoffice_no, mother_no, comment_content, comment_date, writer, comment_state, host_no) values ('C'||SEQ_COMMENTS.nextval, :#{#cvo?.room_no}, :#{#cvo?.backoffice_no}, :#{#cvo?.mother_no}, :#{#cvo?.comment_content}, :comment_date, :#{#cvo?.writer}, :#{#cvo?.comment_state}, :#{#cvo?.host_no})")
	public int backoffice_insertOK_comment(@Param("cvo") CommentInsertVO cvo, @Param("comment_date") Date comment_date);

	// 공간 문의 - 문의 상태 'T'변경
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update comments set comment_state='T' where backoffice_no=?1 and comment_no=?2")
	public Object update_comment_state_T(String backoffice_no, String comment_no);

	// 공간 문의 - 답변 삭제
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM comments where backoffice_no=?1 and comment_no=?2")
	public int backoffice_deleteOK_comment(String backoffice_no, String comment_no);

	// 공간 문의 - 문의 상태 'F'변경
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update comments set comment_state='F' where backoffice_no=?1 and comment_no=?2")
	public void update_comment_state_F(String backoffice_no, String mother_no);

}
