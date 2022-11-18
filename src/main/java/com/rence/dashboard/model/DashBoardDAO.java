package com.rence.dashboard.model;

import java.util.List;

public interface DashBoardDAO {

	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer start_row, Integer end_row);

	public List<ReserveVIEW> backoffice_reserve_selectAll(String backoffice_no, String reserve_state,Integer start_row, Integer end_row);

	public List<ReserveVIEW> backoffice_search_reserve(String backoffice_no, String reserve_state, String searchword,
			Integer start_row, Integer end_row);


}
