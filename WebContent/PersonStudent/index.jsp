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
	var studentpassword = ${requestScope.list.password};
	var id = ${requestScope.list.id};
	$(function () {
		
	});
	
	function alertpasseord() {
		if($(".inputinput:eq(0)").val() == studentpassword){
			if($(".inputinput:eq(1)").val() == $(".inputinput:eq(2)").val()){
				open("PersonStudent?cmd=passwordalter&id="+id+"&password="+$(".inputinput:eq(1)").val(),"_self");
			}else{
				alert("两次密码不一样");
			}
		}else{
			alert("请输入正确密码");
		}
	}
</script>
</head>
<body>
	<div class="demoTable">
	<div class="layui-form layui-border-box layui-table-view" >
		<div class="layui-table-box">
			<div class="layui-table-body layui-table-main" >
				<span class="inputspan"> 
					<label class="inputtext">老密码</label><input class="inputinput" name="oldpasseord" value=""><br>
				</span>
				<span class="inputspan">
					<label class="inputtext">新密码</label><input class="inputinput" name="newpassword" value=""><br>
				</span>
				<span class="inputspan">
					<label class="inputtext">重复</label><input class="inputinput" name="pattern" value="">
				</span>
				
			</div>
		</div>
	</div>
		<button class="layui-btn" onclick="alertpasseord()">修改</button>
		
	</div>


</body>

