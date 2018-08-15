<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../model/list_header.jsp" %>


<body>

<script type="text/javascript" >

function openwin(ids,idt,idv,idb)
{
	
	show("table?cmd=edit&id="+ids+"&testpaper_id="+idt+"&course_id="+idv+"&teacher_id="+idb,400,400);
}
function openwins()
{
	
	show("table?cmd=edit",400,400);
	
}
function fresh()
{
	
	location.href="table";
}
function checkdelect(cmd)
{
      if(confirm("是否删除？ "))
    	  {
    	  location.href="table?cmd=delect&id="+cmd;
    	  
    	  }
	
	}
</script>

	<div class="demoTable">
	
	
<form action="index.action" method="post">
<input name="txt"> <button  class="layui-btn" type="submit">查询</button><button  class="layui-btn" type="button" onclick="openwins()">新增</button>
</form>
 

	</div>


<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table">
			<thead>
					<tr>
						<th>试卷名称</th><th>课程名称</th><th>教师名称</th><th>发布时间</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach items="${requestScope.list}" var="r">
                 
                     <tr>
						<th>${r.testpapername}</th>
						<th>${r.coursename}</th>
						<th>${r.teachername}</th>
						<th>${r.publishdate}</th>
						<th><a href="javascript:openwin(${r.id},${r.testpaper_id},${r.course_id},${r.teacher_id})" >修改</a></th>
						<th> <a href="javascript:checkdelect(${r.id})">删除</a></th>
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
			<button type="button" class="layui-laypage-btn" onclick="location.href='index.action?pageno='" >上一页</button>
			<button type="button" class="layui-laypage-btn" onclick="location.href='index.action?pageno=''">下一页</button>
			</span>
				</div>
		</div>
	</div>
	
</div>

</body>
</html>
