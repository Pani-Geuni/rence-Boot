/**
	 * @author 김예은
	 
*/
package com.rence.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.ReserveInfo_ViewVO;
import com.rence.user.model.UserShortInfo_ViewVO;
import com.rence.user.model.UserVO;

public interface MypageMenuRepository extends JpaRepository<ReserveInfo_ViewVO, Object> {

	@Query(nativeQuery = true, value = 
			"select * from ReserveInfoView"
			+ "	where reserve_no = ?1"
			+ "	and ROWNUM between 1 and 1")
	public ReserveInfo_ViewVO select_one_reserve_info(String reserve_no);

	@Query(nativeQuery = true, value = 
			"select * from USER_SHORT_INFO_VIEW where user_no = ?1")
	public UserShortInfo_ViewVO select_one_user_info(String user_no);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from review where review_no = ?1")
	public int delete_review(String review_no);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from comments where comment_no = ?1")
	public int delete_comment(String comment_no);

}