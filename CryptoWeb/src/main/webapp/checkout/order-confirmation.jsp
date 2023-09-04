<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transacción Cancelada</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.18.0/font/bootstrap-icons.css">
    <style>
        /* Clase personalizada para el botón */
        .custom-button {
            background-color: #ffc107; /* Color de fondo amarillo */
            color: #333; /* Color del texto */
            font-weight: bold; /* Negrita */
            border: none; /* Sin borde */
            padding: 10px 20px; /* Espaciado interno */
            border-radius: 5px; /* Bordes redondeados */
        }
    </style>
</head>
<body style="background-color: #f7f7f7;">
    <div class="container mt-5">
        <div class="card mx-auto" style="max-width: 400px; background-color: #f7f7f7; border-radius: 10px; box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);">
            <div class="card-body">
                <div class="container text-center">
                    <h2 style="font-size: 2.5rem; color: black;">The request has been successful!</h2>
                     <img src="${pageContext.request.contextPath}/img/Check.png" alt="Éxito" width="150" style="margin-top: 20px;">
                    <h3 style="color: black; margin-top: 20px; font-size: 1.2rem;">
                        Thanks for your purchase. Please come back soon!!!
                    </h3>
                    <br/>
                    <!-- Aplicando la clase de Bootstrap "btn btn-warning" y la clase personalizada "custom-button" -->
                    <a href="#" onclick="onProductChange()" class="btn btn-warning custom-button">Home</a>
                    <script>
                        function onProductChange() {
                            const url = "/CryptoWeb/ProductController";
                            window.location.href = url;
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
