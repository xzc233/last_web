package cn.xzc.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Good;
import cn.xzc.domain.Cart;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet("/client/BuyServlet")
public class BuyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BusinessService service = new BusinessServiceImpl();
		Good good = service.findGood(id);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.add(good);
		
		//一定要用重定向技术
		response.sendRedirect(request.getContextPath() + "/client/listcart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
