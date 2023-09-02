
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
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/OrderDetailsController"})
public class ODetailsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");

        if (action == null) {
            // Si no se especifica ninguna acción, mostrar la lista de todas las órdenes
            List<OrderEN> Orders = new OrderDAL().getAllOrders();
            request.setAttribute("Orders", Orders);
            request.getRequestDispatcher("/Views/Order.jsp").forward(request, response);
        } else if (action.equals("view")) {
            // Si la acción es "view", se solicita ver los detalles de una orden específica
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderEN order = new OrderDAL().searchById(orderId);
            request.setAttribute("OrderDetails", order);
            request.getRequestDispatcher("/Views/OrderDetails.jsp").forward(request, response);
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