<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн библиотека::Вход</title>
        <link href="css/style_index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% if(request.getParameter("session") != null && request.getParameter("session").equals("0")) {
            session.invalidate();
            request.getSession(true);
        } %>

        <div class="main">
            <div class="content">
                <p class="title"><span class="text"><img src="images/lib.png" width="76" height="77" hspace="10" vspace="10" align="middle"/></span></p>
                <p class="title">Онлайн библиотека</p>
                <p class="text">Добро пожаловать в онлайн библиотеку, где вы сможете найти любую книгу на ваш вкус. Доступны функции поиска</p>
                <p class="text">Проект находится в стадии разработки, поэтому дизайн и функционал будет постоянно дорабатываться.</p>
                <p class="text">По всем вопросам обращайтесь по адресу <a href="mailto:support@testlibrary.com">support@testlibrary.com</a></p>
                <p>&nbsp;</p>
            </div>
            <div class="login_div">
                <p class="title">Для входа введите свои данные:</p>
                <form class="login_form" name="login_form" action="pages/main.jsp" method="POST">
                    Имя: <input type="text" name="username" value="" size="20" />
                    <input type="submit" value="Войти" name="btn_enter" />
                </form>
            </div>
            <div class="footer">
                Зарегистрирвоанный товарный знак 2013
            </div>
        </div>
    </body>
</html>
