<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
<form method="post"
		action="${pageContext.request.contextPath}/OrderServlet?action=findOrderByName&&curPage=1">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 借阅管理</strong>
<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					
					<li>搜索：</li>

					<li><input type="text" placeholder="请输入用户姓名" name="name"
						value="" id="name" class="input"
						style="width: 250px; line-height: 17px; display: inline-block" />
						<select class="input" name="struts"
						style="width: 250px; line-height: 17px; display: inline-block">
						<option class="input"
						style="width: 250px; line-height: 17px; display: inline-block" value="借阅中">借阅中</option>
						<option class="input"
						style="width: 250px; line-height: 17px; display: inline-block" value="已归还">已归还</option>
						<option class="input"
						style="width: 250px; line-height: 17px; display: inline-block" value="逾期">逾期</option>
						</select>
						<button type="submit" class="btn btn-success">搜索</button>
						&nbsp;&nbsp;<a class="button border-green" href="${pageContext.request.contextPath}/OrderServlet?action=updateOrder" >更新借阅信息</a>
						</li>
				</ul>
				 
			</div>
			</div>
		</div>
</form>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
               
        <th>借阅码</th>
        <th>借阅人</th>
       <th>书籍名称</th>
       <th>开始时间</th>
        <th>归还时间</th>
        <th>状态</th> 
        <th>操作</th>  
           
      </tr>
      <c:forEach items="${requestScope.page.list }" var="order">      
        <tr>
          <td><input type="checkbox" name="id[]" value="1" />
            ${order.id }</td>
          <td>${order.orderid}</td>
          <td>${order.name}</td>
          <td>${order.book}</td>
          <td>${order.begintime}</td> 
          <td>${order.endtime}</td> 
          <td>${order.struts}</td>
            <td>
				<c:choose>
				<c:when test="${order.struts eq '借阅中' }">
					<div class="button-group"> <a class="button border-blue" href="${pageContext.request.contextPath}/OrderServlet?action=return&&id=${order.id}&&bookid=${order.bookid }&&curPage=${page.curPage }" onclick="return window.confirm('确定用户已还书？')">归还</a> 
            
				</c:when>
				<c:when test="${order.struts eq '已归还' }">
					<div class="button-group"> <a class="button border-green"  >无操作</a> 
            </c:when>
				<c:otherwise>	
					<div class="button-group"> <a class="button border-blue" href="${pageContext.request.contextPath}/OrderServlet?action=return&&id=${order.id}&&bookid=${order.bookid }&&curPage=${page.curPage }" onclick="return window.confirm('确定用户已还书？')">归还</a> 
            
					<div class="button-group"> <a class="button border-red" href="new.jsp" onclick="return window.confirm('确定提醒还书？')">提醒还书</a> 			
				</c:otherwise>
			</c:choose>
             </div>
					</div>
					</div>
					</div>
			</td>
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
					<a href="${pageContext.request.contextPath }/OrderServlet?action=findAll&&curPage=1">首页</a>
								</c:otherwise>
								</c:choose>
								 
								 <c:choose>
								 <c:when test="${page.curPage eq page.totalPage  }">
								 <span style="color: gray;font-size: 12px">尾页</span>
								 </c:when>
								 <c:otherwise>
								 <a href="${pageContext.request.contextPath }/OrderServlet?action=findAll&&curPage=${page.totalPage}">尾页</a>
								 </c:otherwise>
								 </c:choose>
								 
								 <c:choose>
								 <c:when test="${page.curPage eq 1 }">
								 <span style="color: gray;font-size: 12px">上一页</span>
								 </c:when>
								 <c:otherwise>
					<a href="${pageContext.request.contextPath }/OrderServlet?action=findAll&&curPage=${page.curPage-1 }">上一页</a>
								</c:otherwise>
								 </c:choose>
								 
								  <c:choose>
								 <c:when test="${page.curPage eq page.totalPage  }">
								 <span style="color: gray;font-size: 12px">下一页</span>
								 </c:when>
								 <c:otherwise>
					<a href="${pageContext.request.contextPath }/OrderServlet?action=findAll&&curPage=${page.curPage+1 }">下一页</a>
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