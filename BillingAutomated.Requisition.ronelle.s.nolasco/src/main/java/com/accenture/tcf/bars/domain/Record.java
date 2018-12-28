package com.accenture.tcf.bars.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@SecondaryTable(name="billing")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="request_id")
	private int billing_id;
	
	
	@Column(name = "billing_cycle")
	private int billingCycle;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="account_name")
	private String accountname;
	
	@Column(name="last_name")
	private String customerLastName;
	
	@Column(name="first_name")
	private String customerFirstName;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	
	public Record(int billngCycle, Date startDate, Date endDate, String accountname, String customerLastName,
			String customerFirstName, BigDecimal amount) {
		
		this.billingCycle = billngCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountname = accountname;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.amount = amount;
	}

	public int getBillngCycle() {
		return billingCycle;
	}
	public void setBillngCycle(int billngCycle) {
		this.billingCycle = billngCycle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
