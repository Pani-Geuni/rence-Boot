/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserMileageVO;

public interface MileageRepository extends JpaRepository<UserMileageVO, Object> {

	// 총마일리지 조회
	@Query(nativeQuery = true, 
			value = "select * from( select * from USER_DETAIL_MILEAGE_VIEW where state !='W' and user_no=?1 order by no desc)WHERE ROWNUM between 1 and 1")
	public UserMileageVO totalMileage_selectOne(String user_no);

	// user_mileage_selectAll
	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1")
	List<UserMileageVO> mileage_selectAll(String user_no);

	// user_mileage_search_list
	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1")
	public List<UserMileageVO> mileage_search_list_all(String user_no);

	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 and state='T'")
	public List<UserMileageVO> mileage_search_list_plus(String user_no);

	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 and state='F'")
	public List<UserMileageVO> mileage_search_list_minus(String user_no);

}// end class
