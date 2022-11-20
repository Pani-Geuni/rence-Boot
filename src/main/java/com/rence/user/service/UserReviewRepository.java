/**
	 * @author 강경석
	 
*/

package com.rence.user.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserReviewVO;

public interface UserReviewRepository extends JpaRepository<UserReviewVO, Object>  {

	
	//마이페이지 - 리뷰리스트
	@Query(nativeQuery = true, value = "select * from user_review_view where user_no = ?1")
	List<UserReviewVO> select_all_review(String user_no);

}//end class
