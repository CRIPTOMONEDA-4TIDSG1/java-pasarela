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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        Stripe.apiKey = "sk_test_51NT6LPHZVMcvwy3PLsN0auZyZoQuH8XcZ7LZUuApoPoQOCDj8iHTC84SydMpX46Atg2OY3AkPVhbuhfG1QQgOeYP00dBebTfYw"; // Tu clave secreta de Stripe
        
        // Recupera los parámetros de la URL
        String productName = request.getParameter("productName");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        BigDecimal total = new BigDecimal(request.getParameter("total"));
        int id = Integer.parseInt(request.getParameter("Id"));

        // Calcula la cantidad
        int amount = total.intValue() / price.intValue();

        // Obtiene la fecha actual
        Date now = new Date();

        OrderEN order;
        order = new OrderEN(now,"kalet.elsalva.com",id,total.intValue(),amount);
        new OrderDAL().create(order);

        // Agrega la orden (sustituye esta línea con la lógica real)
        // _orderBL.AddOrder(order);

        // Configura la URL de éxito y cancelación
        String domain = "https://paymentgateway.somee.com/";
        String successUrl = domain + "checkout/order-confirmation.jsp";
        String cancelUrl = domain + "checkout/login.jsp";

        // Construye los objetos de línea de sesión
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productName)
                        .build();

        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("usd")
                        .setUnitAmount(price.multiply(BigDecimal.valueOf(100)).longValue())
                        .setProductData(productData)
                        .build();

        SessionCreateParams.LineItem lineItem =
                SessionCreateParams.LineItem.builder()
                        .setPriceData(priceData)
                        .setQuantity((long) amount)
                        .build();

        // Configura la sesión
        SessionCreateParams createParams = SessionCreateParams.builder()
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .setLineItems(Collections.singletonList(lineItem))
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .build();

        // Crea la sesión
        Session session = null;
        try {
            
            session = Session.create(createParams);
        } catch (StripeException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Almacena la sesión en la solicitud
        request.setAttribute("sessionId", session.getId());

        // Redirige a la página de checkout
        request.getRequestDispatcher("/Checkout.jsp").forward(request, response);
    }
}


