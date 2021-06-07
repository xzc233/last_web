package cn.xzc.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Cart;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

//ɾ��ָ���Ĺ�����
@WebServlet("/client/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		BusinessService service = new BusinessServiceImpl();
		service.deleteCartItem(id, cart);
		
		//ɾ���ɹ���������ת��listcart.jspҳ��
		response.sendRedirect(request.getContextPath() + "/client/listcart.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
