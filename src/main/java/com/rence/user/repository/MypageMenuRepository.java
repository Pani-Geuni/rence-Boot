/**
	 * @author 김예은
	 
*/
package com.rence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.ReserveInfo_ViewVO;
import com.rence.user.model.UserVO;

public interface MypageMenuRepository extends JpaRepository<ReserveInfo_ViewVO, Object> {

	@Query(nativeQuery = true, value = 
			"select * from ReserveInfoView"
			+ "	where reserve_no = ?1"
			+ "	and ROWNUM between 1 and 1")
	public ReserveInfo_ViewVO select_one_reserve_info(String reserve_no);

	@Query(nativeQuery = true, value = 
			"select user_no, user_name, user_tel, user_email "
			+ "from userinfo where user_no = ?1")
	public UserVO select_one_user_info(String user_no);

}