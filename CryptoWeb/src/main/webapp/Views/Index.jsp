<%-- 
    Document   : Index
    Created on : 29 ago. 2023, 21:33:57
    Author     : kalet
--%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="EntityBussines.ProductEN" %>
<jsp:include page="/layout/header.jsp" />
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cripto</title>
    </head>
    <body>
        <h1>CRYPTOCURRENCIES </h1>
    </body>

<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Conversor</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
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

     function redirectToCheckOut( id, price, productName) {
        const amountOutput = event.target.parentElement.parentElement.querySelector('#total-price');
        const total = amountOutput.value;
        const Price = parseInt(price).toFixed(2);
        
        const url = "/CryptoWeb/OrderController?Id="+ id +"&"+"total=" + total +"&price="+ Price +"&productName="+ productName;  
       window.location.href = url;
    }
    </script>
</head>
<body>
    <table>
        <tr>
            <th>TITULO</th>
            <th>DESCRIPCIÓN</th>
            <th>STOCK</th>
            <th>PRECIO</th>
            <th>ACCIONES</th>
        </tr>
        <% List<ProductEN> products = (List<ProductEN>) request.getAttribute("products");
           for (ProductEN product : products) { %>
            <tr>
                <td><%= product.getCryptoName() %></td>
                <td><%= product.getDescriptionCrypto() %></td>
                <td><%= product.getAmount() %></td>
                <td>
                    $<input  type="number" class="price-input"
                           data-cryptoname="<%= product.getCryptoName() %>"
                           data-exchangerate="<%= product.getPrice() %>"
                           oninput="convertToCrypto(this)">
                </td>
                <td>
                    <input type="text" id="total-price" readonly class="amount-output" style="width: 80px;">
                    <span class="crypto-name"></span>
                    <button type="button" onclick="redirectToCheckOut('<%= product.getId() %>', '<%= product.getPrice() %>', '<%= product.getCryptoName() %>')">Comprar</button>
                </td>
            </tr>
        <% } %>
    </table>
</body>

</html>
