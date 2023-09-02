<%-- 
    Document   : header
    Created on : 09-02-2023, 10:35:33 AM
    Author     : themi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/header.css">
    <title></title>
</head>
<body>
    <header>
        <h1>PAYMENT GATEWAY</h1>
        <nav>
            <ul>
                <li><a href="#" onclick="onProductChange()">Home</a></li>
               <li><a href="#" onclick="onOrderChange()">Orders</a></li>
            </ul>
        </nav>
    </header>
    <main>
    </main>
       <script>
        function onOrderChange() {
            const url = "/CryptoWeb/OrderDetailsController";
            window.location.href = url;         
        }
                  function onProductChange(){
            const url = "/CryptoWeb/ProductController"
            window.location.href= url;
        }
    </script>

</body>
</html>
