<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="motiontest.*" import="java.sql.*"%>
    <%
	String id = (String) request.getAttribute("id");
	String day = (String) request.getAttribute("day");
	String route_no = (String) request.getAttribute("route_no");
	String transit_no = (String) request.getAttribute("transit_no");
	String from_st = (String) request.getAttribute("from_st");
	String to_st = (String) request.getAttribute("to_st");
	String price = (String) request.getAttribute("price");

	String route_name = CommonDB.getRouteName(route_no);
	String transit_name = CommonDB.getTransitName(transit_no);
	CommonUpdData data = (CommonUpdData) request.getAttribute("data");
	request.getSession().setAttribute("upddata", data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>交通費登録システム：編集画面</h2>
	<form action="./EditCheck">
		<table>
			<tr>
				<th>日付</th>
				<th>:</th>
				<td><%=day%></td>
			</tr>
			<tr>
				<th>片道or往復</th>
				<th>:</th>
				<td><%=route_name%></td>
			</tr>
			<tr>
				<th>交通機関</th>
				<th>:</th>
				<td><%=transit_name%></td>
			</tr>
			<tr>
				<th>出発駅</th>
				<th>:</th>
				<td><%=from_st%></td>
				<th>到着駅</th>
				<th>:</th>
				<td><%=to_st%></td>
			</tr>
			<tr>
				<th>運賃</th>
				<th>:</th>
				<td><%=price%>円</td>
			</tr>
		</table>
		<div>
			<input type="hidden" name="id" value="<%=id%>"> <input
				type="hidden" name="day" value="<%=day%>"> <input
				type="hidden" name="route_name" value="<%=route_name%>"> <input
				type="hidden" name="transit_name" value="<%=transit_name%>">
			<input type="hidden" name="from_st" value="<%=from_st%>"> <input
				type="hidden" name="to_st" value="<%=to_st%>"> <input
				type="hidden" name="price" value="<%=price%>">
		</div>
		<div>
			<input type="submit" value="編集">
		</div>

	</form>

	<form action="./edit.jsp">
		<div>
			<input type="hidden" name="id" value="<%=id%>"> <input
				type="hidden" name="day" value="<%=day%>"> <input
				type="hidden" name="route_name" value="<%=route_name%>"> <input
				type="hidden" name="transit_name" value="<%=transit_name%>">
			<input type="hidden" name="from_st" value="<%=from_st%>"> <input
				type="hidden" name="to_st" value="<%=to_st%>"> <input
				type="hidden" name="price" value="<%=price%>">
		</div>
		<div>
			<input type="submit" value="戻る">
		</div>
	</form>

</body>
</html>