<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <base href="${pageContext.request.contextPath }/admin/">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>用户注册</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/user.css">
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 用户注册</strong></div>
    <div class="body-content">
        <form   action="${pageContext.request.contextPath}/UserServlet?action=register" omsubmit='return checkForm()' method="post">


            <div class="field">
                <input style="width:80%;" placeholder="请输入用户姓名" type="text" class="input" id="name" name="name" required />
                <div class="tips"></div>
            </div><br>
            <div class="field">
                <input style="width:80%;" placeholder="请输入用户联系方式" minlength="11" maxlength="11"
                       pattern="^(0|86|17951)?1[0-9]{10}" type="text" class="input" id="tel" name="tel" required />
                <div class="tips"></div>
            </div><br>
            <div class="field">
                <input style="width:80%;" placeholder="请输入密码" minlength="6" maxlength="11"
                       type="text" class="input" id="password" name="password" required />
                <div class="tips"></div>
            </div><br>
            <div class="field">
                <input style="width:80%;" placeholder="请输入用户身份证号" minlength="18" maxlength="18"
                       name="IDCardNumber" id="IDCardNumber" type="text"  onblur="isCardNo(this.value)" class="input"   required/>
                <label id="errorMsg"></label>
                <div class="tips"></div>
            </div><br>
            <div class="field">
                <input style="width:80%;" placeholder="请输入用户家庭住址" type="text" class="input" id="address" name="address" required />
                <div class="tips"></div>
            </div><br>




            <div class="form-group" >
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function isCardNo(card)
    {

// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
        if(reg.test(card) === false)
        {
            alert("身份证输入不合法");
            return false;
        } else{
            return true;
        }
    }
</script>

</body>
</html>
