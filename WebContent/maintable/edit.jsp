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

<script type="text/javascript">

function  save()
{
	 $.post("table", $(".f1").serialize(),function(json)
			 {
		 if(json.status>0)
			 {
			
			 parent.fresh();
			 var  index=parent.layer.getFrameIndex(window.name);
			 parent.layer.close(index);
			 
			 }
		 if(json.status==1)
			 {
			 alert("添加成功")
			 }
		 if(json.status==2)
		 {
		 alert("修改成功")
		 }
		 if(json.status==-1)
		 {
		 alert("添加失败")
		 }
		 if(json.status==-2)
		 {
		 alert("修改失败")
		 }
			 },"json");
	}
	
	function changepaper(object)
	{
		$.post("table?cmd=onchange", $(".f1").serialize(),function(json)
		{
			$("#change").empty();			 
			 for(var i = 0; i< json.length; i++){
     			
     			$("<option value="+ json[i].id+" >"+  json[i].papername+"</option>").appendTo("#change");
     		}
			 
				
				 },"json");
		
		
	}

</script>

	<form class="f1" action="table" method="post">
   <div class="inputview">
   <c:if test="${requestScope.id==null}">
   <input type="hidden" name="cmd" value="add">
   </c:if>
   <c:if test="${requestScope.id!=null}">
   <input type="hidden" name="cmd" value="update">
    <input type="hidden" name="id" value=${ requestScope.id}>
   </c:if>
    <c:if test="${requestScope.testpaperlist.id!=null}">
		  <input type="hidden" name="oldtestpaperid" value=${ requestScope.testpaperlist.id}>
		  </c:if>
    		<span class="inputspan"> 
		<label class="inputtext">试卷名称:</label> 
		
		  
		  
		 <select name="testpaper_id" id="change" class="inputselect">
		 
		 <option value=${ requestScope.testpaperlist.id} >${requestScope.testpaperlist.testpapername}</option>
		
		<c:forEach items="${requestScope.testpaperlists}" var="r" varStatus="vs">
			<c:if test="${r.id==requestScope.testpaperlist.id}">
			<option value=${r.id}  selected="selected">${r.testpapername}</option>
			</c:if>
			<c:if test="${r.id!=requestScope.testpaperlist.id}">
			<option value=${ r.id} >${r.testpapername}</option>
			</c:if>
			</c:forEach>
			
		</select>	
		</span>
		
		
		<span class="inputspan"> 
		 <label class="inputtext">课程名称:</label> 
		   <select name="course_id" class="inputselect" onchange="changepaper(this)">
			<c:forEach items="${requestScope.courselists}" var="r" varStatus="vs">
			<c:if test="${r.id==requestScope.courselist.id}">
			<option value=${ r.id} selected="selected">${r.coursename}</option>
			</c:if>
			<c:if test="${r.id!=requestScope.courselist.id}">
			<option value=${ r.id} >${r.coursename}</option>
			</c:if>
			</c:forEach>
		</select>
		</span>
		
		
		<span class="inputspan"> 	
		<label class="inputtext">教师名称:</label> 
		 <select name="teacher_id" class="inputselect">
			<c:forEach items="${requestScope.teacherlists}" var="r" varStatus="vs">
			<c:if test="${r.id==requestScope.teacherlist.id}">
			<option value=${ r.id} selected="selected">${r.teachername}</option>
			</c:if>
			<c:if test="${r.id!=requestScope.teacherlist.id}">
			<option value=${ r.id}>${r.teachername}</option>
			</c:if>
			</c:forEach>
		</select> 	
		</span>
		
		<span class="inputspan"> 
		<label class="inputtext">发布时间:</label> 
		 <input type="text" name="publishdate" value="${requestScope.publishdate}">
		</span>
		</div>
		
		
		<div class="inputbtpanel ">
		<button class="seachbt1" type="button" onclick="save()" >保存</button>
		</div>
	</form>


</body>
</html>
