<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>

<%
	//request의 attribute 영역의 리스트를 가져온다 **dao에서 가져오는거 아님
	//어트리뷰트 받을때 형변환 해줘야함(오브젝트 형이라)
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>[phonebook2]</h1>
	
	<h2>전화번호 리스트</h2>
	
	<p>입력한 정보 내역입니다.</p>
	
	<%
	for(int i=0; i<personList.size(); i++) {
	%>
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td><%=personList.get(i).getName()%></td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td><%=personList.get(i).getHp()%></td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td><%=personList.get(i).getCompany()%></td>
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=updateForm&id=<%=personList.get(i).getPerson_id()%>">[수정]</a></td>
				<td><a href="/phonebook2/pbc?action=delete&id=<%=personList.get(i).getPerson_id()%>">[삭제]</a></td>
			</tr>
		</table>
		<br>
	<%
	}
	%>
	
	<a href="/phonebook2/pbc?action=writeForm">등록</a>
	
	

</body>
</html>