<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>


    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">
    <script src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>

</head>

<body data-type="login">
<script src="assets/js/theme.js"></script>
<div class="am-g tpl-g">

    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">

            </div>


            <form id="form-1" class="am-form tpl-form-line-form">
                <div class="am-form-group">
                    <input type="text" name="username" class="tpl-form-input" id="user-name" placeholder="请输入账号"
                           value="${cookie['login.username'].value }">

                </div>

                <div class="am-form-group">
                    <input type="password" name="password" class="tpl-form-input" id="user-pass" placeholder="请输入密码"
                           value="${cookie['login.password'].value }">

                </div>
                <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox" name="rem" ${cookie['login.username']==null? '':'checked'} >
                    <label for="remember-me">

                        记住账号和密码
                    </label>

                </div>


                <div id="slider1" class="slider"></div>

                <div class="am-form-group">
                    <button id="btn" type="button"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">安全登录
                    </button>
                </div>
                <br>
                <a href="${pageContext.request.contextPath }/register.html">没有账号，去注册</a>
            </form>
        </div>
    </div>
</div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">
    $(function () {
        var alreadyValidate = false; //是否已经通过滑块验证
        $("#slider1").slider({
            callback: function (result) {
                alreadyValidate = result;
            }
        });


        $("#btn").click(function () {
            if (!alreadyValidate) {
                alert("请先拖动滑块验证");
                return;
            }
            $.post('${pageContext.request.contextPath}/user!login.html', $("#form-1").serialize(), function (data) {
                if (data === "login-success") {
                    alert("登录成功");
                    window.location.href = '${pageContext.request.contextPath}/manage/index.html';
                }
                if (data === "login-error-UsernameNotExist") {
                    alert("用户名不存在");
                }
                if (data === "login-error-PasswordError") {
                    alert("您填写的密码不正确");
                }
            });
        });
    });
</script>
</body>

</html>