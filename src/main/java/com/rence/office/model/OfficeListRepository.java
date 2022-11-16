package com.rence.office.model;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfficeListRepository extends JpaRepository<ListViewVO, Object> {

	@Query(nativeQuery = true, value = "create or replace view ListView as"
			+ "("
			+ "select * from( "
		+ "		    select left.backoffice_no, backoffice_type, company_name, roadname_address, backoffice_tag, REGEXP_SUBSTR(backoffice_image, '[^,]+', 1, 1) as backoffice_image, nvl(min_room_price, 0) as min_room_price, nvl(avg(rv.review_point), 0) as avg_rating from("
		+ "		        select b.backoffice_no, b.backoffice_type, b.company_name, b.roadname_address, b.backoffice_tag, b.backoffice_image, min(r.room_price)as min_room_price "
		+ "		        from backofficeinfo b left join roominfo "
		+ "		        on b.backoffice_no = r.backoffice_no "
		+ "		        where b.backoffice_state = 'Y' group by b.backoffice_no, b.company_name, b.roadname_address, b.backoffice_tag, b.backoffice_image, b.backoffice_type "
		+ "		    )left left join review rv "
		+ "		    on left.backoffice_no = rv.backoffice_no "
		+ "		    group by left.backoffice_no, company_name, roadname_address, backoffice_tag, backoffice_image, "
		+ "			min_room_price, nvl(min_room_price, 0), min_room_price, 0, min_room_price, "
		+ "			0, min_room_price, 0, REGEXP_SUBSTR(backoffice_image, '[^,]+', 1, 1), backoffice_image, "
		+ "			'[^,]+', 1, 1, backoffice_type "
		+ "		) "
		+ "		where backoffice_type like ?2 "
		
		+ "		IF ?1 == 'date' THEN "
		+ "		ORDER BY backoffice_no desc"
		+ "		IF ?1 == 'rating' THEN "
		+ "		ORDER BY total.avg_rating desc, total.backoffice_no desc"
		+ "		IF ?1 == 'cheap' THEN "
		+ "		ORDER BY total.min_room_price, total.backoffice_no desc"
		+ "		IF ?1 == 'expensive' THEN "
		+ "		ORDER BY total.min_room_price desc, total.backoffice_no desc"
		+ "		)")
	public List<ListViewVO> select_all_list(String condition, String type);
	public List<ListViewVO> search_list(String type, String location, String searchWord, String condition);

}