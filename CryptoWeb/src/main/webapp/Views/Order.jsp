<%@page import="java.util.List"%>
<%@page import="EntityBussines.OrderEN"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Cryptocurrencies</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/Order.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="display-4 text-center mb-4">ORDER CRYPTOCURRENCIES</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">Fecha de Orden</th>
                    <th class="text-center">Email</th>
                    <th class="text-center">ID del Producto</th>
                    <th class="text-center">Cantidad</th>
                    <th class="text-center">Total</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% List<OrderEN> orders = (List<OrderEN>) request.getAttribute("Orders");
                   for (OrderEN order : orders) { %>
                    <tr>
                        <td><%= order.getId() %></td>
                        <td><%= order.getDateOrder() %></td>
                        <td><%= order.getEmail() %></td>
                        <td><%= order.getProductId() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td><%= order.getTotal() %></td>
                        <td><a href="Order/<%= order.getId() %>" class="btn btn-primary">Ver Detalles</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>


</body>
</html>
