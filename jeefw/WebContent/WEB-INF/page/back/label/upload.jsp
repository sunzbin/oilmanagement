<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-ie.css" />
		<script src="${contextPath}/static/layui/jquery.min.js"></script>
		<script src="${contextPath}/static/assets/js/ace/ace-extra.js"></script>
		<script src="${contextPath}/static/assets/js/ace/html5shiv.js"></script>
		<script src="${contextPath}/static/assets/js/ace/respond.js"></script>
		<script src="${contextPath}/static/assets/js/bootstrap.js"></script>
<title>上传文件</title>
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form action="${contextPath}/labelAxis/readExcel" name="Form" id="Form" method="post" enctype="multipart/form-data">
								<div id="zhongxin">
								<table style="width:95%;" >
									<tr>
										<td style="padding-top: 20px;">
											<input type="file" id="excel" name="excel" style="width:50px;" onchange="fileType(this)" />
										</td>
									</tr>
									<tr>
										<td style="text-align: center;padding-top: 10px;">
											<a class="btn btn-mini btn-primary" onclick="save();">导入</a>
											<button type="button" class="btn btn-w-m btn-white btnCancle">取消</button>
											<%-- <a class="btn btn-mini btn-success" onclick="window.location.href='${contextPath}/user/downExcel.do'">下载模版</a> --%>
										</td>
									</tr>
								</table>
								</div>
								<div id="zhongxin2" class="center" style="display:none"><br/><img src="${contextPath}/static/assets/img/jzx.gif" /><br/><h4 class="lighter block green"></h4></div>
							</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- ace scripts -->
	<script src="${contextPath}/static/assets/js/ace/ace.js"></script>
	<!-- 上传控件 -->
	<script src="${contextPath}/assets/js/ace/elements.fileinput.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="${contextPath}/static/assets/js/jquery.tips.js"></script>
	<script type="text/javascript">
		//保存
		function save(){
			if($("#excel").val()=="" || document.getElementById("excel").files[0] =='请选择xls格式的文件'){
				$("#excel").tips({
					side:3,
		            msg:'请选择xls格式的文件',
		            bg:'#AE81FF',
		            time:3
		        });
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		function fileType(obj){
			var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
		    if(fileType.trim() != '.xls' && fileType.trim() != '.xlsx'){
		    	$("#excel").tips({
					side:3,
		            msg:'请上传xls格式的文件',
		            bg:'#AE81FF',
		            time:3
		        });
		    	$("#excel").val('');
		    	document.getElementById("excel").files[0] = '请选择xls格式的文件';
		    }
		}
		/*获取弹出窗口索引*/
	    var index = parent.layer.getFrameIndex(window.name);
	    /*取消*/
	    $(".btnCancle").click(function(){
	        parent.layer.close(index);
	    })
	</script>
</body>
</html>
