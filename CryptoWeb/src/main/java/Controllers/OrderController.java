package Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import AccessData.OrderDAL;
import EntityBussines.OrderEN;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kalet
 */
@WebServlet(name="OrderController", urlPatterns= {"/OrderController"})
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera los parámetros de la URL
        String productName = request.getParameter("productName");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        BigDecimal total = new BigDecimal(request.getParameter("total"));
        int id = Integer.parseInt(request.getParameter("Id"));
        
        int amount = total.intValue() / price.intValue();
        
        Date currentDate = Date.valueOf(LocalDate.now());

   
        OrderEN order = new OrderEN(0,currentDate,"kalet.elsalva.com",id,total.intValue(),amount);
        new OrderDAL().create(order);

         
        String domain = "https://localhost:8080/"; // Cambia la URL según corresponda
        
        Stripe.apiKey = "sk_test_51NT6LPHZVMcvwy3PLsN0auZyZoQuH8XcZ7LZUuApoPoQOCDj8iHTC84SydMpX46Atg2OY3AkPVhbuhfG1QQgOeYP00dBebTfYw"; // Tu clave secreta de Stripe
      
        SessionCreateParams.Builder builder = new SessionCreateParams.Builder()
                .setSuccessUrl(domain + "checkout/order-confirmation.jsp")
                .setCancelUrl(domain + "checkout/login.jsp")
                .setMode(SessionCreateParams.Mode.PAYMENT);


        SessionCreateParams.LineItem.PriceData.ProductData.Builder productDataBuilder =
            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(productName);

                SessionCreateParams.LineItem.PriceData.Builder priceDataBuilder =
                    SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("usd")
                        .setUnitAmount((long) (price.intValue() * 100))
                        .setProductData(productDataBuilder.build());

               SessionCreateParams.LineItem.Builder lineItemBuilder =
               SessionCreateParams.LineItem.builder()
                    .setPriceData(priceDataBuilder.build())
                    .setQuantity((long) amount);
               
                builder.addLineItem(lineItemBuilder.build());
                
         try {
            SessionCreateParams createParams = builder.build();
            Session session = Session.create(createParams);

            request.setAttribute("sessionId", session.getId());
            request.getRequestDispatcher("/Checkout.jsp").forward(request, response);
        }catch (StripeException ex) {
            // Maneja la excepción, por ejemplo, muestra un mensaje de error
            ex.printStackTrace();
        }
    }   
}  
