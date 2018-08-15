<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ include file="../model/list_header.jsp" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript">

$(function(){
	$(".selectall").on("click",function(){
		if(event.target.checked)
			$("[type=checkbox]").prop("checked","checked")
		else
			$("[type=checkbox]").prop("checked","")
	});
});
function seletcs() {
	if(confirm("确认删除？")){
		var lists=[];
		$(".che").each(function() {
			if($(this).prop('checked'))
				lists.push($(this).val());
		});
		if(lists.length > 0){
			open("Student?cmd=deleteall&lists="+lists,"_self");
		}
	}
};

function deletes(id) {
	if(confirm("确认删除？")){
		open("Student?cmd=delete&id="+id,"_self");
	}
};
	function fresh() {
		open("Student","_self");
	}
	
	function show() {
		showw("Student?cmd=add",300,400)
	}
	
	function alter(id) {
		showw("Student?cmd=edit&id="+id,300,400)
	}
	
</script>
</head>
<body>
	<div class="demoTable">
	<form action="Course" method="post">
		<input class="inputselect" type="text" name="he" value="${requestScope.he}" >
		<button class="layui-btn" type="submit">查询</button><button  class="layui-btn" type="button" onclick="show()">新增</button>
		<button class="layui-btn" type="button" onclick="seletcs()">删除</button>
	</form>
	</div>
	<div class="layui-form layui-border-box layui-table-view" >
		<div class="layui-table-box">
			<div class="layui-table-body layui-table-main" >
				<table  class="layui-table">
				<thead>
					<tr>
						<th><input class="selectall" type="checkbox"></input></th><th>学生号</th><th>学生名</th><th>性别</th><th>密码</th><th>课程</th><th>功能1</th><th>功能2</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${requestScope.list}">
						<tr>
							<td ><input class="che" value="${row.id}" type="checkbox"></<input></td>
							<td><c:out value="${row.studentid}"/></td>
							<td><c:out value="${row.studentname}"/></td>
							<c:if test="${row.sex == 1}">
								<td>男</td>
							</c:if>
							<c:if test="${row.sex == 0}">
								<td>女</td>
							</c:if>
							<td><c:out value="${row.password}"/></td>
							<td><c:out value="${row.coursename}"/></td>
						   	<td><a href="javascript:alter(${row.id})">修改</a><br></td>
						   	<td><a href="javascript:deletes(${row.id})">删除</a><br></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="layui-table-page">
		<div id="layui-table-page1">
			<div class="layui-box layui-laypage layui-laypage-default" >
			<span class="layui-laypage-skip">
			<c:set var="s" value="${requestScope.count}"></c:set>
			<button type="button" class="layui-laypage-btn" onclick="window.location='Student?count=${requestScope.count = 0}&he=${requestScope.he}&max=${requestScope.max}'" >首页</button>
			<button type="button" class="layui-laypage-btn" onclick="window.location='Student?count=${s+1}&he=${requestScope.he}&max=${requestScope.max}'" >下一页</button>
			<button type="button" class="layui-laypage-btn" onclick="window.location='Student?count=${s-1}&he=${requestScope.he}&max=${requestScope.max}'">上一页</button>
			<button type="button" class="layui-laypage-btn" onclick="window.location='Student?count=${fn:length(requestScope.pages)}&he=${requestScope.he}&max=${requestScope.max}'">尾页</button>
			<c:forEach var="r" items="${requestScope.pages}" varStatus="v">
				<a type="button" class="layui-laypage-btn" href="Student?count=${v.index}&he=${requestScope.he}&max=${requestScope.max}&selectedid=${ requestScope.selectedid}">${r+1}</a>
			</c:forEach>
			</span>
			</div>
		</div>
	</div>
	
</div>

</body>

