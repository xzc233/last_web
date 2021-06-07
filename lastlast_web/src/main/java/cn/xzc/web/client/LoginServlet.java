package cn.xzc.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet("/client/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BusinessService service = new BusinessServiceImpl();
		User user = service.findUser(username, password);
		if (user == null) {
			request.setAttribute("message", "用户名或者密码错误！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String ipAddr=getIpAddr(request);
		service.saveIp(ipAddr,username,true);
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

