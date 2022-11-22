/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserVO;

public interface MileageRepository extends JpaRepository<UserMileageVO, Object> {

	// 총마일리지 조회
	@Query(nativeQuery = true, value = "select * from( select * from USER_DETAIL_MILEAGE_VIEW where state !='W' and user_no=?1 order by no desc)WHERE ROWNUM between 1 and 1")
	public UserMileageVO totalMileage_selectOne(String user_no);

	// 마일리지 리스트수-전체(페이징 처리를 위해서) all
	@Query(nativeQuery = true, value = "select count(*) ffrom user_detail_mileage_view where user_no = ?1")
	public long count_allmileage(String user_no);
	
	// 마일리지 리스트수-적립(페이징 처리를 위해서) plus
	@Query(nativeQuery = true, value = "select count(*) ffrom user_detail_mileage_view where user_no = ?1 and state='T'")
	public long count_plusmileage(String user_no);
	
	// 마일리지 리스트수-사용(페이징 처리를 위해서) minus
	@Query(nativeQuery = true, value = "select count(*) ffrom user_detail_mileage_view where user_no = ?1 and state='F'")
	public long count_minusmileage(String user_no);

	
	// 마일리지 리스트 all 페이징
	@Query(nativeQuery = true, value = "select * from (select * from user_detail_mileage_view where user_no = ?1 order by no desc) where rownum between ?2 and ?3")
	public List<UserMileageVO> user_mileage_selectAll_paging(String user_no, Integer start_row, Integer end_row);
	
	
	
	// user_mileage_search_list paging
	@Query(nativeQuery = true, value = "select * from (select * from user_detail_mileage_view where user_no = ?1 and state='T' order by no desc) where rownum between ?2 and ?3")
	public List<UserMileageVO> mileage_search_list_plus_paging(String user_no, Integer start_row, Integer end_row);
	
	@Query(nativeQuery = true, value = "select * from (select * from user_detail_mileage_view where user_no = ?1 and state='F' order by no desc) where rownum between ?2 and ?3")
	public List<UserMileageVO> mileage_search_list_minus_paging(String user_no, Integer start_row, Integer end_row);

	
	
//	// user_mileage_selectAll
//	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 order by no desc")
//	List<UserMileageVO> mileage_selectAll(String user_no);
//
//	// user_mileage_search_list
//	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 order by no desc")
//	public List<UserMileageVO> mileage_search_list_all(String user_no);
//
//	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 and state='T' order by no desc")
//	public List<UserMileageVO> mileage_search_list_plus(String user_no);
//
//	@Query(nativeQuery = true, value = "select * from user_detail_mileage_view where user_no = ?1 and state='F' order by no desc")
//	public List<UserMileageVO> mileage_search_list_minus(String user_no);

	
	


	


}// end class
