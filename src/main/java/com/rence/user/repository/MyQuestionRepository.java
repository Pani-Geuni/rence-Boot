package com.rence.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.QuestionVO;

public interface MyQuestionRepository extends JpaRepository<QuestionVO, Object> {

	// 마이페이지 - 문의내역리스트
	@Query(nativeQuery = true, value = "select * from user_commentpage_view where mother_no is null and user_no=?1 order by comment_no desc")
	public List<QuestionVO> select_all_question(String user_no);

	// 마이페이지 - 문의내역리스트
	@Query(nativeQuery = true, value = "select * from user_commentpage_view where mother_no=?1")
	public QuestionVO select_one_answer(String comment_no);

	// 마이페이지 - 문의내역리스트 - 문의 삭제
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from user_commentpage_view where comment_no = ?1")
	public int delete_comment(String comment_no);

}// end class
