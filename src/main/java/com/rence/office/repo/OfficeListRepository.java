package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.ListViewVO;

public interface OfficeListRepository extends JpaRepository<ListViewVO, Object> {

	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ " where backoffice_type like ?1"
		+ " ORDER BY backoffice_no desc")
	public List<ListViewVO> selectAll_orderBy_date(String type);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY avg_rating desc, backoffice_no desc")
	public List<ListViewVO> selectAll_orderBy_rating(String type);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY min_room_price, backoffice_no desc")
	public List<ListViewVO> selectAll_orderBy_cheap(String type);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY min_room_price desc, backoffice_no desc")
	public List<ListViewVO> selectAll_orderBy_expensive(String type);
	
	//	:: SEARCH :: //
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY backoffice_no desc")
	public List<ListViewVO> searchAll_orderBy_date(String type, String location, String searchWord);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY avg_rating desc, backoffice_no desc")
	public List<ListViewVO> searchAll_orderBy_rating(String type, String location, String searchWord);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY min_room_price, backoffice_no desc")
	public List<ListViewVO> searchAll_orderBy_cheap(String type, String location, String searchWord);
	
	@Query(nativeQuery = true, value = 
		"select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY total.min_room_price desc, total.backoffice_no desc")
	public List<ListViewVO> searchAll_orderBy_expensive(String type, String location, String searchWord);

}