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
 * BizLeave generated by hbm2java
 */
@Entity
@Table(name = "biz_leave", catalog = "oa")
public class BizLeave implements java.io.Serializable {

	private Integer id;
	private SysEmployee sysEmployee;
	private Date starttime;
	private Date endtime;
	private int leaveday;
	private String reason;
	private String status;
	private String leavetype;
	private Integer nextDealSn;
	private String approveOpinion;
	private Date createtime;
	private Date modifytime;

	public BizLeave() {
	}

	public BizLeave(SysEmployee sysEmployee, Date starttime, Date endtime, int leaveday, String reason) {
		this.sysEmployee = sysEmployee;
		this.starttime = starttime;
		this.endtime = endtime;
		this.leaveday = leaveday;
		this.reason = reason;
	}

	public BizLeave(SysEmployee sysEmployee, Date starttime, Date endtime, int leaveday, String reason, String status,
			String leavetype, Integer nextDealSn, String approveOpinion, Date createtime, Date modifytime) {
		this.sysEmployee = sysEmployee;
		this.starttime = starttime;
		this.endtime = endtime;
		this.leaveday = leaveday;
		this.reason = reason;
		this.status = status;
		this.leavetype = leavetype;
		this.nextDealSn = nextDealSn;
		this.approveOpinion = approveOpinion;
		this.createtime = createtime;
		this.modifytime = modifytime;
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
	@JoinColumn(name = "EMPLOYEE_SN", nullable = false)
	public SysEmployee getSysEmployee() {
		return this.sysEmployee;
	}

	public void setSysEmployee(SysEmployee sysEmployee) {
		this.sysEmployee = sysEmployee;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STARTTIME", nullable = false, length = 19)
	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDTIME", nullable = false, length = 19)
	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	@Column(name = "LEAVEDAY", nullable = false)
	public int getLeaveday() {
		return this.leaveday;
	}

	public void setLeaveday(int leaveday) {
		this.leaveday = leaveday;
	}

	@Column(name = "REASON", nullable = false, length = 500)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LEAVETYPE", length = 50)
	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	@Column(name = "NEXT_DEAL_SN")
	public Integer getNextDealSn() {
		return this.nextDealSn;
	}

	public void setNextDealSn(Integer nextDealSn) {
		this.nextDealSn = nextDealSn;
	}

	@Column(name = "APPROVE_OPINION", length = 100)
	public String getApproveOpinion() {
		return this.approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYTIME", length = 19)
	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}
