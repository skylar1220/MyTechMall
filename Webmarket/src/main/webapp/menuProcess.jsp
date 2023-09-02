<%@page import="java.io.PrintWriter"%>
<%@page import="service.CartService"%>
<%
String memberId = (String) session.getAttribute("memberId");

CartService cartService = new CartService();

Integer cartCount = cartService.getCartCount(memberId);
if(cartCount == null) cartCount= 0 ;

PrintWriter writer = response.getWriter();
writer.print(cartCount);

%>