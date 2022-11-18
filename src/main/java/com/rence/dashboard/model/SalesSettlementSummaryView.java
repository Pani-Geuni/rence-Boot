package com.rence.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="SALESSETTLEMENT_SUMMARY_VIEW")
@Slf4j
public class SalesSettlementSummaryView implements Serializable{
//	@Column(name="sales_no")
//	private String sales_no;
	
	@Transient
	@Column(name="sales_income")
	private String sales_income;
	
	@Id
	@Column(name="sales_total")
	private String sales_total;
	
	@Column(name="sales_cancel")
	private String sales_cancel;
}
