/**
	 * @author 김예은
	 
*/
package com.rence.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rence.user.model.ReserveInfo_ViewVO;
import com.rence.user.model.ReviewEntityVO;
import com.rence.user.model.UserDTO;


public interface MypageMenuRepository extends JpaRepository<ReserveInfo_ViewVO, Object> {

	@Query(nativeQuery = true, value = 
			"select * from ("
			+ "select * "
			+ "from ReserveInfoView "
			+ "where reserve_no = ?1 "
			+ "order by mileage_no desc "
			+ ") where rownum=1")
	public ReserveInfo_ViewVO select_one_reserve_info(String reserve_no);

	@Query(nativeQuery = true, value = 
			"SELECT user_no, user_name, user_tel, user_email "
			+ "FROM userinfo "
			+ "where user_no = ?1")
	public UserDTO select_one_user_info(String user_no);
	
	
	@Query(nativeQuery = true, value = 
			"SELECT count(review_no) FROM review "
			+ "where room_no = ?1 and backoffice_no=?2")
	public int is_write_review(String room_no, String backoffice_no);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = 
		"insert into review(review_no, review_content, review_point, review_date, room_no, backoffice_no, user_no) "
		+ "	values('R'||SEQ_REVIEW.nextval, :#{#vo?.review_content}, :review_point, current_date, :#{#vo?.room_no}, :#{#vo?.backoffice_no}, :#{#vo?.user_no})")
	void insert_review(@Param("review_point") Float review_point,@Param("vo") ReviewEntityVO vo);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from review where review_no = ?1")
	public int delete_review(String review_no);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from comments where comment_no = ?1")
	public int delete_comment(String comment_no);

}