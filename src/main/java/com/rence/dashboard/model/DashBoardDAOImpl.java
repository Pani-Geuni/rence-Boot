package com.rence.dashboard.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rence.dashboard.repository.CommentAListRepository;
import com.rence.dashboard.repository.CommentQListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DashBoardDAOImpl implements DashBoardDAO {
	
	@Autowired
	CommentQListRepository cq_repository;
	
	@Autowired
	CommentAListRepository ca_repository;

	@Override
	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer start_row, Integer end_row) {

		List<CommentListQView> qvos = cq_repository.select_all_q(backoffice_no, start_row, end_row);
		log.info("Question:{}",qvos);
		log.info("Question::::{}",qvos.size());
		if (qvos!=null) {
			for (int i = 0; i < qvos.size(); i++) {
				CommentListAView ca_vo = new CommentListAView();
				ca_vo.setMother_no(qvos.get(i).getComment_no());
				String mother_no = ca_vo.getMother_no();
				log.info("mother_no::::{}",mother_no);
				List<CommentListAView> avos = ca_repository.select_all_a(backoffice_no,mother_no);
				log.info("Answer:{}",avos);
				
				if (avos!=null) {
					for (int j = 0; j < avos.size(); j++) {
						
							qvos.get(i).setAnswer_no(avos.get(j).getComment_no());
							qvos.get(i).setAnswer_content(avos.get(j).getComment_content());
							qvos.get(i).setAnswer_date(avos.get(j).getComment_date());
					}
				}
			}
		} log.info("Question&+Answer:{}",qvos);
		
		
		return qvos;
	}

}
