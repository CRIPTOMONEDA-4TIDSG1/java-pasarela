<%@page import="java.util.List"%>
<%@page import="EntityBussines.OrderEN"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" type="text/css" href="css/Order.css">
     <script>
        function onProductChange(){
            const url = "/CryptoWeb/ProductController"
            window.location.href= url;
        }
    </script>
</head>
    <body>
           <h1>ORDER CRYPTOCURRENCIES </h1>
           <button onclick="onProductChange()"> Back...</button>
        <table>
            <tr>
                <th>ID</th>
                <th>Fecha de Orden</th>
                <th>Email</th>
                <th>ID del Producto</th>
                <th>Cantidad</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
            <% List<OrderEN> orders = (List<OrderEN>) request.getAttribute("Orders");
               for (OrderEN order : orders) { %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getDateOrder() %></td>
                    <td><%= order.getEmail() %></td>
                    <td><%= order.getProductId() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= order.getTotal() %></td>
                    <td><a href="ODetailsController?orderId=<%= order.getId() %>">Ver Detalles</a></td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
