<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="TListTest" method="get">
出発駅：<input type="text" name="from_st">
到着駅：<input type="text" name="to_st">

<p>※交通機関はhiddenで送ってます（電車を選択した状態）</p>
<!--  <input type="hidden" name="transit_no" value="2">-->
<input type="submit" value="おくる">
</form>
</body>
</html>