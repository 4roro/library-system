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

    <table class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
        <th width=150>类别名称</th>       
        <th>类别描述</th>
        <th width=300>操作</th>       
      </tr>
      <c:forEach items="${requestScope.types }" var="type">      
        <tr>
          <td><input type="checkbox" name="id[]" value="1" />
            ${type.id }</td>
          <td>${type.name}</td>
          <td>${type.detail}</td>
          
          <td><div class="button-group"> <a class="button border-main" href="${pageContext.request.contextPath }/TypeServlet?action=findTypeById&&id=${type.id }"><span class="icon-edit"></span> 修改</a> <a class="button border-red" href="${pageContext.request.contextPath}/TypeServlet?action=delete&&id=${type.id}&&curPage=${page.curPage }" ><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
        </c:forEach>
      
 </table>
  </div>
</form>

</body></html>