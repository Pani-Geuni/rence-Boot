/**
 * @author 김예은
*/
package com.rence.office.repo;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rence.office.model.Comment_EntityVO;
import com.rence.office.model.ListViewVO;
import com.rence.user.model.ReviewEntityVO;

public interface OfficeInfoRepository extends JpaRepository<ListViewVO, Object> {

//	public OfficeInfoVO select_one_office_info(String backoffice_no);
//
//	public OfficeOperatingTimeVO_date select_one_operating_time(String backoffice_no);
//	
//	public List<OfficeRoomVO> select_all_room(String backoffice_no);
//	
//	public List<OfficeQuestionVO> select_all_comment(String backoffice_no);
//	
//	public OfficeQuestionVO select_one_answer(String mother_no);
//	
//	public List<OfficeReviewVO> select_all_review(String backoffice_no);
//	
//	public int check_reserve(OfficeReserveVO vo) throws ParseException;
//	
//	public String select_one_last_reserve(String user_no);
//	
//	public PaymentInfoVO select_one_final_payment_info(String reserve_no);
//	
//	public int reserve_paymentOK(OfficePaymentVO pvo);
//	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = 
		"insert into review(review_no, review_content, review_point, review_date, room_no, backoffice_no, user_no) "
		+ "	values('R'||SEQ_REVIEW.nextval, :#{#vo?.review_content}, :review_point, sysdate, :#{#vo?.room_no}, :#{#vo?.backoffice_no}, :#{#vo?.user_no})")
	void insert_review(@Param("review_point") Float review_point,@Param("vo") ReviewEntityVO vo);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = 
			"insert into comments(comment_no, mother_no, comment_content, comment_date, room_no, backoffice_no, user_no, host_no) "
			+ "		values('C'||SEQ_COMMENTS.nextval, null, :#{#vo2?.comment_content}, sysdate, :#{#vo2?.room_no}, :#{#vo2?.backoffice_no}, :#{#vo2?.user_no}, null)")
	public int insert_question(@Param("vo2") Comment_EntityVO vo2);

}