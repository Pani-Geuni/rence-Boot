package com.rence.master.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MASTERINFO")
public class MasterEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_master")
	@SequenceGenerator(sequenceName = "seq_master", allocationSize = 1, name = "seq_master")
	@Column(name="master_no")
	private String master_no;
	
	@Column(name="master_id")
	private String master_id;
	
	@Column(name="master_pw")
	private String master_pw;
}
