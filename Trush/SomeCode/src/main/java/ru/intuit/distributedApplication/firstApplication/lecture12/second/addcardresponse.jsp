<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="ru.intuit.distributedApplication.firstApplication.lecture12.second.BillingService,
	ru.intuit.distributedApplication.firstApplication.lecture12.second.Billing,
	ru.intuit.distributedApplication.firstApplication.lecture12.second.Card"%>
<%
	Card card = new Card();
	try {
		Billing billing = new BillingService().getBillingPort();
		String cardnumber = request.getParameter("cardnumber");
		String person = request.getParameter("person");
		card.setCard(cardnumber);
		card.setPerson(person);
		List<Card> list = new ArrayList<>();
		list.add(card);
		billing.addNewCard(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<h2>
	<font color="black">
		<%=card.getPerson() + "\t" + card.getBalance() %>
	</font>
</h2>