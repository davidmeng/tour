<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">   
<title>Hello World</title>
<link  href='<c:url value="/resources/css/bootstrap.css" />' rel="stylesheet">
<link  href='<c:url value="/resources/css/signin.css" />' rel="stylesheet">
<style type="text/css">

	#tourForm {
		width: 500px;
	}
	#tourForm label {
		width: 250px;
	}
	#tourForm label.error, #tourForm input.submit {
		margin-left: 253px;
	}
</style>
<script src="<c:url value="/resources/js/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>

</head>
<body>
<div class="container">
<form action="compare" method="post" id="tourForm" class="form-signin" onsubmit="return checkForm()">
	<h2 class="form-signin-heading text-center">Hello World</h2>
	<input placeholder="address" class="form-control" type="url" name="urls" onblur="addAddress($(this))"/>
	<input placeholder="address" class="form-control" type="url" name="urls" onblur="addAddress($(this))"/>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	<div id="alert" style="display:none" class="alert alert-warning" role="alert"></div>
</form>
</div>
<script type="text/javascript">

function checkForm(){

	var number = 0 ;
	
	$.each( $("input[name='urls']"), function(){
	  	if ($(this).val()!=''){
	  		number++ ;
		}
	});

	if (number < 2 ){
		alert("too short");
		return false ;
	}
}

function addAddress(o){

	value = o.val();

	if(o.val()==''){
		$("#alert").show("200");
		$("#alert").html("Can't be empty !!!");
		return ;
	}else if (!/^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test( value )){
		o.val('');
		o.focus();
		$("#alert").show("200");
		$("#alert").html("Invalid address !!!");
		return ;
		}
	$("#alert").hide("200");
	$("button").before('<input placeholder="address" class="form-control" type="text" name="urls" onblur="addAddress($(this))"/>');
	
}



</script>
</body>
</html>