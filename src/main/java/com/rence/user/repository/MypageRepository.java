/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserMypageVO;

public interface MypageRepository extends JpaRepository<UserMypageVO, Object>  {

	//user_mileage_selectOne
	@Query(nativeQuery = true, value= "select t from UserMypageVO t where t.user_no = ?1")
	UserMileageVO findByUser_no(String user_no);
	
	

}//end class
