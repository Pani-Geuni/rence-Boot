package com.rence.office.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeOperatingTimeVO_date;

public interface OfficeOperatingTimeRepository extends JpaRepository<OfficeOperatingTimeVO_date, Object> {

	
	@Query(nativeQuery = true, value = ""
			+ "select * "
			+ "from backofficeoperatingtime "
			+ "where backoffice_no=?1")
	public OfficeOperatingTimeVO_date select_one_operating_time(String backoffice_no);
}
