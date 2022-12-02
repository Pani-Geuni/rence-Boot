/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmailVO implements Serializable{

	private String reciver;
	private String subject;
	private String content;
	
	public EmailVO() {}


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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((reciver == null) ? 0 : reciver.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		EmailVO other = (EmailVO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (reciver == null) {
			if (other.reciver != null)
				return false;
		} else if (!reciver.equals(other.reciver))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EmailVO [reciver=" + reciver + ", subject=" + subject + ", content=" + content + "]";
	}
	

}
