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



	<form class="f1" action="save.action" method="post">
<div class="inputview">
		<span class="inputspan"> 
		<label class="inputtext">姓名:</label> 
		 <input class="inputinput" name="name" >
		
		 <label class="inputtext">年龄:</label> 
		  <input class="inputinput" name="age" type="number" > 
		</span>
		<span class="inputspan"> 	
		<label class="inputtext">性别:</label> 
		 <select name="sex" class="inputselect">
			
		</select> 
		
		<label class="inputtext">状态:</label>  <select name="status" class="inputselect">
			
		</select>
		</span>
		<span class="inputspan"> 
		<label class="inputtext">房间:</label>
<select name="roomid" class="inputselect" style="width:390px;">
			
		</select> </span>
		</div>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="submit" >保存</button>
		</div>
	</form>


</body>
</html>
