<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div style="margin: 30px;">
            <a href="books.jsp"><< вернуться</a>
        </div>
        <div class="pdf_viewer">
            <applet code="EmbedPDF.class" archive="<%=getServletContext().getContextPath()%>/jars/EmbedPDF.jar" width="850" height="900">
                <!-- The URL of the PDF document that we want to show: -->
                <param name="pdf" value="<%= request.getContextPath()%>/PdfContent?index=<%=request.getParameter("index")%>&session_id=<%=request.getSession().getId()%>"/> 

                <!-- The location of the font files: -->
                <!--<param name="fonts" value="fontsseparate"/> -->

                <!-- Whether users may open the PDF document in a new window: -->
                <param name="enableOpenWindow" value="true"/>

                <!-- Whether the PDF is rendered with subpixel-antialiasing (may be slow and needs more memory) -->
                <param name="enableSubpixAA" value="true"/>

                <!-- Whether to display the "print" button. -->
                <!--<param name="enablePrinting" value="true"/>-->


            <!-- The following parameters are recommended to improve usability and
                 performance of the applet when run with Sun's Java Plugin: -->

                <!-- whether language-specific texts shall be looked up on the server. -->
                <param name="codebase_lookup" value="false"/>

                <!-- whether the code of the applet shall be shared with other applets. -->
                <param name="classloader_cache" value="false"/>

                <!-- Whether the server provides a highly compressed .pack.gz-version of the applet.
                     The amount of memory that the applet may use (128m is 128 mega bytes).
                -->
                <param name="java_arguments" value="-Djnlp.packEnabled=true -Xmx128m"/>

                <!-- the splash screen to show, while the applet loads. -->
                <param name="image" value="<%=getServletContext().getContextPath()%>/images/splash.gif"/>

                <!-- the border of the splash screen. -->
                <param name="boxborder" value="false"/>

                <!-- whether the splash screen shall be centered. -->
                <param name="centerimage" value="true"/>

            <!-- The following parameters are required for Applet security: -->
                <!--<param name="permissions" value="sandbox"/>-->

            <!-- If the browser does not supprt java, we just provide a link to the PDF-document. -->
                <!--<a href="example.pdf">example.pdf</a>-->
          </applet>
        </div>