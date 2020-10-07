<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%
//ページング用（とりあえず住所録のそのまま）
//String listCnt =(String) request.getAttribute("listCnt");
//String nowPage =(String) request.getAttribute("page");
//String SerchName =(String) request.getAttribute("SerchName");
//int listC= Integer.parseInt(listCnt);
//int now= Integer.parseInt(nowPage);

//int maxPage=listC/10;

//if(maxPage%10 !=0){
//	maxPage=maxPage+1;
//}

//if(SerchName==null){
//	SerchName="";
//}

String transit_no=(String)request.getAttribute("transit_no");
String from_st=(String)request.getAttribute("from_st");
String to_st=(String)request.getAttribute("to_st");

//DBから「transit_data」を取得する用
ResultSet rs= (ResultSet) request.getAttribute("rs");


//登録か編集かの判断値（名前は仮仕様）
String menulist=(String)request.getAttribute("menulist");
int menuNo= Integer.parseInt(menulist);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>交通費登録システム：交通手段</p>

<!-- ここにページング -->

<!-- 交通手段一覧表示 -->
<form method="post">
<table border="1">
<tr>
<th>No</th>
<th>交通機関</th>
<th>出発駅</th>
<th>到着駅</th>
<th>金額</th>
<th></th>
</tr>

<%
while(rs.next()){
%>

<tr>
<!-- 交通し手段の値を表示 -->
<td><%=rs.getString("data_id") %></td>
<td><%=rs.getString("transit_name") %></td>
<td><%=rs.getString("from_st") %></td>
<td><%=rs.getString("to_st") %></td>
<td><%=rs.getString("price") %></td>

<!-- 選択した値を渡す用 -->
<input name="transit_no" type="hidden" value=<%=rs.getString("transit_name") %>>
<input name="from_st" type="hidden" value=<%=rs.getString("from_st") %>>
<input name="to_st" type="hidden" value=<%=rs.getString("to_st") %>>
<input name="price" type="hidden" value=<%=rs.getString("price") %>>
<%
if(menuNo==1){
%>
<!-- 登録画面へ戻る -->
<input type="hidden" name="menulist" value="1">
<td><button type="submit" formaction="add.jsp">選択</button></td>

<%}else{ %>
<!-- 編集画面へ戻る -->
<input type="hidden" name="menulist" value="2">
<td><button type="submit" formaction="edit.jsp">選択</button></td>

<%
}
%>
</tr>
<%
}
%>
</table>
</form>

<!-- ここにページング -->

<!-- 戻るボタン表示 -->
<form method="post">
<%
if(menuNo==1){
%>
<!-- 登録画面へ戻る -->
<input type="hidden" name="menulist" value="1">
<button type="submit" formaction="add.jsp" >戻る</button>
<%
}else{
%>
<!-- 編集画面へ戻る -->
<input type="hidden" name="menulist" value="2">
<button type="submit" formaction="edit.jsp" >戻る</button>
<%
}
%>
</form>
</body>
</html>