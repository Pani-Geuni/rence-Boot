package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeQuestionVO;

public interface OfficeQuestionRepository extends JpaRepository<OfficeQuestionVO, Object> {

	
	@Query(nativeQuery = true,
			value="select * from (select ROWNUM as rn, c.* from ( select * from question_list_view where mother_no is null and backoffice_no=?1 order by comment_no desc) c ) where rn between ?2 and ?3")
	public List<OfficeQuestionVO> select_all_comment(String backoffice_no, Integer start_row, Integer end_row);
	
	
	@Query(nativeQuery = true, 
			value="select * from question_list_view where mother_no=?1")
	public OfficeQuestionVO select_one_answer(String comment_no);
	
	@Query(nativeQuery = true, 
			value="select count(*) from question_list_view where  mother_no is null and backoffice_no=?1")
	public long total_rowCount_question_all(String backoffice_no);
}
