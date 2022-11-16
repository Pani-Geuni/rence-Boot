/**
 * @author 전판근
 */

package com.rence.office.model;

public class OfficeQuestionVO {

	private String comment_no;
	private String state;
	private String comment_content;
	private String comment_date;
	private String user_no;
	private String user_name;
	private String user_image;
	private String room_no;
	private String room_name;
	
	private String answer_content;
	private String answer_date;
	
	public OfficeQuestionVO() {
		// TODO Auto-generated constructor stub
	}

	public OfficeQuestionVO(String comment_no, String state, String comment_content, String comment_date,
			String user_no, String user_name, String user_image, String room_no, String room_name,
			String answer_content, String answer_date) {
		super();
		this.comment_no = comment_no;
		this.state = state;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
		this.user_no = user_no;
		this.user_name = user_name;
		this.user_image = user_image;
		this.room_no = room_no;
		this.room_name = room_name;
		this.answer_content = answer_content;
		this.answer_date = answer_date;
	}

	public String getComment_no() {
		return comment_no;
	}

	public void setComment_no(String comment_no) {
		this.comment_no = comment_no;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}

	public String getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(String answer_date) {
		this.answer_date = answer_date;
	}

	@Override
	public String toString() {
		return "OfficeQuestionVO [comment_no=" + comment_no + ", state=" + state + ", comment_content="
				+ comment_content + ", comment_date=" + comment_date + ", user_no=" + user_no + ", user_name="
				+ user_name + ", user_image=" + user_image + ", room_no=" + room_no + ", room_name=" + room_name
				+ ", answer_content=" + answer_content + ", answer_date=" + answer_date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer_content == null) ? 0 : answer_content.hashCode());
		result = prime * result + ((answer_date == null) ? 0 : answer_date.hashCode());
		result = prime * result + ((comment_content == null) ? 0 : comment_content.hashCode());
		result = prime * result + ((comment_date == null) ? 0 : comment_date.hashCode());
		result = prime * result + ((comment_no == null) ? 0 : comment_no.hashCode());
		result = prime * result + ((room_name == null) ? 0 : room_name.hashCode());
		result = prime * result + ((room_no == null) ? 0 : room_no.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((user_image == null) ? 0 : user_image.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		OfficeQuestionVO other = (OfficeQuestionVO) obj;
		if (answer_content == null) {
			if (other.answer_content != null)
				return false;
		} else if (!answer_content.equals(other.answer_content))
			return false;
		if (answer_date == null) {
			if (other.answer_date != null)
				return false;
		} else if (!answer_date.equals(other.answer_date))
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
		if (room_name == null) {
			if (other.room_name != null)
				return false;
		} else if (!room_name.equals(other.room_name))
			return false;
		if (room_no == null) {
			if (other.room_no != null)
				return false;
		} else if (!room_no.equals(other.room_no))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (user_image == null) {
			if (other.user_image != null)
				return false;
		} else if (!user_image.equals(other.user_image))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		return true;
	}

	
	
	
	
}
