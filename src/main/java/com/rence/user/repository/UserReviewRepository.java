/**
 * @author 강경석
*/

package com.rence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserReviewVO;

public interface UserReviewRepository extends JpaRepository<UserReviewVO, Object>  {

	// 리뷰 리스트수(페이징 처리를 위해서)
	@Query(nativeQuery = true, value = "select count(*) from user_review_view where user_no = ?1")
	long total_rowCount_review(String user_no);
	
	
	//마이페이지 - 리뷰리스트 페이징
	@Query(nativeQuery = true, value = "select  * from (select ROWNUM as rn, u.* from (select * from user_review_view where user_no = ?1 order by review_no desc) u) where rn between ?2 and ?3")
	List<UserReviewVO> select_all_review_paging(String user_no, Integer start_row, Integer end_row);
	
	//마이페이지 - 리뷰리스트
	@Query(nativeQuery = true, value = "select * from user_review_view where user_no = ?1")
	List<UserReviewVO> select_all_review(String user_no);

}//end class
