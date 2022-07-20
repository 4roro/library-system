<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath }/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 书籍添加</strong></div>
  <div class="body-content">
    <form  class="form-x" action="${pageContext.request.contextPath}/OrderServlet?action=borrow" method="post" 
						>      
      <div class="form-group">
        <div class="label">
          <label>书籍名称：</label>
        </div>
        <div class="field">
        <input type="text" name="bookid" id="bookid" value="${requestScope.books.id }" hidden/>
          <input style="width:80%;" type="text" class="input"  value="${requestScope.books.name } " readonly/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>书籍展示：</label>
        </div>
        <div class="field">
        <img src="${pageContext.request.contextPath}/${requestScope.books.img }" style="width:100px;height:150px">
         <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>联系方式：</label>
        </div>
        <div class="field">
        
          <input style="width:80%;" type="text" class="input" id="tel" name="tel" value="" required/>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>归还时间：</label>
        </div>
        <div class="field">
        
          <input style="width:80%;" type="date" class="input" 

  name="endtime"  required/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>


</body>
</html>