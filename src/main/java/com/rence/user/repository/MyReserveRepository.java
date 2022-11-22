/**
	 * @author 강경석
	 
*/

package com.rence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.MyPageReserveListVO;

public interface MyReserveRepository extends JpaRepository<MyPageReserveListVO,Object> {

	// 예약 리스트수-현재(페이징 처리를 위해서)
			@Query(nativeQuery = true, 
					value= "select count(*) from USER_RESERVE_VIEW where user_no = ?1 and payment_total is not null and sysdate <= reserve_edate ")
			public long count_nowReserve();
	
	
	//마이페이지- 현재 예약현황 리스트 가져오기
		@Query(nativeQuery = true, 
				value= "select * from USER_RESERVE_VIEW where user_no = ?1 and payment_total is not null and sysdate <= reserve_edate "
						+ "and ROWNUM between 1 and 1")
//				value = "select leftdb.reserve_no, leftdb.user_no, leftdb.reserve_sdate, leftdb.reserve_edate, leftdb.company_name, leftdb.roadname_address, leftdb.backoffice_image, p.payment_total \r\n"
//						+ "		from(\r\n"
//						+ "		    select r.reserve_no, r.user_no,r.reserve_sdate, r.reserve_edate, b.company_name, b.roadname_address, b.backoffice_image\r\n"
//						+ "		    from reserveinfo r left join backofficeinfo b \r\n"
//						+ "		    on r.backoffice_no = b.backoffice_no\r\n"
//						+ "		) leftdb left join paymentinfo p\r\n"
//						+ "		on leftdb.reserve_no = p.reserve_no\r\n"
//						+ "		where leftdb.user_no = ?1 AND p.payment_total is not null AND sysdate <= leftdb.reserve_edate")
		public List<MyPageReserveListVO> select_all_now_reserve_list(String user_no);

		
		
		
		
		//마이페이지- 과거 예약현황 리스트
		@Query(nativeQuery = true, 
				value= "select * from USER_RESERVE_VIEW where user_no = ?1 "
						+ "and payment_total is not null and sysdate <= reserve_edate")
		public List<MyPageReserveListVO> select_all_before_reserve_list(String user_no);




		
	
}//end class
