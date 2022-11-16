/**
 * @author 전판근
 */

package com.rence.office.model;

public class QuestionVO2 {
	String comment_no;
	String comment_content;
	String comment_date;
	String room_no;
	String backoffice_no;
	String user_no;
	public QuestionVO2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionVO2(String comment_no, String comment_content, String comment_date, String room_no,
			String backoffice_no, String user_no) {
		super();
		this.comment_no = comment_no;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
		this.room_no = room_no;
		this.backoffice_no = backoffice_no;
		this.user_no = user_no;
	}
	public String getComment_no() {
		return comment_no;
	}
	public void setComment_no(String comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getBackoffice_no() {
		return backoffice_no;
	}
	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backoffice_no == null) ? 0 : backoffice_no.hashCode());
		result = prime * result + ((comment_content == null) ? 0 : comment_content.hashCode());
		result = prime * result + ((comment_date == null) ? 0 : comment_date.hashCode());
		result = prime * result + ((comment_no == null) ? 0 : comment_no.hashCode());
		result = prime * result + ((room_no == null) ? 0 : room_no.hashCode());
		result = prime * result + ((user_no == null) ? 0 : user_no.hashCode());
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
		QuestionVO2 other = (QuestionVO2) obj;
		if (backoffice_no == null) {
			if (other.backoffice_no != null)
				return false;
		} else if (!backoffice_no.equals(other.backoffice_no))
			return false;
		if (comment_content == null) {
			if (other.comment_content != null)
				return false;
		} else if (!comment_content.equals(other.comment_content))
			return false;
		if (comment_date == null) {
			if (other.comment_date != null)
				return false;
		} else if (!comment_date.equals(other.comment_date))
			return false;
		if (comment_no == null) {
			if (other.comment_no != null)
				return false;
		} else if (!comment_no.equals(other.comment_no))
			return false;
		if (room_no == null) {
			if (other.room_no != null)
				return false;
		} else if (!room_no.equals(other.room_no))
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QuestionVO [comment_no=" + comment_no + ", comment_content=" + comment_content + ", comment_date="
				+ comment_date + ", room_no=" + room_no + ", backoffice_no=" + backoffice_no + ", user_no=" + user_no
				+ "]";
	}
	
	
}
