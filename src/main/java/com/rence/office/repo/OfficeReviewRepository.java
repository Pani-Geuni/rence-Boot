package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeReviewVO;

public interface OfficeReviewRepository extends JpaRepository<OfficeReviewVO, Object> {
	
	
	@Query(nativeQuery = true,
			value="select * from ("
					+ "select ROWNUM as rn, c.* "
					+ "from ( "
					+ "select * "
					+ "from review_list_view "
					+ "where backoffice_no=?1 "
					+ "order by review_no desc) c "
					+ ") where rn between ?2 and ?3")
	public List<OfficeReviewVO> select_all_review(String backoffice_no, Integer start_row, Integer end_row);
	
	
	@Query(nativeQuery = true, 
			value="select count(*) from review_list_view where backoffice_no=?1")
	public long total_rowCount_review_all(String backoffice_no);
	
}
