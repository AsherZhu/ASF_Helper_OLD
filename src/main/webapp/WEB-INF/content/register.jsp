<%--
  Created by IntelliJ IDEA.
  User: zhushuai.net@gmail.com
  Date: 2018/1/30
  Time: 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>龙逸网络</title>

    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
   <script src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>


</head>
<body>
<script src="assets/js/theme.js"></script>

<script type="text/javascript">
    $(function(){
        $('#user-email').keyup(function(e){
            var elem = this;
            if(elem.value.length==0)return;
            $.post('${pageContext.request.contextPath}/user!test.html',{'ty':'email','val':elem.value},function(data){
                if(data==1){
                    $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                }else{
                    $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().append("<span class='am-icon-check'></span>");
                }
            });
        });
        $('#user-name').keyup(function(e){
            var elem = this;
            if(elem.value.length==0)return;
            $.post('${pageContext.request.contextPath}/user!test.html',{'ty':'username','val':elem.value},function(data){
                if(data==1){
                    $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                }else{
                    $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().append("<span class='am-icon-check'></span>");
                }
            });
        });

        $("#btn-submit").click(function(){
            $.post('${pageContext.request.contextPath}/user!save2.html',$('#form1').serialize(),function(data){
                switch(data){
                    case '0':alert('您尚未同意注册协议');break;
                    case '1':alert('验证码填写错误');break;
                    case '2':alert('用户名已经存在，无法注册，请更换');break;
                    case '3':alert('邮箱已经被注册，请更换');break;
                    case '4':alert('淘宝用户名已存在，请重新输入');break;
                    case '5':window.location.href='${pageContext.request.contextPath}/login.html'; break;
                    default:alert('服务器数据异常');break;
                }
            });
        });

    });
</script>


<div class="am-g tpl-g">

    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-title">注册用户</div>
            <span class="tpl-login-content-info"> 创建一个新的用户 </span>


            <form id="form1" action="" method="post"
                  class="am-form tpl-form-line-form" name="f1">

                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="user-name"
                           placeholder="用户名" name="username">
                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" id="password"
                           placeholder="请输入密码" name="password">
                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" id="er-password"
                           placeholder="再次输入密码">
                </div>
                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="user-email"
                           placeholder="邮箱" name="email">
                </div>
                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="user-taobaoId"
                           placeholder="淘宝ID" name="taobaoId">
                </div>
                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="code"
                           placeholder="验证码" name="cc"> <img alt=""
                                                             src="<%=request.getContextPath()%>/verify.html"
                                                             onclick="this.src='<%=request.getContextPath()%>/verify.html?t='+Math.random()">

                </div>

                <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox" name="ok"> <label
                        for="remember-me"> 我已阅读并同意 <a href="javascript:;">《用户注册协议》</a>
                </label>
                </div>

                <div class="am-form-group">

                    <button id="btn-submit" type="button"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">
    function reloadyzm(img){
        //img.src = '${pageContext.request.contextPath}/verify.html?t='+Math.random();
        img.src = '<%=request.getContextPath()%> /verify.html?t='+ Math.random();
    }
</script>
</body>

</html>
