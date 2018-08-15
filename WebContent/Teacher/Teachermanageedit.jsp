<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript" src="../libs/js/jquery.min.js"></script>
</head>
<body>



	<form class="f1" action="teachermanage" method="post">
     <div class="inputview">
     <c:if test="${requestScope.list!=null}">
      <input class="inputinput" type="hidden" value="insert" name="cmd" >
      <input class="inputinput" type="hidden" name="id"  value="${requestScope.list.id}" >
      </c:if>
      <c:if test="${requestScope.list==null}">
      <input class="inputinput" type="hidden" value="add" name="cmd" >
   
      </c:if>
		<span class="inputspan"> 
		
		<label class="inputtext">id:</label> 
		  <input class="inputinput" name="teacherid"  value="${requestScope.list.teacherid }" >
		<label class="inputtext">姓名:</label> 
		 <input class="inputinput" name="teachername" value="${requestScope.list.teachername }" >
		
		
		 <c:if test="${requestScope.list==null }">
		  <label class="inputtext">权限:</label> 
		<select name="authoraty" class="inputselect">
		
		 	<option value=1 selected="selected">高级老师</option>
			<option value=0>普通教师</option>
			</select>
		 </c:if>
		
		</span>
		<span class="inputspan"> 	
		<label class="inputtext">性别:</label> 
		
		 <select name="sex" class="inputselect">
		 <c:if test="${requestScope.list.sex==1 }">
		 	<option value=1 selected="selected">男</option>
			<option value=0>女</option>
			</c:if>
			<c:if test="${requestScope.list.sex==0 }">
		 	<option value=1 >男</option>
			<option value=0 selected="selected">女</option>
			</c:if>
			
			<c:if test="${requestScope.list==null }">
		 	<option value=1 selected="selected">男</option>
			<option value=0>女</option>
			</c:if>
			
		</select> 
		
	<label class="inputtext">密码:</label> 
		 <input class="inputinput" name="password" value="${requestScope.list.password }" >
			
		 </span>
		</div>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="submit" >保存</button>
		</div>
	</form>


</body>
</html>