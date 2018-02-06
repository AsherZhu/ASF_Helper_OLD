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
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
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
    $(function () {

        // 检查用户名是否存在
        $('#user-name').keyup(function (e) {
            var elem = this;
            if (elem.value.length === 0) return;
            $.post('${pageContext.request.contextPath}/user!test.html', {
                'ty': 'username',
                'val': elem.value
            }, function (data) {
                if (data == 1) {
                    $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                } else {
                    $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().append("<span class='am-icon-check'></span>");
                }
            });
        });

        // 密码验证状态：['0','0']：通过，['1','0']：不符合规范，['0','1']：两次输入不一致，['1','1']：都不符合
        var passwordValidation = ['1', '1'];
        // 检查用户名是否符合规范:6-20位字母数字组合
        $('#user-password').keyup(function (e) {
            var reg = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,20}$/;
            var elem = this;
            if (reg.test(elem.value)) {
                $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                $(elem).parent().append("<span class='am-icon-check'></span>");
                passwordValidation.splice(0, 1, '0');
            } else {
                $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                passwordValidation.splice(0, 1, '1');

            }
            console.log(passwordValidation.toString());
        });

        // 检查两次输入密码是否一致
        $('#re-password').keyup(function (e) {
            var elem = this;
            var passwd1 = document.getElementById('user-password').value;
            var passwd2 = elem.value;
            if (passwd1 === passwd2) {
                $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                $(elem).parent().append("<span class='am-icon-check'></span>");
                passwordValidation.splice(1, 1, '0');
            } else {
                $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                passwordValidation.splice(1, 1, '1');
            }
            console.log(passwordValidation.toString());
        });

        // 检查Email是否存在
        $('#user-email').keyup(function (e) {
            var elem = this;
            if (elem.value.length === 0) return;
            $.post('${pageContext.request.contextPath}/user!test.html', {
                'ty': 'email',
                'val': elem.value
            }, function (data) {
                if (data == 1) {
                    $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                } else {
                    $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().append("<span class='am-icon-check'></span>");
                }
            });
        });


        // 检查淘宝ID是否存在
        $('#user-taobaoId').keyup(function (e) {
            var elem = this;
            if (elem.value.length === 0) return;
            $.post('${pageContext.request.contextPath}/user!test.html', {
                'ty': 'taobaoId',
                'val': elem.value
            }, function (data) {
                if (data == 1) {
                    $(elem).parent().removeClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().addClass("am-form-error am-form-icon am-form-feedback").append("<span class='am-icon-times'></span>");
                } else {
                    $(elem).parent().addClass("am-form-success am-form-icon am-form-feedback").find("span").remove();
                    $(elem).parent().append("<span class='am-icon-check'></span>");
                }
            });
        });

        // 最终检查 弹框如果无误 提交服务器进行注册
        $("#btn-submit").click(function () {
            if (passwordValidation[0] == '1') {
                alert('密码不符合规范，请重新输入');
            } else if (passwordValidation[1] == '1') {
                alert('两次密码输入不一致，请重新输入');
            } else {
                $.post('${pageContext.request.contextPath}/user!save2.html', $('#form1').serialize(), function (data) {
                    switch (data) {
                        case '0':
                            alert('您尚未同意注册协议');
                            break;
                        case '1':
                            alert('验证码填写错误');
                            break;
                        case '2':
                            alert('用户名已经存在，无法注册，请更换');
                            break;
                        case '3':
                            alert('邮箱已经被注册，请更换');
                            break;
                        case '4':
                            alert('淘宝用户名已存在，请重新输入');
                            break;
                        case '5':
                            window.location.href = '${pageContext.request.contextPath}/login.html';
                            break;
                        default:
                            console.log(passwordValidation);
                            alert("服务器数据异常，请稍候再试");
                            break;
                    }
                });
            }
        });
    });
</script>


<div class="am-g tpl-g">
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
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
                    <input type="password" class="tpl-form-input" id="user-password"
                           placeholder="请输入密码（6-20位字母数字组合）" name="password">
                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" id="re-password"
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
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交
                    </button>
                </div>
                <a href="${pageContext.request.contextPath }/login.html">已有账号，去登录</a>
            </form>
        </div>
    </div>
</div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">
    function reloadyzm(img) {
        //img.src = '${pageContext.request.contextPath}/verify.html?t='+Math.random();
        img.src = '<%=request.getContextPath()%> /verify.html?t=' + Math.random();
    }
</script>
</body>

</html>
