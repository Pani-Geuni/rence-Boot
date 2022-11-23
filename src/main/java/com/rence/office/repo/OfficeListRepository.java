package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.ListViewVO;

public interface OfficeListRepository extends JpaRepository<ListViewVO, Object> {
	
	@Query(nativeQuery = true, value = 
		"select count(*) from ListView"
		+ " where backoffice_type like ?1")
	public int list_totalCnt(String type);

	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ " where backoffice_type like ?1"
		+ " ORDER BY backoffice_no desc))"
		+ " where NUM between ?2 and ?3")
	public List<ListViewVO> selectAll_orderBy_date(String type, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY avg_rating desc, backoffice_no desc))"
		+ " where NUM between ?2 and ?3")
	public List<ListViewVO> selectAll_orderBy_rating(String type, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY min_room_price, backoffice_no desc))"
		+ "	where NUM between ?2 and ?3")
	public List<ListViewVO> selectAll_orderBy_cheap(String type, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1"
		+ "	ORDER BY min_room_price desc, backoffice_no desc))"
		+ "	where NUM between ?2 and ?3")
	public List<ListViewVO> selectAll_orderBy_expensive(String type, Integer min, Integer max);
	
	
	
	//	:: SEARCH :: //
	@Query(nativeQuery = true, value = 
		" select count(*) from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3")
	public int search_list_totalCnt(String type, String location, String searchWord);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY backoffice_no desc))"
		+ "	where NUM between ?4 and ?5")
	public List<ListViewVO> searchAll_orderBy_date(String type, String location, String searchWord, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY avg_rating desc, backoffice_no desc))"
		+ "	where NUM between ?4 and ?5")
	public List<ListViewVO> searchAll_orderBy_rating(String type, String location, String searchWord, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY min_room_price, backoffice_no desc))"
		+ "	where NUM between ?4 and ?5")
	public List<ListViewVO> searchAll_orderBy_cheap(String type, String location, String searchWord, Integer min, Integer max);
	
	@Query(nativeQuery = true, value = 
		"select * from ("	
		+ "select  ROWNUM AS NUM, backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image,backoffice_type, min_room_price, avg_rating "
		+ "from ("
		+ " select * from ListView"
		+ "	where backoffice_type like ?1 AND roadname_address like ?2 AND company_name like ?3"
		+ "	ORDER BY min_room_price desc, backoffice_no desc))"
		+ " where NUM between ?4 and ?5")
	public List<ListViewVO> searchAll_orderBy_expensive(String type, String location, String searchWord, Integer min, Integer max);

}