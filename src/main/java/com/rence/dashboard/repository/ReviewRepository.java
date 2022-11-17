package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReviewListVO;

public interface ReviewRepository extends JpaRepository<ReviewListVO, Object>{

	@Query(nativeQuery = true, value = "select * from (select rownum as rnum,  review_no, review_content, review_point, TO_CHAR(review_date, 'YYYY-MM-DD HH24:MI:SS') as review_date, user_image, substr(user_name,1,1)||lpad('*',length(user_name)-2,'*') \r\n"
			+ "		from(\r\n"
			+ "			select ROW_NUMBER() OVER(PARTITION BY rv.review_no ORDER BY rv.review_no ASC ) no, review_no, review_content, review_point, review_date, user_image, user_name \r\n"
			+ "			from review rv left outer join userinfo u on rv.user_no=u.user_no\r\n"
			+ "			where backoffice_no=?1)A\r\n"
			+ "		where A.no=1 order by review_date desc) where rnum between ?2 and ?3")
	List<ReviewListVO> backoffice_review_selectAll(String backoffice_no, Integer start_row, Integer end_row);

}
