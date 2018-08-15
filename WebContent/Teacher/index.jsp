<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../model/list_header.jsp" %>


<body>
	<div class="demoTable">
	
	
<form action="index.action" method="post">
<input name="txt"> <button  class="layui-btn" type="submit" >查询</button><button  class="layui-btn" type="button" onclick="location.href='Teachermanageedit.jsp'">新增</button>
</form>
	

	</div>


<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table">
			<thead>
					<tr>
						<th>教师id</th><th>姓名</th><th>性别</th><th>权限</th><th>密码</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.list}" var="r">
                 
                     <tr>
						<th>${r.teacherid}</th>
						<th>${r.teachername}</th>
						<th>${r.sex==0?"女":"男"}</th>
						<th>${r.authoraty==0?"普通教师":"高级教师"}</th>
						<th>${r.password}</th>
						<th><a href="teachermanage?cmd=edit&id=${r.id}" >修改</a></th>
						<th> <a href="teachermanage?cmd=delect&id=${r.id}">删除</a></th>
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
