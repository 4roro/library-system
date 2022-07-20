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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>  
	
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/UserServlet?action=findUsers&&curPage=1">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 书籍管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="adduser.jsp"> 添加用户</a> </li>
        <li>搜索：</li>
        
        <li>
          <input type="text" placeholder="请输入用户名称" name="name"  value="" id="name" class="input" style="width:250px; line-height:17px;display:inline-block" />
         <button type="submit"  class="btn btn-success">搜索</button></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
        <th>用户名</th>       
        <th>联系方式</th>
        <th>家庭住址</th>
        <th>身份证号码</th>
        
        <th>操作</th>       
      </tr>
      <c:forEach items="${requestScope.page.list }" var="user">      
        <tr>
          <td><input type="checkbox" name="id[]" value="1" />
            ${user.id }</td>
          <td>${user.name}</td>
          <td>${user.tel}</td>
          <td>${user.address}</td>  
           <td>${user.card}</td>         
          <td><div class="button-group">  <a class="button border-red" href="${pageContext.request.contextPath}/UserServlet?action=delete&&id=${user.id}&&curPage=${page.curPage }" ><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
        </c:forEach>
      
      <tr>
								<td class="line_table" align="center" colspan="11" height="20">
								<div class="pagelist"><span class="left_bt2">第${page.curPage }页
										&nbsp;&nbsp;共${page.totalPage }页
								</span>&nbsp;&nbsp; 
								<c:choose>
								<c:when test="${page.curPage eq 1 }">
								 <span style="color: gray;font-size: 12px">首页</span>
								</c:when>
								<c:otherwise>
					<a href="${pageContext.request.contextPath }/UserServlet?action=findUsers&&curPage=1">首页</a>
								</c:otherwise>
								</c:choose>
								 
								 <c:choose>
								 <c:when test="${page.curPage eq page.totalPage  }">
								 <span style="color: gray;font-size: 12px">尾页</span>
								 </c:when>
								 <c:otherwise>
								 <a href="${pageContext.request.contextPath }/UserServlet?action=findUsers&&curPage=${page.totalPage}">尾页</a>
								 </c:otherwise>
								 </c:choose>
								 
								 <c:choose>
								 <c:when test="${page.curPage eq 1 }">
								 <span style="color: gray;font-size: 12px">上一页</span>
								 </c:when>
								 <c:otherwise>
					<a href="${pageContext.request.contextPath }/UserServlet?action=findUsers&&curPage=${page.curPage-1 }">上一页</a>
								</c:otherwise>
								 </c:choose>
								 
								  <c:choose>
								 <c:when test="${page.curPage eq page.totalPage  }">
								 <span style="color: gray;font-size: 12px">下一页</span>
								 </c:when>
								 <c:otherwise>
					<a href="${pageContext.request.contextPath }/UserServlet?action=findUsers&&curPage=${page.curPage+1 }">下一页</a>
								</c:otherwise>
								 </c:choose>
								</div></td>
							</tr>
    </table>
  </div>
</form>
<script type="text/javascript">

function del(id){
	if(confirm("您确定要删除吗?")){
		
	}
}

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

</script>
</body></html>