/**
 * 
 * @author 최진실
 *
 */
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

	public Object reserve_state_auto_update();

	public List<ScheduleListView> backoffice_schedule_list(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime);

	public List<reservationView> backoffice_reservation(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String room_no);


}
