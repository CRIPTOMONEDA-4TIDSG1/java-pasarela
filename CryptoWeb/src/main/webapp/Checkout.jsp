<%-- 
    Document   : Checkout
    Created on : 31 ago. 2023, 01:09:42
    Author     : kalet
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
</head>
<body>
    <h1>Checkout</h1>
    
    <!-- Redirige al usuario a la página de pago de Stripe -->
    <script src="https://js.stripe.com/v3/"></script>
    <script>
        var sessionId = '<%= request.getAttribute("sessionId") %>';
        var stripe = Stripe('pk_test_51NT6LPHZVMcvwy3P0JudceGQ9w5JhBAdk7jiDCs8HhnZhzchB8fCH50DwdKK4f8O9ZkFMEF96Mtjm2W4v8DXSbzJ00CMTiBzL4');
        stripe.redirectToCheckout({
            sessionId: sessionId
        });
    </script>
</body>
</html>

