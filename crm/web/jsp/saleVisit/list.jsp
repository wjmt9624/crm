﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<TITLE>客户拜访记录列表</TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
	<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
		  rel=stylesheet>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
	<SCRIPT language=javascript>
		function to_page(page){
			if(page){
				$("#page").val(page);
			}
			document.customerForm.submit();
		}
	</SCRIPT>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
			$('#visit_time').datepick({dateFormat: 'yy-mm-dd'});
			$('#visit_end_time').datepick({dateFormat: 'yy-mm-dd'});
		});
	</script>

	<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<form action="${pageContext.request.contextPath}/saleVisit_findAll.action" method="post" id="customerForm" name="customerForm">
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
							  border=0></TD>
			<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
				height=20></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
					src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
			<TD vAlign=top width="100%" bgColor=#ffffff>
				<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
					<TR>
						<TD class=manageHead>当前位置：客户拜访管理 &gt; 客户拜访记录列表</TD>
					</TR>
					<TR>
						<TD height=2></TD>
					</TR>
				</TABLE>
				<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
					   width="100%" align=center border=0>
					<TBODY>
					<TR>
						<TD height=25>
							<TABLE cellSpacing=0 cellPadding=2 border=0>
								<TBODY>
								<TR>
									<TD>拜访时间：从</TD>
									<td>
										<input type="text" id="visit_time" name="visit_time" value="<s:date name="visit_time" format="yyyy-MM-dd"/>"/>
									</td>

									<TD>到</TD>
									<td>
										<input type="text" id="visit_end_time" name="visit_end_time" value="<s:date name="visit_end_time" format="yyyy-MM-dd"/>"/>
									</td>

									<TD><INPUT class=button id=sButton2 type=submit
											   value=" 筛选 " name=sButton2></TD>
								</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>

					<TR>
						<TD>
							<TABLE id=grid
								   style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
								   cellSpacing=1 cellPadding=2 rules=all border=0>
								<TBODY>
								<TR
										style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<TD>业务员名称</TD>
									<TD>客户名称</TD>
									<TD>拜访时间</TD>
									<TD>拜访地点</TD>
									<TD>拜访详情</TD>
									<TD>下次拜访时间</TD>
									<TD>操作</TD>
								</TR>
								<s:iterator value="list">
									<TR
											style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<TD><s:property value="user.user_name"/> </TD>
										<TD><s:property value="customer.cust_name" /></TD>
										<TD><s:date name="visit_time" format="yyyy-MM-dd"/></TD>
										<TD><s:property value="visit_addr"/></TD>
										<TD><s:property value="visit_detail"/></TD>
										<TD><s:date name="visit_nexttime" format="yyyy-MM-dd"/></TD>
										<TD>
											<a href="#">修改</a>
											&nbsp;&nbsp;
											<a href="#">删除</a>
										</TD>
									</TR>
								</s:iterator>
								</TBODY>
							</TABLE>
						</TD>
					</TR>

					<TR>
						<TD><SPAN id=pagelink>
											<DIV
													style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"/></B>]页
												,每页显示

												<select name="pageSize" onchange="to_page()">
												<option value="3" <s:if test="pageSize == 3">selected</s:if>>3</option>
												<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
												<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
												</select>
												条
												<s:if test="currPage != 1">
													[<A href="javascript:to_page(1)">首页</A>]
													[<A href="javascript:to_page(<s:property value="currPage-1"/> )">前一页</A>]
												</s:if>&nbsp;
												<B>
													<s:iterator var="i" begin="1" end="totalPage">
														<s:if test="#i == currPage">
															<s:property value="#i"/>
														</s:if>
														<s:else>
															<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/> </a>
														</s:else>
													</s:iterator>
												</B>
												<s:if test="currPage != totalPage">
													[<A href="javascript:to_page(<s:property value="currPage+1"/>)">后一页</A>]
													[<A href="javascript:to_page(<s:property value="totalPage"/>)">尾页</A>]
												</s:if>
												到
												<input type="text" size="3" id="page" name="currPage"/>
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
					</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
					src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
							  border=0></TD>
			<TD align=middle width="100%"
				background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	</form>
</BODY>
</HTML>
