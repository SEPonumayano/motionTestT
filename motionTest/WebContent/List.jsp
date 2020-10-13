<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ResultSet rs = (ResultSet) request.getAttribute("rs");
	%>
	<%
		//String nowPage=(String)request.getAttribute("nowPage");
	%>
	<%
		//String now=(String)request.getAttribute("page");
		//int np= Integer.parseInt(nowPage);
		//int np= 1;
	%>
	<%
		String listCnt = (String) request.getAttribute("listCnt");
		//int listC=Integer.parseInt("listCnt");
		//int listC=1;
	%>
	<%!//DB取得用変数
	int maxPage = 0;%>
	<%
		//maxPage=listC/10;
	%>

	<%
		//if(listC%10>0){
		//maxPage=maxPage+1;
		//}
	%>


	<h1>交通費登録システム:一覧</h1>
	<form action="AddTest" method="GET">
		<input type="submit" value="新規登録">
	</form>
	<form action="Excel" method="post">
		<input type="submit" value="Excelへ出力">
	</form>

	<%
		rs = (ResultSet) request.getAttribute("rs");
	%>
	<form>

		<table border="1">
			<tr>
				<td>No</td>
				<td>日付</td>
				<td>片道or往復</td>
				<td>交通機関</td>
				<td>出発駅</td>
				<td>到着駅</td>
				<td>金額</td>
				<td></td>
			</tr>


			<%
				while (rs.next()) {
			%>


			<form method="get" name="<%=rs.getInt("id")%>">
				<tr>
					<td><%=rs.getString("id")%><input type="hidden" name="id"
						value="<%=rs.getString("id")%>"></td>
					<td><%=rs.getString("day")%><input type="hidden" name="day"
						value="<%=rs.getString("day")%>"></td>
					<td><%=rs.getString("route_name")%><input type="hidden"
						name="route_name" value="<%=rs.getString("route_name")%>"></td>
					<td><%=rs.getString("transit_name")%><input type="hidden"
						name="transit_name" value="<%=rs.getString("transit_name")%>"></td>
					<td><%=rs.getString("from_st")%><input type="hidden"
						name="from_st" value="<%=rs.getString("from_st")%>"></td>
					<td><%=rs.getString("to_st")%><input type="hidden"
						name="to_st" value="<%=rs.getString("to_st")%>"></td>
					<td><%=rs.getString("price")%><input type="hidden"
						name="price" value="<%=rs.getString("price")%>"></td>
					<td><input type="submit" value="編集" formaction="./.jsp"> <input
						type="submit" value="削除" formaction="./.jsp"></td>
				</tr>
			</form>
			<%
				}
			%>


		</table>
	</form>

	<form action="AddTest" method="GET">
		<input type="submit" value="新規登録">
	</form>


</body>
</html>