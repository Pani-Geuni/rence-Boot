package com.rence.dashboard.model;

import java.util.List;

public interface DashBoardDAO {

	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer start_row, Integer end_row);

	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, String reserve_state,Integer start_row, Integer end_row);

	public List<ReserveListView> backoffice_search_reserve(String backoffice_no, String reserve_state, String searchword,
			Integer start_row, Integer end_row);

	public RoomSummaryView room_summary_selectOne(String backoffice_no);

	public SalesSettlementSummaryView payment_summary_selectOne(String backoffice_no);

	public SalesSettlementDetailView SalesSettlementDetailView(String backoffice_no, String sales_date);


}
