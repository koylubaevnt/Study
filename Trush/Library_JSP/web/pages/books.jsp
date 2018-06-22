<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.koylubaevnt.library.beans.Book"%>
<%@page import="com.koylubaevnt.library.enums.SearchType"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../WEB-INF/jspf/left_menu.jspf" %>
<%@include file="../WEB-INF/jspf/letters.jspf" %>
<jsp:useBean id="bookList" class="com.koylubaevnt.library.beans.BookList" scope="page"/>

<div class="book_list">

<%  
    List<Book> list = new ArrayList<Book>();
    if(request.getParameter("genre_id") != null) {
        long genreId = Long.valueOf(request.getParameter("genre_id"));
        list = bookList.getBooksByGenre(genreId);
    } else if(request.getParameter("letter") != null) {
        String letter = request.getParameter("letter");
        list = bookList.getBooksByLetter(letter);
    } else if(request.getParameter("search_string") != null) {
        String searchStr = request.getParameter("search_string");
        SearchType searchType = SearchType.TITLE;
        if(request.getParameter("search_option").equals("Автор")) {
            searchType = SearchType.AUTHOR;
        }
        if (searchStr != null && !searchStr.trim().equals("")) {
            list = bookList.getBooksBySearch(searchStr, searchType);
        }   
    } else if(session.getAttribute("currentBookList") != null) {
        list = (List<Book>) session.getAttribute("currentBookList");
    } else {
        list = bookList.getAllBooks();
    }
%>


    <h5 style="text-align: left; margin-top: 20px;">Найдено книг: <%=list.size()%></h5>
        <%
            session.setAttribute("currentBookList", list);
            for (Book book : list) {
                
        %>
        <div class="book_info">
            <div class="book_title">
                <p>
                    <a href="content.jsp?index=<%=list.indexOf(book)%>">
                        <%=book.getName()%>
                    </a>
                </p>
            </div>
            <div class="book_image">
                <a href="content.jsp?index=<%=list.indexOf(book)%>">
                    <img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book) %>" height="250" width="190" alt="обложка книги"/>
                </a>
            </div>
            <div class="book_details">
                <br/><strong>ISBN:</strong> <%=book.getIsbn()%>
                <br/><strong>Издательство:</strong> <%=book.getPublisher()%>
                <br/><strong>Количество страниц:</strong> <%=book.getPageCount()%>
                <br/><strong>Год издания:</strong> <%=book.getPublishYear()%>
                <br/><strong>Автор:</strong> <%=book.getAuthor()%>
                <p style="margin: 10px;"><a href="content.jsp?index=<%=list.indexOf(book)%>">Читать</a></p>
            </div>
        </div>
        <%}%>
</div>