package cn.xzc.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

//处理用户注销请求
@WebServlet("/client/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user=(User)request.getSession().getAttribute("user");
			String ipAddr=getIpAddr(request);
			BusinessService service = new BusinessServiceImpl();
			service.saveIp(ipAddr,user.getUsername(),false);
			session.removeAttribute("user");
			session.removeAttribute("cart");
		}
		
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
		doGet(request, response);
	}

}