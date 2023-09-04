<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="EntityBussines.ProductEN" %>
<jsp:include page="/layout/header.jsp" />
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cripto</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="css/Order.css">
</head>

<body>
    <div class="container mt-4">
        <h1 class="display-4 text-center mb-4">CRYPTOCURRENCIES</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">TITULO</th>
                    <th class="text-center">DESCRIPCIÓN</th>
                    <th class="text-center">STOCK</th>
                    <th class="text-center">PRECIO</th>
                    <th class="text-center">ACCIONES</th>
                </tr>
            </thead>
            <tbody>
                <% List<ProductEN> products = (List<ProductEN>) request.getAttribute("products");
                   for (ProductEN product : products) { %>
                    <tr>
                        <td><%= product.getCryptoName() %></td>
                        <td><%= product.getDescriptionCrypto() %></td>
                        <td><%= product.getAmount() %></td>
                        <td>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">$</span>
                                </div>
                                <input type="number" class="form-control price-input"
                                    data-cryptoname="<%= product.getCryptoName() %>"
                                    data-exchangerate="<%= product.getPrice() %>"
                                    oninput="convertToCrypto(this)">
                            </div>
                        </td>
                        <td>
                            <div class="input-group">
                                <input type="text" readonly class="form-control amount-output" style="width: 80px;">
                                <span class="input-group-append">
                                    <span class="input-group-text crypto-name"></span>
                                </span>
                            </div>
                            <button type="button" class="btn btn-primary mt-2"
                                onclick="redirectToCheckOut('<%= product.getId() %>', '<%= product.getPrice() %>', '<%= product.getCryptoName() %>')">Comprar</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <!-- Add JavaScript -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
    <script>
       function convertToCrypto(input) {
        const dollarAmount = parseFloat(input.value);
        const exchangeRate = parseFloat(input.dataset.exchangerate);
        const output = input.parentElement.nextElementSibling.querySelector('.amount-output');
        const cryptoNameSpan = input.parentElement.nextElementSibling.querySelector('.crypto-name');
        if (!isNaN(dollarAmount) && dollarAmount > 0) {
            const cryptoAmount = dollarAmount * exchangeRate;
            output.value = cryptoAmount.toFixed(2);
            cryptoNameSpan.innerText = input.dataset.cryptoname.toUpperCase();
        } else {
            output.value = '';
            cryptoNameSpan.innerText = '';
        }
    }

    function convertAllToCrypto() {
        const priceInputs = document.getElementsByClassName('price-input');
        for (const input of priceInputs) {
            convertToCrypto(input);
        }
    }

    function redirectToCheckOut(id, price, productName) {
        const amountOutput = event.target.parentElement.parentElement.querySelector('.amount-output');
        const total = amountOutput.value;
        const Price = parseInt(price).toFixed(2);
        
        const url = "/CryptoWeb/OrderController?Id=" + id + "&total=" + total + "&price=" + Price + "&productName=" + productName;  
        window.location.href = url;
    }
    </script>
</body>

</html>
