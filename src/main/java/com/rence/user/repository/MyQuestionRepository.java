/**
	 * @author 강경석
	 
*/

package com.rence.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserQuestionVO;

public interface MyQuestionRepository extends JpaRepository<UserQuestionVO, Object> {

	// 문의 리스트수-현재(페이징 처리를 위해서)
	@Query(nativeQuery = true, value = "select count(*) from user_commentpage_view where mother_no is null and user_no=?1 order by comment_no desc")
	public long count_question(String user_no);
	
	// 마이페이지 - 문의내역리스트 페이징
	@Query(nativeQuery = true, value = "select * from(select ROWNUM as rn, u.* from (select * from user_commentpage_view where mother_no is null and user_no=?1 order by comment_no desc) u) where rn between ?2 and ?3")
	public List<UserQuestionVO> select_all_question_paging(String user_no, Integer start_row, Integer end_row);
	
	// 마이페이지 - 문의내역리스트(답변)
	@Query(nativeQuery = true, value = "select * from user_commentpage_view where mother_no=?1")
	public UserQuestionVO select_one_answer(String comment_no);
	
	
	

//	// 마이페이지 - 문의내역리스트 - 문의 삭제
//	@Transactional
//	@Modifying
//	@Query(nativeQuery = true, value = "delete from user_commentpage_view where comment_no = ?1")
//	public int delete_comment(String comment_no);
//	
//	****MypageMenuRepository에 구현
	

//	// 마이페이지 - 문의내역리스트
//	@Query(nativeQuery = true, value = "select * from user_commentpage_view where mother_no is null and user_no=?1 order by comment_no desc")
//	public List<UserQuestionVO> select_all_question(String user_no);



	

	
	


}// end class
