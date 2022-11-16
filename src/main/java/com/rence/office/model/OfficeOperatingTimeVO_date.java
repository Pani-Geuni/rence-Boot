/**
 * @author 전판근
 */

package com.rence.office.model;

import java.io.Serializable;
import java.util.Date;

public class OfficeOperatingTimeVO_date implements Serializable {
	
	private String backoffice_no;
	
	private Date mon_stime;
	private Date mon_etime;
	private Date tue_stime;
	private Date tue_etime;
	private Date wed_stime;
	private Date wed_etime;
	private Date thu_stime;
	private Date thu_etime;
	private Date fri_stime;
	private Date fri_etime;
	private Date sat_stime;
	private Date sat_etime;
	private Date sun_stime;
	private Date sun_etime;
	
	private String mon_dayoff;
	private String tue_dayoff;
	private String wed_dayoff;
	private String thu_dayoff;
	private String fri_dayoff;
	private String sat_dayoff;
	private String sun_dayoff;
	
	public OfficeOperatingTimeVO_date() {
		// TODO Auto-generated constructor stub
	}

	public OfficeOperatingTimeVO_date(String backoffice_no, Date mon_stime, Date mon_etime, Date tue_stime, Date tue_etime,
			Date wed_stime, Date wed_etime, Date thu_stime, Date thu_etime, Date fri_stime, Date fri_etime,
			Date sat_stime, Date sat_etime, Date sun_stime, Date sun_etime, String mon_dayoff, String tue_dayoff,
			String wed_dayoff, String thu_dayoff, String fri_dayoff, String sat_dayoff, String sun_dayoff) {
		super();
		this.backoffice_no = backoffice_no;
		this.mon_stime = mon_stime;
		this.mon_etime = mon_etime;
		this.tue_stime = tue_stime;
		this.tue_etime = tue_etime;
		this.wed_stime = wed_stime;
		this.wed_etime = wed_etime;
		this.thu_stime = thu_stime;
		this.thu_etime = thu_etime;
		this.fri_stime = fri_stime;
		this.fri_etime = fri_etime;
		this.sat_stime = sat_stime;
		this.sat_etime = sat_etime;
		this.sun_stime = sun_stime;
		this.sun_etime = sun_etime;
		this.mon_dayoff = mon_dayoff;
		this.tue_dayoff = tue_dayoff;
		this.wed_dayoff = wed_dayoff;
		this.thu_dayoff = thu_dayoff;
		this.fri_dayoff = fri_dayoff;
		this.sat_dayoff = sat_dayoff;
		this.sun_dayoff = sun_dayoff;
	}
	

	public String getBackoffice_no() {
		return backoffice_no;
	}

	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
	}

	public Date getMon_stime() {
		return mon_stime;
	}

	public void setMon_stime(Date mon_stime) {
		this.mon_stime = mon_stime;
	}

	public Date getMon_etime() {
		return mon_etime;
	}

	public void setMon_etime(Date mon_etime) {
		this.mon_etime = mon_etime;
	}

	public Date getTue_stime() {
		return tue_stime;
	}

	public void setTue_stime(Date tue_stime) {
		this.tue_stime = tue_stime;
	}

	public Date getTue_etime() {
		return tue_etime;
	}

	public void setTue_etime(Date tue_etime) {
		this.tue_etime = tue_etime;
	}

	public Date getWed_stime() {
		return wed_stime;
	}

	public void setWed_stime(Date wed_stime) {
		this.wed_stime = wed_stime;
	}

	public Date getWed_etime() {
		return wed_etime;
	}

	public void setWed_etime(Date wed_etime) {
		this.wed_etime = wed_etime;
	}

	public Date getThu_stime() {
		return thu_stime;
	}

	public void setThu_stime(Date thu_stime) {
		this.thu_stime = thu_stime;
	}

	public Date getThu_etime() {
		return thu_etime;
	}

	public void setThu_etime(Date thu_etime) {
		this.thu_etime = thu_etime;
	}

	public Date getFri_stime() {
		return fri_stime;
	}

	public void setFri_stime(Date fri_stime) {
		this.fri_stime = fri_stime;
	}

	public Date getFri_etime() {
		return fri_etime;
	}

	public void setFri_etime(Date fri_etime) {
		this.fri_etime = fri_etime;
	}

	public Date getSat_stime() {
		return sat_stime;
	}

	public void setSat_stime(Date sat_stime) {
		this.sat_stime = sat_stime;
	}

	public Date getSat_etime() {
		return sat_etime;
	}

	public void setSat_etime(Date sat_etime) {
		this.sat_etime = sat_etime;
	}

	public Date getSun_stime() {
		return sun_stime;
	}

	public void setSun_stime(Date sun_stime) {
		this.sun_stime = sun_stime;
	}

	public Date getSun_etime() {
		return sun_etime;
	}

	public void setSun_etime(Date sun_etime) {
		this.sun_etime = sun_etime;
	}

	public String getMon_dayoff() {
		return mon_dayoff;
	}

	public void setMon_dayoff(String mon_dayoff) {
		this.mon_dayoff = mon_dayoff;
	}

	public String getTue_dayoff() {
		return tue_dayoff;
	}

	public void setTue_dayoff(String tue_dayoff) {
		this.tue_dayoff = tue_dayoff;
	}

	public String getWed_dayoff() {
		return wed_dayoff;
	}

	public void setWed_dayoff(String wed_dayoff) {
		this.wed_dayoff = wed_dayoff;
	}

	public String getThu_dayoff() {
		return thu_dayoff;
	}

	public void setThu_dayoff(String thu_dayoff) {
		this.thu_dayoff = thu_dayoff;
	}

	public String getFri_dayoff() {
		return fri_dayoff;
	}

	public void setFri_dayoff(String fri_dayoff) {
		this.fri_dayoff = fri_dayoff;
	}

	public String getSat_dayoff() {
		return sat_dayoff;
	}

	public void setSat_dayoff(String sat_dayoff) {
		this.sat_dayoff = sat_dayoff;
	}

	public String getSun_dayoff() {
		return sun_dayoff;
	}

	public void setSun_dayoff(String sun_dayoff) {
		this.sun_dayoff = sun_dayoff;
	}

	@Override
	public String toString() {
		return "OfficeOperatingTimeVO [backoffice_no=" + backoffice_no + ", mon_stime=" + mon_stime + ", mon_etime="
				+ mon_etime + ", tue_stime=" + tue_stime + ", tue_etime=" + tue_etime + ", wed_stime=" + wed_stime
				+ ", wed_etime=" + wed_etime + ", thu_stime=" + thu_stime + ", thu_etime=" + thu_etime + ", fri_stime="
				+ fri_stime + ", fri_etime=" + fri_etime + ", sat_stime=" + sat_stime + ", sat_etime=" + sat_etime
				+ ", sun_stime=" + sun_stime + ", sun_etime=" + sun_etime + ", mon_dayoff=" + mon_dayoff
				+ ", tue_dayoff=" + tue_dayoff + ", wed_dayoff=" + wed_dayoff + ", thu_dayoff=" + thu_dayoff
				+ ", fri_dayoff=" + fri_dayoff + ", sat_dayoff=" + sat_dayoff + ", sun_dayoff=" + sun_dayoff + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backoffice_no == null) ? 0 : backoffice_no.hashCode());
		result = prime * result + ((fri_dayoff == null) ? 0 : fri_dayoff.hashCode());
		result = prime * result + ((fri_etime == null) ? 0 : fri_etime.hashCode());
		result = prime * result + ((fri_stime == null) ? 0 : fri_stime.hashCode());
		result = prime * result + ((mon_dayoff == null) ? 0 : mon_dayoff.hashCode());
		result = prime * result + ((mon_etime == null) ? 0 : mon_etime.hashCode());
		result = prime * result + ((mon_stime == null) ? 0 : mon_stime.hashCode());
		result = prime * result + ((sat_dayoff == null) ? 0 : sat_dayoff.hashCode());
		result = prime * result + ((sat_etime == null) ? 0 : sat_etime.hashCode());
		result = prime * result + ((sat_stime == null) ? 0 : sat_stime.hashCode());
		result = prime * result + ((sun_dayoff == null) ? 0 : sun_dayoff.hashCode());
		result = prime * result + ((sun_etime == null) ? 0 : sun_etime.hashCode());
		result = prime * result + ((sun_stime == null) ? 0 : sun_stime.hashCode());
		result = prime * result + ((thu_dayoff == null) ? 0 : thu_dayoff.hashCode());
		result = prime * result + ((thu_etime == null) ? 0 : thu_etime.hashCode());
		result = prime * result + ((thu_stime == null) ? 0 : thu_stime.hashCode());
		result = prime * result + ((tue_dayoff == null) ? 0 : tue_dayoff.hashCode());
		result = prime * result + ((tue_etime == null) ? 0 : tue_etime.hashCode());
		result = prime * result + ((tue_stime == null) ? 0 : tue_stime.hashCode());
		result = prime * result + ((wed_dayoff == null) ? 0 : wed_dayoff.hashCode());
		result = prime * result + ((wed_etime == null) ? 0 : wed_etime.hashCode());
		result = prime * result + ((wed_stime == null) ? 0 : wed_stime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficeOperatingTimeVO_date other = (OfficeOperatingTimeVO_date) obj;
		if (backoffice_no == null) {
			if (other.backoffice_no != null)
				return false;
		} else if (!backoffice_no.equals(other.backoffice_no))
			return false;
		if (fri_dayoff == null) {
			if (other.fri_dayoff != null)
				return false;
		} else if (!fri_dayoff.equals(other.fri_dayoff))
			return false;
		if (fri_etime == null) {
			if (other.fri_etime != null)
				return false;
		} else if (!fri_etime.equals(other.fri_etime))
			return false;
		if (fri_stime == null) {
			if (other.fri_stime != null)
				return false;
		} else if (!fri_stime.equals(other.fri_stime))
			return false;
		if (mon_dayoff == null) {
			if (other.mon_dayoff != null)
				return false;
		} else if (!mon_dayoff.equals(other.mon_dayoff))
			return false;
		if (mon_etime == null) {
			if (other.mon_etime != null)
				return false;
		} else if (!mon_etime.equals(other.mon_etime))
			return false;
		if (mon_stime == null) {
			if (other.mon_stime != null)
				return false;
		} else if (!mon_stime.equals(other.mon_stime))
			return false;
		if (sat_dayoff == null) {
			if (other.sat_dayoff != null)
				return false;
		} else if (!sat_dayoff.equals(other.sat_dayoff))
			return false;
		if (sat_etime == null) {
			if (other.sat_etime != null)
				return false;
		} else if (!sat_etime.equals(other.sat_etime))
			return false;
		if (sat_stime == null) {
			if (other.sat_stime != null)
				return false;
		} else if (!sat_stime.equals(other.sat_stime))
			return false;
		if (sun_dayoff == null) {
			if (other.sun_dayoff != null)
				return false;
		} else if (!sun_dayoff.equals(other.sun_dayoff))
			return false;
		if (sun_etime == null) {
			if (other.sun_etime != null)
				return false;
		} else if (!sun_etime.equals(other.sun_etime))
			return false;
		if (sun_stime == null) {
			if (other.sun_stime != null)
				return false;
		} else if (!sun_stime.equals(other.sun_stime))
			return false;
		if (thu_dayoff == null) {
			if (other.thu_dayoff != null)
				return false;
		} else if (!thu_dayoff.equals(other.thu_dayoff))
			return false;
		if (thu_etime == null) {
			if (other.thu_etime != null)
				return false;
		} else if (!thu_etime.equals(other.thu_etime))
			return false;
		if (thu_stime == null) {
			if (other.thu_stime != null)
				return false;
		} else if (!thu_stime.equals(other.thu_stime))
			return false;
		if (tue_dayoff == null) {
			if (other.tue_dayoff != null)
				return false;
		} else if (!tue_dayoff.equals(other.tue_dayoff))
			return false;
		if (tue_etime == null) {
			if (other.tue_etime != null)
				return false;
		} else if (!tue_etime.equals(other.tue_etime))
			return false;
		if (tue_stime == null) {
			if (other.tue_stime != null)
				return false;
		} else if (!tue_stime.equals(other.tue_stime))
			return false;
		if (wed_dayoff == null) {
			if (other.wed_dayoff != null)
				return false;
		} else if (!wed_dayoff.equals(other.wed_dayoff))
			return false;
		if (wed_etime == null) {
			if (other.wed_etime != null)
				return false;
		} else if (!wed_etime.equals(other.wed_etime))
			return false;
		if (wed_stime == null) {
			if (other.wed_stime != null)
				return false;
		} else if (!wed_stime.equals(other.wed_stime))
			return false;
		return true;
	}
	
	
}
