package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeQuestionVO;

public interface OfficeQuestionRepository extends JpaRepository<OfficeQuestionVO, Object> {

	
	@Query(nativeQuery = true,
			value="select * from question_list_view where mother_no is null and backoffice_no=?1 order by comment_no desc")
	public List<OfficeQuestionVO> select_all_comment(String backoffice_no);
	
	
	@Query(nativeQuery = true, 
			value="select * from question_list_view where mother_no=?1")
	public OfficeQuestionVO select_one_answer(String comment_no);
}
