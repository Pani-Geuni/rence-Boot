/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.util.List;
import java.util.Map;

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
			String not_stime, String not_etime,String off_type);

	public List<ReservationView> backoffice_reservation(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String room_no, String off_type, int min, int max);

	public BOPaymentVO backoffice_reservation_cancel(String backoffice_no, String reserve_no, String user_no);

	public int backoffice_reservation_cnt(String backoffice_no, String not_sdate, String not_edate, String not_stime,
			String not_etime, String room_no, String off_type);

	public int backoffice_reserve_selectAll_cnt(String backoffice_no, String reserve_state);

	public int backoffice_search_reserve_cnt(String backoffice_no, String searchword, String reserve_state);

	public int backoffice_updateOK_sales(String backoffice_no, String room_no, String payment_no);


}
