<%-- 
    Document   : OrderDetails
    Created on : 09-01-2023, 07:25:54 PM
    Author     : themi
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/layout/header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de la Orden</title>
    <link rel="stylesheet" type="text/css" href="css/Detalle.css">
   
</head>
<body>
    <h1>Detalles de la Orden</h1>
    
    <table>
        <tr>
            <th>ID de la Orden:</th>
            <td>${OrderDetails.id}</td>
        </tr>
        <tr>
            <th>Fecha de la Orden:</th>
            <td>${OrderDetails.dateOrder}</td>
        </tr>
        <tr>
            <th>Email del Cliente:</th>
            <td>${OrderDetails.email}</td>
        </tr>
        <tr>
            <th>ID del Producto:</th>
            <td>${OrderDetails.productId}</td>
        </tr>
        <tr>
            <th>Cantidad:</th>
            <td>${OrderDetails.quantity}</td>
        </tr>
        <tr>
            <th>Total:</th>
            <td>${OrderDetails.total}</td>
        </tr>
    </table>

    <a href="/CryptoWeb/OrderDetailsController">Volver a la Lista de Órdenes</a>
</body>
</html>
