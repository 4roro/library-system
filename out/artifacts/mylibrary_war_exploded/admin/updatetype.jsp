<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<base href="${pageContext.request.contextPath }/admin/">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 类别修改</strong></div>
  <div class="body-content">
    <form action="${pageContext.request.contextPath}/TypeServlet?action=update" method="post" 
						>      
     
        
        <div class="field">
        <input type="text" name="id" id="id"
							value="${requestScope.type.id }" hidden />
          <input style="width:80%;" value="${requestScope.type.name }" type="text" class="input" id="name" name="name"  required/>
          <div class="tips"></div>
        </div>
     
      <div class="form-group" >
        
        
      </div>
      
        <div class="field">
          <textarea style="width:80%;height:300px" value="${requestScope.type.detail}" id="detail" name="detail" required>${requestScope.type.detail}</textarea>
          <div class="tips"></div>
        </div>

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
</body>
</html>