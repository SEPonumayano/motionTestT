<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>交通費登録システム：ログイン</h1>
    <form action="Login" method="post">
    <table>
      <tr>
        <th>メールアドレス:</th>
        <td><input type="text" name="address"></td>
      </tr>
      <tr>
        <th>パスワード:</th>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
      	<th></th>
      	<td><input type="submit" class="btn" value="ログイン"></td>
      </tr>
     </table>
  	</form>
  	<p>原
add harashinya1192
pass hara1192</p>
<% String getErr=(String)request.getAttribute("message"); %>
<%if(getErr!=null){ %>
<%=getErr%>
<%}%>

</body>
</html>