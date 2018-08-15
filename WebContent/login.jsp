<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"
    pageEncoding="utf-8"%>
<%@ include file="model/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - 登录</title>
    
    <link href="libs/css/bootstrap.min.css" rel="stylesheet">
    <link href="libs/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="libs/css/animate.css" rel="stylesheet">
    <link href="libs/css/style.css" rel="stylesheet">
    <link href="libs/css/login.css" rel="stylesheet">
    
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
        var a=0;
        function changerimg(o) {
			o.src="img?a="+(a++);
		}
        function save()
        {
        	
        	$.post("login",$("#login-form").serialize(),function(json)
        			{
        		 if(json.status==1)
    			 {
    			
    			alert("登陆成功  教师"+json.text)
    			location.href="index.jsp"
    			 
    			 }
        		 else if(json.status==3)
        			 {
        			 alert("登陆成功  学生"+json.text)
         			 location.href="Studentindex.jsp"
        			 }
        		 else
        			 {
        			 alert("登陆失败")
        			 }
        		}
        	,"json");
        	
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-12">
                <form id="login-form" method="post" action="User/login.action">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到后台框架</p>
                    <input type="text" id="username" name="username" class="form-control uname" placeholder="用户名" />
                    <input type="password" id="password" name="password" class="form-control pword m-b" placeholder="密码" />
                    <input type="text"  name="code" class="form-control" style="width:180px;display: inline;" placeholder="验证码" />
                    <img src="img" onclick="changerimg(this);">
                    <a href="">忘记密码了？</a>
               
                <button class="btn btn-success btn-block" type="button" onclick="save()">登录</button>
                 </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
               
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	function submitForm() {
    		/*
    		1、验证用户名
    		1.1、验证用户名是否为空
    		1.2、是否合法：4-8数字或字母
    		2、密码不能为空
    		3、ajax提交用户名和密码，并且接受后台返回的json数据
    		*/
    		var username = $("#username").val();
    		var password = $("#password").val();
    		//1.1、验证用户名是否为空
    		if(util.isNull(username)) {
    			mylayer.errorMsg("用户名不能为空");
    			return;
    		}
    		
    		//1.2、是否合法：4-8数字或字母
    		if(!isUsernameValid(username)) {
    			mylayer.errorMsg("用户名不合法，4-8数字或字母");
    			return;
    		}
    		
    		//2、密码不能为空
    		if(util.isNull(password)) {
    			mylayer.errorMs
    			g("密码不能为空");
    			return;
    		}
    		
    		//3、ajax提交用户名和密码，并且接受后台返回的json数据
    		/*$.ajax({
    			url : "manager/user/login.action",
    			type : "POST",
    			dataType : "json",
    			data : $("#login-form").serialize(),
    			success : function(data) {
    				if(data.code == util.SUCCESS) {
    					mylayer.successUrl(data.msg, "manager/index.action");
    				} else {
    					mylayer.errorMsg(data.msg);
    				}
    			}
    		});*/
    	}
    	
    	/*是否合法：4-8数字或字母*/
    	function isUsernameValid(value) {
    		var pattern = /^[0-9a-zA-Z]{4,8}$/;
    		return pattern.test(value);
    	} 
    </script>
</body>

</html>
