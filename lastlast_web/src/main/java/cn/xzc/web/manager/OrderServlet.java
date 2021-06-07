package cn.xzc.web.manager;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Order;
import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet(name = "OrderServlet1", urlPatterns = { "/manager/OrderServlet1" })
public class OrderServlet extends HttpServlet {
	
	private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","请先登录！");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		if ("get".equals(method)) {
			get(request, response);
		}
		if ("find".equals(method)) {
			find(request, response);
		}
		if ("update".equals(method)) {
			update(request, response);
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			service.updatOrder(id, true);
			saveOp(request,response,"updateGoodStatus");
			request.setAttribute("message", "订单已置为发货，请及时发货<meta http-equiv='refresh' content='1;url="+request.getContextPath()+"/SendEmail'");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "出错！！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user=(User)request.getSession().getAttribute("user");
		if(!"root".equals(user.getUsername())){
			
			Order order = service.findOrder(id,user.getId());
			request.setAttribute("order", order);
			request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
			return;
		}
		Order order = service.findOrder(id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
	}
	

	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		List<Order> list = service.getOrderByStatus(status);
		request.setAttribute("list", list);
		saveOp(request,response,"getManagerOrder");
		request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> list1 = service.getOrderByStatus(true);
		List<Order> list2 = service.getOrderByStatus(false);
		list1.addAll(list2);
		request.setAttribute("list", list1);
		saveOp(request,response,"getAllOrder");
		request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}

	
	private void saveOp(HttpServletRequest request, HttpServletResponse response,String op) throws ServletException, IOException{
		String ipAddr=getIpAddr(request);
		User user=(User)request.getSession().getAttribute("user");
		String name=user.getUsername();
		String permission=user.getPermission();
		service.saveOp(ipAddr,name,permission,op);
		
	}
	public String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	      if(ip ==null || ip.length() ==0 ||"unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("Proxy-Client-IP");      
	      }      
	      if(ip ==null || ip.length() ==0 ||"unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	     if(ip ==null || ip.length() ==0 ||"unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	      }      
	     return ip;      
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

