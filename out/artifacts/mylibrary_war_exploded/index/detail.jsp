<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书馆</title>
<base href="${pageContext.request.contextPath }/index/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/index/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/index/lightbox.css" type="text/css" media="screen" />
	
	<script src="${pageContext.request.contextPath }/index/js/prototype.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/index/js/scriptaculous.js?load=effects" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/index/js/lightbox.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/index/js/java.js"></script>
</head>
<body>
<div id="wrap">

       <div class="header">
			<div class="logo">
				<a href="index.jsp"><img src="images/logo.gif" alt="" title=""
					border="0" /></a>
			</div>
			<div id="menu">
				<ul>
					<li ><a href="index.jsp">首页</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/BookServlet?action=findAll&&curPage=1">书籍</a></li>
					<li><a href="${pageContext.request.contextPath}/TypeServlet?action=findAll">分类</a></li>
					<li><a href="${pageContext.request.contextPath}/NoticeServlet?action=findNotice">公告</a></li>
					<c:choose>
						<c:when test="${sessionScope.user.id eq null }">
						<li class="selected"><a href="myacount.jsp">登录</a></li>
					</c:when>
						<c:otherwise>
						<li class="selected"><a href="${pageContext.request.contextPath}/UserServlet?action=logout">退出</a>
						</li>			
						</c:otherwise>
					</c:choose>
					<li><a href="${pageContext.request.contextPath}/OrderServlet?action=findMyOrder&&userid=${sessionScope.user.id}&&curPage=1">我的借阅</a></li>
					<li><a href="bookdetail.jsp">个人信息</a></li>
				</ul>
			</div>


		</div>
       
       
       <div class="center_content">
       	<div class="left_content">
        	<div class="crumb_nav">
            <a href="index.jsp">首页</a> &gt;&gt; 书籍详情
            </div>
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>书籍详情</div>
        
        	<div class="feat_prod_box_details">
            
            	<div class="prod_img"><img src="${pageContext.request.contextPath}/${requestScope.books.img }" style="width:100px;height:140px" alt="" title="" border="0" />
                <br /><br />
                <a href="${pageContext.request.contextPath}/${requestScope.books.img }" style="width:300px;height:450px" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0" /></a>
                </div>
                
                <div class="prod_det_box">
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title">${requestScope.books.name } &nbsp;&nbsp;详情介绍</div>
                    <p class="details">${requestScope.books.detail } </p>
                    <div class="price"><strong>价格:</strong> <span class="red">${requestScope.books.pay }元    star:${requestScope.books.star }次&nbsp;&nbsp;当前馆藏:${requestScope.books.countbook }本</span></div>
                     <div class="clear">作者:${requestScope.books.author }  出版社: ${requestScope.books.publicer }</div>
                    </div>
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>	
            
              
            <div id="demo" class="demolayout">

                <ul id="demo-nav" class="demolayout">
                <li><a class="active" href="#tab1">书籍评论</a></li>
                <li><a class="" href="#tab2">推荐更多</a></li>
                </ul>
    
            <div class="tabs-container">
            
                    <div style="display: block;" class="tab" id="tab1">
                        <p class="more_details">${requestScope.books.detail } </p>  
                        <p class="more_details" >${requestScope.books.comments }</p>                                 
                    
                    <form action="${pageContext.request.contextPath}/BookServlet?action=addComments" method="post">
                    <input type="text" name="bookname" value="${requestScope.books.name } " hidden/>
                    <input type="text" name="before" value="${requestScope.books.comments } " hidden/>
                    <input type="text" name="bookid" value="${requestScope.books.id }" hidden/>
                    <input type="text" name="name" value="${sessionScope.user.name} " hidden/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="comments" style="width:350px;height:22px" >
                    <input type="submit" style="width:50px;height:25px" value="评论">
                    </form>
                    </div>	
                    
                    <div style="display: none;" class="tab" id="tab2">
                    <c:forEach items="${requestScope.book }" var="book" begin="0"
					end="2">
                    <div class="new_prod_box">
                        <a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${book.id}&&name=${book.name}" >${book.name }</a>
                        <div class="new_prod_bg">
                        <a href="${pageContext.request.contextPath}/BookServlet?action=findById&&id=${book.id}&&name=${book.name}" ><img src="${pageContext.request.contextPath }/${book.img}" style="width:70px;height:90px" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    </c:forEach>
                      <div class="clear"></div>
                            </div>	
            
            </div>
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
					<a href="cart.html" class="view_cart">查看我的</a>

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
					<c:forEach items="${requestScope.booknew }" var="book" begin="3"
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
							<li><a href="#">${type.name }</a></li>
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

<script type="text/javascript">

var tabber1 = new Yetii({
id: 'demo'
});

</script>
</html>