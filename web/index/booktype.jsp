<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书馆</title>
<base href="${pageContext.request.contextPath }/index/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

       <div class="header">
       		<div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
        <div id="menu">
            <ul>                                                                       
          
					<li ><a href="index.jsp">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/BookServlet?action=findAll&&curPage=1">书籍</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/TypeServlet?action=findAll">分类</a></li>
					<li><a href="${pageContext.request.contextPath}/NoticeServlet?action=findNotice">公告</a></li>
					
					<li><a href="${pageContext.request.contextPath}/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=1">我的借阅</a></li>
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
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>书籍类别</div>
        
        	<div class="feat_prod_box_details">
            
                 
            <ul class="list">
						<c:forEach items="${requestScope.types }" var="type" >
							<li><a href="${pageContext.request.contextPath}/BookServlet?action=findAllByType&&curPage=1&&typeid=${type.id}"><font color=red size="+1">${type.name }</font></a></li>
							<li>${type.detail }</li>
						</c:forEach>
			</ul>

            </div>	

        <div class="clear"></div>
        </div><!--end of left content-->
        
       <div class="right_content">

				<div class="cart">
					<div class="title">
						<span class="title_icon"><img src="images/cart.gif" alt=""
							title="" /></span>我的借阅
					</div>
					<div class="home_cart_content">
						<span class="red"></span>
					</div>
					<a href="myorders.jsp" class="view_cart">查看我的</a>

				</div>

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

					<div class="title">
						<span class="title_icon"><img src="images/bullet4.gif"
							alt="" title="" /></span>必读书刊
					</div>
					<c:forEach items="${requestScope.books }" var="book" begin="3"
						end="6">
						<div class="new_prod_box">
							<a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${book.id}&&name=${book.name}">${book.name }</a>
							<div class="new_prod_bg">
								<span class="new_icon"><img src="images/promo_icon.gif"
									alt="" title="" /></span> <a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${book.id}&&name=${book.name}"><img
									src="${pageContext.request.contextPath }/${book.img}" style="width:70px;height:90px"  alt="" title="" class="thumb"
									border="0" /></a>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="right_box">

					<div class="title">
						<span class="title_icon"><img src="images/bullet5.gif"
							alt="" title="" /></span>书籍类别
					</div>

					<ul class="list">
						<c:forEach items="${requestScope.types }" var="type" begin="0"
							end="6">
							<li><a href="${pageContext.request.contextPath}/BookServlet?action=findAllByType&&curPage=1&&typeid=${type.id}">${type.name }</a></li>
						</c:forEach>
					</ul>

					<div class="title">
						<span class="title_icon"><img src="images/bullet6.gif"
							alt="" title="" /></span>人事公告
					</div>

					<ul class="list">
						<c:forEach items="${requestScope.notice }" var="notice" begin="0"
							end="6">
							<li><a href="#">${notice.title }&nbsp;&nbsp;</a></li>
						</c:forEach>
					</ul>

				</div>


			</div>
			<!--end of right content-->

			<div class="clear"></div>
		</div>
		<!--end of center content-->
		<div class="footer">
			<div class="left_footer">
				<img src="images/footer_logo.gif" alt="" title="" /><br /> <a
					href="http://www.cssmoban.com/" title="free templates">cssmoban</a>
			</div>
			<div class="right_footer">
				<a href="#">home</a> <a href="#">about us</a> <a href="#">services</a>
				<a href="#">privacy policy</a> <a href="#">contact us</a>

			</div>
		</div>
	</div>
</body>
</html>