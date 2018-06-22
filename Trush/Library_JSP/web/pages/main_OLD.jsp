<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.koylubaevnt.library.beans.AuthorList"%>
<%@page import="com.koylubaevnt.library.beans.Author"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн библиотека</title>
        <link href="../css/style_main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <div class="header">
                <div class="logo">
                    <img src="../images/library.png" alt="Логотип" name="logo"/>
                </div>
                <div class="descr">
                    <h3>Онлайн библиотека проекта</h3>
                </div>
                <form class="search_form" name="search_form" method="POST">
                    <!--<img src="../images/search.jpg"/>-->
                    <input type="text" name="search_string" value="" size="100" />
                    <input class="search_button" type="submit" value="Поиск" name="search_button" />
                    <select name="search_option">
                        <option>Название</option>
                        <option>Автор</option>
                    </select>
                </form>
            </div>
            
            <div class="slidebar1">
                <h4>Список авторов:</h4>
                <ul class="nav">
                    <%--
                    <% AuthorList authorList = new AuthorList();
                        for(Author author : authorList.getAuthorList()) {
                    %>
                        <li><a href="#"><%=author.getName()%></a></li>
                    <%}%>
                    --%>
                    <jsp:useBean id="authorList" class="com.koylubaevnt.library.beans.AuthorList" scope="session"/>
                    <c:forEach var="author" items="${authorList.getAuthorList()}">
                        <li><a href="#">${author.name}</a></li>
                    </c:forEach>
                        
                </ul>
                <p>&nbsp;</p>
            </div>
                
            <div class="content">
                <h1>&nbsp;</h1>
                <p>&nbsp;</p>
            </div>
                
        </div>
    </body>
</html>
