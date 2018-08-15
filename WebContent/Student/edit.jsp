
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript" src="../libs/js/jquery.min.js"></script>
<script type="text/javascript">
	function save() {
		$.post("Student",$(".f1").serialize(),function(json){
			if(json.c>0){
				parent.fresh();
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		},"json");
	}
</script>
<style type="text/css">

</style>
</head>
<body>
	<form  class="f1">
		<div class="inputview">
		
			<c:if test="${requestScope.info!=null}">
				<input type="hidden" name="cmd" value="update">
				<input type="hidden" name="id" value="${requestScope.info.id}">	
			</c:if>
			<c:if test="${requestScope.info==null}">
				<input type="hidden" name="cmd" value="insert">
			</c:if>
			
			<span class="inputspan"> 
				<label class="inputtext">学号</label><input class="inputinput" name="studentid" value="${requestScope.info.studentid}">
			</span>
			
			<span class="inputspan"> 
				<label class="inputtext">学生名</label><input class="inputinput" name="studentname" value="${requestScope.info.studentname}">
			</span>
			
			<span class="inputspan"> 
				<label class="inputtext">密码</label><input class="inputinput" name="password" value="${requestScope.info.password}">
			</span>
			
			<span class="inputspan"> 
			<label class="inputtext">性别</label>
			<select  class="inputselect" name="sex">
			<c:forEach items="${requestScope.clastatus}"  var="r" varStatus="v">
				<c:if test="${requestScope.info.sex!=v.index}">
					<option value="${v.index}">${r}</option>
				</c:if>
				<c:if test="${requestScope.info.sex==v.index}">
					<option selected="selected" value="${v.index}">${r}</option>
				</c:if>
			</c:forEach>
			</select>
			</span>
			
			<span class="inputspan"> 
				<label class="inputtext">课程</label>
				<select  class="inputselect" name="course_id">
					<c:forEach items="${requestScope.course}"  var="r" >
						<c:if test="${requestScope.info.course_id!=r.id}">
							<option value="${r.id}">${r.coursename}</option>
						</c:if>
						<c:if test="${requestScope.info.course_id==r.id}">
							<option selected="selected" value="${r.id}">${r.coursename}</option>
						</c:if>
					</c:forEach>
				</select>
			</span>
		</div>
		<div class="inputbtpanel ">
			<input class="seachbt1" type="button" onclick="save()" value="保存">
		</div>
	</form>
</body>
</html>