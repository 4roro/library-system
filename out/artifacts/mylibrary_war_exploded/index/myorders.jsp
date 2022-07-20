<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书馆</title>
<base href="${pageContext.request.contextPath }/index/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

       <div class="header">
       		<div class="logo"><a href="index.html"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id="menu">
            <ul>                                                                       
            <li ><a href="index.jsp">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/BookServlet?action=findAll&&curPage=1">书籍</a></li>
					<li><a href="${pageContext.request.contextPath}/TypeServlet?action=findAll">分类</a></li>
					<li><a href="${pageContext.request.contextPath}/NoticeServlet?action=findNotice">公告</a></li>
					
					<li class="selected"><a href="${pageContext.request.contextPath}/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=1">我的借阅</a></li>
					<li><a href="bookdetail.jsp">个人信息</a></li>
					<c:choose>
						<c:when test="${sessionScope.user.id eq null }">
						<li ><a href="myacount.jsp">登录</a></li>
					</c:when>
						<c:otherwise>
						<li ><a href="${pageContext.request.contextPath}/UserServlet?action=logout">退出</a>
						</li>			
						</c:otherwise>
					</c:choose>
            </ul>
        </div>     
            
            
       </div> 
       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>我的借阅</div>
        
        	<div class="feat_prod_box_details">
            
            <table class="cart_table">
            	<tr class="cart_title">
                	<td>书籍展示</td>
                	<td>书籍名称</td>
                    <td>节约时间</td>
                    <td>归还时间</td>
                    <td>状态</td>               
                </tr>
                <c:forEach items="${requestScope.page.list }" var="order"> 
            	<tr>
                	<td><a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${order.bookid}&&name=${order.book}"><img src="${pageContext.request.contextPath }/${order.bookimg}" style="width:80px;height:110px" alt="" title="" border="0" class="cart_thumb" /></a></td>
                	<td><a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${order.bookid}&&name=${order.book}">${order.book}</a></td>
          			<td>${order.begintime}</td> 
          			<td>${order.endtime}</td> 
          			<td><font size="+1" color=red>${order.struts}</font></td>              
                </tr>          
            	 </c:forEach>             
            
            </table>
            <div class="pagination">
             <c:if test="${page.totalPage>0}">
			<br>
			<div
				
				height=50>
				<span class="left_bt2"> 第<c:out value="${page.curPage}" />页&nbsp;&nbsp;
					共<c:out value="${page.totalPage}" />页
				</span>&nbsp;&nbsp;
				<c:choose>
					<c:when test="${page.curPage eq 1 }">
						<span style="color: gray; font-size: 12px">[首页]</span>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath }/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=1">[首页]</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${page.curPage eq page.totalPage  }">
						<span style="color: gray; font-size: 12px">[尾页]</span>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath }/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=${page.totalPage}">[尾页]</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${page.curPage eq 1 }">
						<span style="color: gray; font-size: 12px">[上一页]</span>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath }/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=${page.curPage-1 }">[上一页]</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${page.curPage eq page.totalPage  }">
						<span style="color: gray; font-size: 12px">[下一页]</span>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath}/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=${page.curPage+1 }">[下一页]</a>
					</c:otherwise>
				</c:choose>

			</div>
		</c:if></div>   

            </div>	

        <div class="clear"></div>
        </div><!--end of left content-->
        
        <div class="right_content">

             <div class="title">
					<span class="title_icon"><img src="images/bullet3.gif"
						alt="" title="" /></span>关于我们
				</div>
				<div class="about">
					<p>
						<img src="images/about.gif" alt="" title="" class="right" />
						本图书馆致力于为广大热爱学习者提供更好的学习资源，本图书馆书籍可以外售与借阅，并可续借。
					</p>

				</div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>馆长推荐</div>
                    <div class="new_prod_box">
                        <a href="index.jsp">热门书籍</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="index.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="index.jsp">必读书刊</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="index.jsp"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                    
                    
                    <div class="new_prod_box">
                        <a href="index.jsp">product name</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="index.jsp"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>               
             
             </div>
             
             <div class="right_box">
             
             	<div class="title">
					<span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span><a
						href="${pageContext.request.contextPath}/TypeServlet?action=findAll">书籍类别</a></div>
                <ul class="list">
				</ul>

				 <div class="title">
						<span class="title_icon"><img src="images/bullet6.gif"
													  alt="" title="" /></span><a
						 href="${pageContext.request.contextPath}/NoticeServlet?action=findNotice">人事公告</a>
				 </div>
                
                <ul class="list">
                                           
                </ul>      
             
             </div>         
             
        
        </div><!--end of right content-->
        
        
       
       
       <div class="clear"></div>
       </div><!--end of center content-->

       <div class="footer">
       	<div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://www.cssmoban.com/" title="free templates">cssmoban</a></div>
        <div class="right_footer">
        <a href="#">home</a>
        <a href="#">about us</a>
        <a href="#">services</a>
        <a href="#">privacy policy</a>
        <a href="#">contact us</a>
        </div>

       </div>
</div>
</body>
</html>