package cn.xzc.web.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Order;
import cn.xzc.domain.OrderItem;
import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet("/client/OrderListServlet")
public class OrderListServlet extends HttpServlet{
	private BusinessService service = new BusinessServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","ÇëÏÈµÇÂ¼£¡");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		if ("find".equals(method)) {
			find(request, response);
		}
		
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Order> list1 = service.getOrderByStatus(true);
		List<Order> list2 = service.getOrderByStatus(false);
		list1.addAll(list2);
		User user=(User)request.getSession().getAttribute("user");
		Iterator<Order> iterator = list1.iterator();
        while(iterator.hasNext()){
        	Order integer = iterator.next();
            if(!user.getUsername().equals(integer.getUser().getUsername()))
                iterator.remove();   
        }
		request.setAttribute("list", list1);
		request.getRequestDispatcher("/client/listorder.jsp").forward(request, response);
		
	}
	
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Order order = service.findOrder(id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/client/orderdetail.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}
