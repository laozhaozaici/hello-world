<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp"%>
<div class="nav" id="nav">
	<div class="t"></div>
   		<dl class="open">
	       	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">报销单管理</dt>
	           <dd><a href="jsp/claim/searchClaim.action">查看报销单</a></dd>
	           <s:if test='#session.employee.sysPosition.nameCn == "员工"'>
	           <dd><a href="jsp/claim/toAddClaimVoucher.action">添加报销单</a></dd>
	           </s:if>
	       </dl>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">请假管理</dt>
	           <dd><a href="jsp/leave/leave_searchLeave.action">查看请假</a></dd>
	           <s:if test='#session.employee.sysPosition.nameCn == "员工"'>
		       <dd><a href="jsp/leave/leave_toEdit.action">申请请假</a></dd>
		       </s:if>
       </dl>
       <s:if test='#session.employee.sysPosition.nameCn == "部门经理"'>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
		       <dd><a href="jsp/statistics/deptStatisticsByMonth.action">报销单月度部门统计</a></dd>
		       <dd><a href="jsp/statistics/deptStatistics_findDeptYearStatisticsList.action">报销单年度部门统计</a></dd>
       </dl>
       </s:if>
       <s:if test='#session.employee.sysPosition.nameCn == "总经理" ||#session.employee.sysPosition.nameCn== "财务"'>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
		       <dd><a href="jsp/statistics/compMonStatistics_getList.action">报销单月度统计</a></dd>
		       <dd><a href="jsp/statistics/compYearStatistics_getList">报销单年度统计</a></dd>
       </dl>
      </s:if>
</div>
