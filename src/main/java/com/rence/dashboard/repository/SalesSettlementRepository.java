/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.SalesSettlementViewVO;

public interface SalesSettlementRepository extends JpaRepository<SalesSettlementViewVO, Object>{ // 정산 내역

	@Query(nativeQuery = true, value = "select * from SALESSATTLEMENT_LIST_VIEW where backoffice_no='B1001' order by reserve_sdate desc")
	public List<SalesSettlementViewVO> backoffice_sales_selectAll(String backoffice_no);

}
