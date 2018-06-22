<%@ page import="ru.intuit.distributedApplication.firstApplication.lecture12.second.BillingService,
	ru.intuit.distributedApplication.firstApplication.lecture12.second.Billing,
	ru.intuit.distributedApplication.firstApplication.lecture12.second.Card"%>
<%
	Card resp = null;
	try {
		Billing billing = new BillingService().getBillingPort();
		resp = billing.getCard(request.getParameter("cardnumber"));	
	} catch (Exception e) {
		resp = new Card();
	}
%>
<h2>
	<font color="black">
		<%=resp.getPerson() + "\t" + resp.getBalance() %>
	</font>
</h2>