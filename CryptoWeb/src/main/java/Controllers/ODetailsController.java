
package Controllers;

import AccessData.OrderDAL;
import EntityBussines.OrderEN;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author themi
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/OrderDetailsController","/Order/*"})
public class ODetailsController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         String url = request.getRequestURI(); 
         String contextPath = request.getContextPath(); 

         String relativePath = url.substring(contextPath.length());

        if (relativePath.startsWith("/Order/") ) {
            
            String idStr = relativePath.substring("/Order/".length());
            int orderId = Integer.parseInt(idStr);
            
            OrderEN order = new OrderDAL().searchById(orderId);
            request.setAttribute("OrderDetails", order);
            request.getRequestDispatcher("/Views/OrderDetails.jsp").forward(request, response);
        } else {
            List<OrderEN> Orders = new OrderDAL().getAllOrders();
            request.setAttribute("Orders", Orders);
            request.getRequestDispatcher("/Views/Order.jsp").forward(request, response);
       
          }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Manejo de las solicitudes POST si es necesario
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}