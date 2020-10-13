<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%
/** 登録か編集かの判断値**/
String menulist=(String)request.getAttribute("menulist");

/** もし値の受け渡しがある場合用**/
//日付
String day=(String) request.getAttribute("day");
//片道or往復
String route_no=(String) request.getAttribute("route_no");
//交通機関
String transit_no=(String)request.getAttribute("transit_no");
//出発駅
String from_st=(String)request.getAttribute("from_st");
//到着駅
String to_st=(String)request.getAttribute("to_st");
//金額
String price=(String)request.getAttribute("price");

/** DBから「route」を取得する用 **/
ResultSet rs= (ResultSet) request.getAttribute("rs");

/** DBから「transit」を取得する用 **/
ResultSet rs1= (ResultSet) request.getAttribute("rs1");

String errmsg=(String)request.getAttribute("errmsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="addTest.css">
<title>登録テスト用</title>
</head>
<body>
交通費登録システム：登録
<form class="addlist" method="post">

<dl>
<dt>日付：</dt>
<dd>
<%
if(day!=null){
%><input type="text" name="day" value="<%=day%>">
<%}else{ %>
<input type="text" name="day">
<%
}
%>
</dd>

<dt>片道or往復：</dt>
<dd><select name="route_no">
<%
while(rs.next()){
	if(rs.getString("route_no").equals(route_no)){
%>
<option value="<%=rs.getString("route_no")%>" selected><%=rs.getString("route_name")%></option>
<%} else{%>
<option value="<%=rs.getString("route_no")%>"><%=rs.getString("route_name")%></option>
<%}
}%>
</select>
</dd>

<dt>&nbsp;</dt>
<dd>
<input type="hidden" name="menulist" value="<%=menulist%>">
<input class="transitdatabt" type="submit" formaction="TListTest" value="以前のデータを参照">
</dd>

<dt>交通機関：</dt>
<dd><select name="transit_no">
<%
while(rs1.next()){
	if(rs1.getString("transit_no").equals(transit_no)){
%>
<option value="<%=rs1.getString("transit_no")%>" selected><%=rs1.getString("transit_name")%></option>
<%} else{%>
<option value="<%=rs1.getString("transit_no")%>"><%=rs1.getString("transit_name")%></option>
<%}
}%>
</select></dd>

<dt>出発駅：</dt>
<dd>
<%
if(from_st!=null){
%>
<input type="text" name="from_st" value="<%=from_st%>">
<%
}else{
%>
<input type="text" name="from_st">
<%
}
%>


― 到着駅：

<%
if(to_st!=null){
%>
<input type="text" name="to_st" value="<%=to_st%>">
<%
}else{
%>
<input type="text" name="to_st">
<%
}
%>
</dd>

<dt>金額：</dt>
<dd>
<%
if(price!=null){
%><input type="text" name="price" value="<%=price%>">
<%
}else{
%>
<input type="text" name="price">
<%
}
%>
</dd>


<dt>&nbsp;</dt>
<dd><%if(errmsg != null){ %>
<%=errmsg%>
<%}else{%>
&nbsp;
<%
}
%>
</dd>

<dt>&nbsp;</dt>
<dd>
<input class="transitionbt" type="submit" formaction="AddTest" value="確認">
<button class="transitionbt" type="submit" formaction="List">戻る</button>
</dd>
</dl>
</form>
</body>
</html>