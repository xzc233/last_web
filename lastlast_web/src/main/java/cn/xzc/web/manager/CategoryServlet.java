package cn.xzc.web.manager;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Category;
import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;
import cn.xzc.utils.WebUtils;

@WebServlet("/manager/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	
	private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","ÇëÏÈµÇÂ¼£¡");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		String method = request.getParameter("method");
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = service.getAllCategory();
		saveOp(request,response,"getAllCategory");
		request.setAttribute("categories", list);
		request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Category c = WebUtils.request2Bean(request, Category.class);
			c.setId(UUID.randomUUID().toString());
			service.addCategory(c);
			
			request.setAttribute("message", "Ìí¼Ó³É¹¦£¡£¡");
			saveOp(request,response,"addCategory");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Ìí¼ÓÊ§°Ü£¡£¡");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
