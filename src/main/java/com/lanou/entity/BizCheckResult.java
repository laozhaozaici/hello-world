package com.lanou.entity;
// Generated 2017-12-5 11:04:30 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BizCheckResult generated by hbm2java
 */
@Entity
@Table(name = "biz_check_result", catalog = "oa")
public class BizCheckResult implements java.io.Serializable {
	//id111111
	private Integer id;
	private BizClaimVoucher bizClaimVoucher;
	private SysEmployee sysEmployee;  
	private Date checkTime;
	private String result;
	private String comm;

	public BizCheckResult() {
	}

	public BizCheckResult(BizClaimVoucher bizClaimVoucher, SysEmployee sysEmployee, String result, String comm) {
		this.bizClaimVoucher = bizClaimVoucher;
		this.sysEmployee = sysEmployee;
		this.result = result;
		this.comm = comm;
	}

	public BizCheckResult(BizClaimVoucher bizClaimVoucher, SysEmployee sysEmployee, Date checkTime, String result,
			String comm) {
		this.bizClaimVoucher = bizClaimVoucher;
		this.sysEmployee = sysEmployee;
		this.checkTime = checkTime;
		this.result = result;
		this.comm = comm;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLAIM_ID", nullable = false)
	public BizClaimVoucher getBizClaimVoucher() {
		return this.bizClaimVoucher;
	}

	public void setBizClaimVoucher(BizClaimVoucher bizClaimVoucher) {
		this.bizClaimVoucher = bizClaimVoucher;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHECKER_SN", nullable = false)
	public SysEmployee getSysEmployee() {
		return this.sysEmployee;
	}

	public void setSysEmployee(SysEmployee sysEmployee) {
		this.sysEmployee = sysEmployee;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHECK_TIME", length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "RESULT", nullable = false, length = 50)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "COMM", nullable = false, length = 500)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}
