package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeReviewVO;

public interface OfficeReviewRepository extends JpaRepository<OfficeReviewVO, Object> {
	
	
	@Query(nativeQuery = true,
			value="select * from review_list_view where backoffice_no=?1")
	public List<OfficeReviewVO> select_all_review(String backoffice_no);
	
}
