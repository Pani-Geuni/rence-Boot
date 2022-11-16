/**
 * @author 강경석
 * 인증관련 vo
 */

package com.rence.user.model;

import java.util.Objects;

public class EmailVO {

	private String reciver;
	private String subject;
	private String content;
	
	
	public EmailVO() {
		// TODO Auto-generated constructor stub
	}


	public EmailVO(String reciver, String subject, String content) {
		super();
		this.reciver = reciver;
		this.subject = subject;
		this.content = content;
	}


	public String getReciver() {
		return reciver;
	}


	public void setReciver(String reciver) {
		this.reciver = reciver;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public int hashCode() {
		return Objects.hash(content, reciver, subject);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailVO other = (EmailVO) obj;
		return Objects.equals(content, other.content) && Objects.equals(reciver, other.reciver)
				&& Objects.equals(subject, other.subject);
	}


	@Override
	public String toString() {
		return "EmailVO [reciver=" + reciver + ", subject=" + subject + ", content=" + content + "]";
	}
	
	
	
}//end class
