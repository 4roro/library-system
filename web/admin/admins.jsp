<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="addadmin.jsp"> 添加管理员</a> </li>
        </ul>
    </div>
    <table width=400 class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
        <th width=150>类别名称</th>       
        <th>类别描述</th>
         
      </tr>
      <c:forEach items="${requestScope.admins }" var="admin">      
        <tr>
          <td><input type="checkbox" name="id[]" value="1" />
            ${admin.id }</td>
          <td>${admin.adminid}</td>
          <td>${admin.password}</td>
          
           </tr>
        </c:forEach>
      
 </table>
  </div>
</form>

</body></html>