<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../model/list_header.jsp" %>


<body>
	<div class="demoTable">
	
	
<form action="index.action" method="post">
<input name="txt"> <button  class="layui-btn" type="submit">查询</button><button  class="layui-btn" type="button" onclick="location.href='edit.jsp'">新增</button>
</form>
	

	</div>


<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table">
			<thead>
					<tr>
						<th>名称</th><th>性别</th><th>年龄</th><th>状态</th><th>房间</th><th>功能</th>
					</tr>
				</thead>
				<tbody>
			
			
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
