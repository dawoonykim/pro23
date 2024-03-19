<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원검색</title>
</head>
<body>
	<h1>회원검색</h1>

	<!-- 
1. 이름 x, 이메일 x 검색 버튼 누르는 경우
select * from t_member

2. 이름 o, 이메일 x 검색 버튼 누르는 경우 
select * from t_member where name like '홍길동'

3. 이름 x, 이메일 o 검색 버튼 누르는 경우
select * from t_member where email like 'hong@google.com'

4. 이름 o, 이메일 o 검색 버튼 누르는 경우
select * from t_member where name like '홍길동' and email like 'hong@google.com'

 -->
	<form action="http://localhost:8080/pro23/mem4.do?action=searchMember">
		<input type="hidden" name="action" value="searchMember"> 이름 :
		<input type="text" name="name"><br> 이메일 : <input
			type="text" name="email"><br> <input type="submit"
			value="검색"><br>
	</form>

</body>
</html>