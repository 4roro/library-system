<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath }/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span> 书籍修改</strong>
		</div>
		<div class="body-content">
			<form class="form-x"
				action="${pageContext.request.contextPath}/BookServlet?action=update"
				method="post" onSubmit="return verifyInfo()"
				enctype="multipart/form-data">
				<div class="form-group">
					<div class="label">
						<label>书籍名称：</label>
					</div>
					<div class="field">
						<input type="text" name="id" id="id"
							value="${requestScope.books.id }" hidden /> <input
							style="width: 80%;" type="text" class="input" id="name"
							name="name" value="${requestScope.books.name } " />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍描述：</label>
					</div>
					<div class="field">
						<input style="width: 80%;" type="text" class="input" id="deatil"
							name="deatil" value="${requestScope.books.detail } " />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍作者：</label>
					</div>
					<div class="field">
						<input style="width: 80%;" type="text" class="input" id="deatil"
							name="author" value="${requestScope.books.author } " />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍出版：</label>
					</div>
					<div class="field">
						<input style="width: 80%;" type="text" class="input" id="deatil"
							name="publicer" value="${requestScope.books.publicer } " />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍库存：</label>
					</div>
					<div class="field">
						<input style="width: 80%;" type="text" class="input" id="count"
							name="count" value="${requestScope.books.countbook } " />

						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍单价：</label>
					</div>
					<div class="field">
						<input style="width: 80%;" type="text" class="input" id="pay"
							name="pay" value="${requestScope.books.pay } " />

					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>书籍类别：</label>
					</div>
					<div class="field">
						<select style="width: 80%;" name="typeid" id="typeid"
							class="input">
							<c:forEach items="${requestScope.types}" var="type">
								<option name="typeid" value="${type.id }">${type.name }</option>
							</c:forEach>
						</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>展示图片：</label>
					</div>
					<div class="field">
						<input type="file" id="url1" name="img" id="img"
							class="input tips" style="width: 25%; float: left;"
							value="${requestScope.books.img}"
							data-toggle="hover" data-place="right"
							data-image="${pageContext.request.contextPath}/${requestScope.books.img }" required/>
						<div class="tipss">图片尺寸：500*200</div>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>