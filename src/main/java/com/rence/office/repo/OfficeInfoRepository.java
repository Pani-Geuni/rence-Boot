/**
 * @author 김예은
*/
package com.rence.office.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rence.office.model.Comment_EntityVO;
import com.rence.office.model.ListViewVO;

public interface OfficeInfoRepository extends JpaRepository<ListViewVO, Object> {


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
			"insert into comments(comment_no, mother_no, comment_content, comment_date, room_no, backoffice_no, user_no, host_no, is_secret) "
			+ "		values('C'||SEQ_COMMENTS.nextval, null, :#{#vo2?.comment_content}, sysdate, :#{#vo2?.room_no}, :#{#vo2?.backoffice_no}, :#{#vo2?.user_no}, null, :#{#vo2?.is_secret})")
	public int insert_question(@Param("vo2") Comment_EntityVO vo2);

}