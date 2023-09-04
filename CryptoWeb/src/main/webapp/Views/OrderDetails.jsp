<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de la Orden</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.18.0/font/bootstrap-icons.css">

</head>
<body>
    <div class="container mt-4">
        <div class="card mx-auto" style="max-width: 400px;">
            <div class="card-header" style="background-color: #384B6D; border-bottom: 1px solid #e0e0e0; position: relative;">
                <a href="/CryptoWeb/OrderDetailsController" class="btn btn-danger btn-sm" style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%);">X</a>
                <h3 class="text-center" style="color: gold;">PURCHASE DETAIL</h3>
            </div>
            <div class="card-body" style="background-color: #f7f7f7;">
                <table class="table table-bordered">
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
            </div>
            <div class="card-footer" style="background-color: #384B6D;"></div>
        </div>
    </div>
</body>
</html>
