<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
    <style>
     th, td {
	    border: 1px solid darkgray;
	    padding:5px;
    }
    .newquestion{
        margin-top: 2em;
        margin-left: 15em;
    }
    th{background-color:#A0A0A0;}
	.odd
		{background-color: #F8F8F8;}
	.even
		{background-color:	#D8D8D8;}
    
    </style>
</head>
<body>
<a href="/questions">index</a><br>
<a href="/questions/1">questions/1</a><br>
<a href="/questions/new">questions/new</a><br>
<h1>This is Index</h1>

 
<table>
    <tr>
        <th>Question</th>
        <th>Tags</th>
    </tr>
    <tbody>
    <c:forEach items="${questions}" var= "question" varStatus="counter">
        <tr class="${counter.index % 2 == 0  ? 'even':'odd'}">
            <td><a href="/questions/${question.getId()}">${question.getQuestion()}</a></td>
            <td >
                <c:forEach items="${question.getTag()}" var ="tag">
                    ${tag.getSubject()} <c:if test ="${question.getTag().indexOf(tag)<question.getTag().size()-1}">,</c:if>
                </c:forEach>                                    
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>

<div class="newquestion">
    <a type ="button" href="/questions/new">questions/new</a><br>
</div>

</body>
</html>