<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <style> *{vertical-align: top;}</style>
</head>
<body>
    <a href="/questions">index</a><br>
    <a href="/questions/1">questions/1</a><br>
    <a href="/questions/new">questions/new</a><br>
    <h1>What is your question?</h1>
    <form:form action="/questions/new" method="post" modelAttribute="q">
        <form:errors path="question"></form:errors><br>
        <form:label path="question">Question:</form:label>
        <form:textarea rows="8" cols="50" path="question"></form:textarea><br>
        
        <br>
        <c:out value="${tag_error}"></c:out>
        <label for="tags">Tags :</label>
        <input name="tags"></input>
        <form:button type ="submit"> Create</form:button>
    </form:form>
</body>
</html>