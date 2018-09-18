<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <style>
        .tag
        	{border:1px inset black;
            display: inline-block;
            padding: 8px;}
        h2
        	{display:inline-block;}
        label,.err
	        {vertical-align:top;}
	    table
	    	{border: 1px solid darkgray;}
		td
		    {padding:5px;} 
		.odd
			{background-color: #F8F8F8;}
		.even
			{background-color:	#D0D0D0;}

    </style>
</head>
<body>
    <a href="/questions">index</a><br>
    <a href="/questions/1">questions/1</a><br>
    <a href="/questions/new">questions/new</a><br>
    <c:if test="${question.getId()!=null}" >
            <h1> ${question.getQuestion()} Q_ID : ${question.getId()} </h1>
    </c:if>
    <c:if test="${question.getId()==null}">
        <h1> This page does not exist broski ¯\_(ツ)_/¯</h1>        
    </c:if>

    <c:if test="${question.getId()!=null}" >
    <h2>Tags : </h2>
        <c:forEach var = "tag" items="${question.getTag()}">
           <div class="tag"> ${tag.getSubject()}</div>     
        </c:forEach>
    </c:if>

    <c:if test="${question.getId()!=null && question.getAnswers().size()>0}" >
    	<table>
    	<tr>
    		<th>Answers</th>
    	</tr>
    	<tbody>
    	<c:forEach items="${question.getAnswers()}" var ="answer" varStatus="counter">
	    	<tr class="${counter.index % 2 == 0  ? 'even':'odd'}">
				<td >${answer.getAnswer()}</td>    
	    	<tr>
    	</c:forEach>   	
    	</tbody>
    	</table>
    </c:if>
    
    
    <c:if test="${question.getId()!=null}" >
       <h1> Answer the question!</h1>
		 <form:form action="/questions/${question.getId()}" method="post" modelAttribute="ans">
			 <form:label path="answer">Answer</form:label>
			 <form:errors class="err" path="answer"></form:errors><br>
			 <form:textarea cols="50" rows="5" path="answer"></form:textarea><br>
			 <button type ="submit">Submit</button>
		 </form:form>    
	 </c:if>
    </body>
</html>