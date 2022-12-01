/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReviewListView;

public interface ReviewRepository extends JpaRepository<ReviewListView, Object>{

	@Query(nativeQuery = true, value = " select * from (select rownum as rnum, review_no, review_content, review_point, TO_CHAR(review_date, 'YYYY-MM-DD HH24:MI:SS') as review_date, user_no, user_image, substr(user_name,1,1)||lpad('*',length(user_name)-1,'*') as user_name"
			+ "		from( select * from review_list_b_view where backoffice_no=?1 order by review_date desc)A"
			+ "		where A.no=1 ) where rnum between ?2 and ?3")
	public List<ReviewListView> backoffice_review_selectAll(String backoffice_no, Integer start_row, Integer end_row);

	// 후기 리스트 갯수
	@Query(nativeQuery = true, value = " select count(*) from (select rownum as rnum, review_no, review_content, review_point, TO_CHAR(review_date, 'YYYY-MM-DD HH24:MI:SS') as review_date, user_no, user_image, substr(user_name,1,1)||lpad('*',length(user_name)-1,'*') as user_name"
			+ "		from( select * from review_list_b_view where backoffice_no=?1)A"
			+ "		where A.no=1 order by review_date desc )")
	public long backoffice_review_selectAll_cnt(String backoffice_no);

}
