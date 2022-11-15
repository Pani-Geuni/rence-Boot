/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.model;

import java.io.Serializable;

public class BackOfficeOperatingTimeVO implements Serializable{

	private String opetime_no;
	private String mon_stime;
	private String mon_etime;
	private String tue_stime;
	private String tue_etime;
	private String wed_stime;
	private String wed_etime;
	private String thu_stime;
	private String thu_etime;
	private String fri_stime;
	private String fri_etime;
	private String sat_stime;
	private String sat_etime;
	private String sun_stime;
	private String sun_etime;
	private String backoffice_no;
	private String mon_dayoff;
	private String tue_dayoff;
	private String wed_dayoff;
	private String thu_dayoff;
	private String fri_dayoff;
	private String sat_dayoff;
	private String sun_dayoff;
	
	public BackOfficeOperatingTimeVO() {
		// TODO Auto-generated constructor stub
	}


	public BackOfficeOperatingTimeVO(String opetime_no, String mon_stime, String mon_etime, String tue_stime,
			String tue_etime, String wed_stime, String wed_etime, String thu_stime, String thu_etime, String fri_stime,
			String fri_etime, String sat_stime, String sat_etime, String sun_stime, String sun_etime,
			String backoffice_no, String mon_dayoff, String tue_dayoff, String wed_dayoff, String thu_dayoff,
			String fri_dayoff, String sat_dayoff, String sun_dayoff) {
		super();
		this.opetime_no = opetime_no;
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
		this.backoffice_no = backoffice_no;
		this.mon_dayoff = mon_dayoff;
		this.tue_dayoff = tue_dayoff;
		this.wed_dayoff = wed_dayoff;
		this.thu_dayoff = thu_dayoff;
		this.fri_dayoff = fri_dayoff;
		this.sat_dayoff = sat_dayoff;
		this.sun_dayoff = sun_dayoff;
	}


	public String getOpetime_no() {
		return opetime_no;
	}


	public void setOpetime_no(String opetime_no) {
		this.opetime_no = opetime_no;
	}


	public String getMon_stime() {
		return mon_stime;
	}


	public void setMon_stime(String mon_stime) {
		this.mon_stime = mon_stime;
	}


	public String getMon_etime() {
		return mon_etime;
	}


	public void setMon_etime(String mon_etime) {
		this.mon_etime = mon_etime;
	}


	public String getTue_stime() {
		return tue_stime;
	}


	public void setTue_stime(String tue_stime) {
		this.tue_stime = tue_stime;
	}


	public String getTue_etime() {
		return tue_etime;
	}


	public void setTue_etime(String tue_etime) {
		this.tue_etime = tue_etime;
	}


	public String getWed_stime() {
		return wed_stime;
	}


	public void setWed_stime(String wed_stime) {
		this.wed_stime = wed_stime;
	}


	public String getWed_etime() {
		return wed_etime;
	}


	public void setWed_etime(String wed_etime) {
		this.wed_etime = wed_etime;
	}


	public String getThu_stime() {
		return thu_stime;
	}


	public void setThu_stime(String thu_stime) {
		this.thu_stime = thu_stime;
	}


	public String getThu_etime() {
		return thu_etime;
	}


	public void setThu_etime(String thu_etime) {
		this.thu_etime = thu_etime;
	}


	public String getFri_stime() {
		return fri_stime;
	}


	public void setFri_stime(String fri_stime) {
		this.fri_stime = fri_stime;
	}


	public String getFri_etime() {
		return fri_etime;
	}


	public void setFri_etime(String fri_etime) {
		this.fri_etime = fri_etime;
	}


	public String getSat_stime() {
		return sat_stime;
	}


	public void setSat_stime(String sat_stime) {
		this.sat_stime = sat_stime;
	}


	public String getSat_etime() {
		return sat_etime;
	}


	public void setSat_etime(String sat_etime) {
		this.sat_etime = sat_etime;
	}


	public String getSun_stime() {
		return sun_stime;
	}


	public void setSun_stime(String sun_stime) {
		this.sun_stime = sun_stime;
	}


	public String getSun_etime() {
		return sun_etime;
	}


	public void setSun_etime(String sun_etime) {
		this.sun_etime = sun_etime;
	}


	public String getBackoffice_no() {
		return backoffice_no;
	}


	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
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
		result = prime * result + ((opetime_no == null) ? 0 : opetime_no.hashCode());
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
		BackOfficeOperatingTimeVO other = (BackOfficeOperatingTimeVO) obj;
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
		if (opetime_no == null) {
			if (other.opetime_no != null)
				return false;
		} else if (!opetime_no.equals(other.opetime_no))
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


	@Override
	public String toString() {
		return "BackOfficeOperationgTimeVO [opetime_no=" + opetime_no + ", mon_stime=" + mon_stime + ", mon_etime="
				+ mon_etime + ", tue_stime=" + tue_stime + ", tue_etime=" + tue_etime + ", wed_stime=" + wed_stime
				+ ", wed_etime=" + wed_etime + ", thu_stime=" + thu_stime + ", thu_etime=" + thu_etime + ", fri_stime="
				+ fri_stime + ", fri_etime=" + fri_etime + ", sat_stime=" + sat_stime + ", sat_etime=" + sat_etime
				+ ", sun_stime=" + sun_stime + ", sun_etime=" + sun_etime + ", backoffice_no=" + backoffice_no
				+ ", mon_dayoff=" + mon_dayoff + ", tue_dayoff=" + tue_dayoff + ", wed_dayoff=" + wed_dayoff
				+ ", thu_dayoff=" + thu_dayoff + ", fri_dayoff=" + fri_dayoff + ", sat_dayoff=" + sat_dayoff
				+ ", sun_dayoff=" + sun_dayoff + "]";
	}

}
