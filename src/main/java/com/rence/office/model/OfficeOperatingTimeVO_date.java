/**
 * @author 전판근
 */

package com.rence.office.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="backofficeoperatingtime")
public class OfficeOperatingTimeVO_date {
	
	@Id
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="mon_stime")
	private Date mon_stime;
	
	@Column(name="mon_etime")
	private Date mon_etime;
	
	@Column(name="tue_stime")
	private Date tue_stime;
	
	@Column(name="tue_etime")
	private Date tue_etime;
	
	@Column(name="wed_stime")
	private Date wed_stime;
	
	@Column(name="wed_etime")
	private Date wed_etime;
	
	@Column(name="thu_stime")
	private Date thu_stime;
	
	@Column(name="thu_etime")
	private Date thu_etime;
	
	@Column(name="fri_stime")
	private Date fri_stime;
	
	@Column(name="fri_etime")
	private Date fri_etime;
	
	@Column(name="sat_stime")
	private Date sat_stime;
	
	@Column(name="sat_etime")
	private Date sat_etime;
	
	@Column(name="sun_stime")
	private Date sun_stime;
	
	@Column(name="sun_etime")
	private Date sun_etime;
	
	@Column(name="mon_dayoff")
	private String mon_dayoff;
	
	@Column(name="tue_dayoff")
	private String tue_dayoff;
	
	@Column(name="wed_dayoff")
	private String wed_dayoff;
	
	@Column(name="thu_dayoff")
	private String thu_dayoff;
	
	@Column(name="fri_dayoff")
	private String fri_dayoff;
	
	@Column(name="sat_dayoff")
	private String sat_dayoff;
	
	@Column(name="sun_dayoff")
	private String sun_dayoff;
	
}
