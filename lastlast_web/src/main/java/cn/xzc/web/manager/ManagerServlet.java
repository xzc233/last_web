package cn.xzc.web.manager;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;
import cn.xzc.utils.WebUtils;

@WebServlet("/manager/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	
	private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","���ȵ�¼��");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		if(!user.getPermission().equals("root")) {
			request.setAttribute("message",user.getPermission()+"���޴�Ȩ�޲�����");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		String method = request.getParameter("method");
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("set".equals(method)) {
			set(request, response);
		}
		if ("del".equals(method)) {
			delete(request, response);
		}
	}
		private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//��ʵ�ʿ����У�һ��Ҫ���б�У��
			User user = WebUtils.request2Bean(request, User.class);
			user.setId(UUID.randomUUID().toString());
			user.setPermission("manager");
			service.addUser(user);
			saveOp(request,response,"addUser");
			request.setAttribute("message", "��ϲ����ע��ɹ�������");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ע���û�ʧ�ܣ�����");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
		
		
		private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (!"991213".equals(password)) {
				request.setAttribute("message", "root������󣡣�");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			User user = service.findManager(username);
			if(user == null) {
				request.setAttribute("message","�޴˹����ߣ�");
				request.getRequestDispatcher("/message.jsp").forward(request,response);
				return;
			}
			try {
				service.delUser(user.getId());
				saveOp(request,response,"delUser");
				request.setAttribute("message", "��ϲ����ɾ���ɹ�������");
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "ɾ��ʧ�ܣ�����");
			}
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
		private void set(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String newPwd = request.getParameter("newPwd");
			if (!password.equals(newPwd)) {
				request.setAttribute("message", "�������벻ͬ����");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			User user = service.findManager(username);
			if (user == null) {
				request.setAttribute("message", "�û������󣡣�");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			try {
				service.setPassword(user.getId(),newPwd);
				saveOp(request,response,"setPwd");
				request.setAttribute("message", "��ϲ�����޸ĳɹ�������");
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "�޸�����ʧ�ܣ�����");
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
		doGet(request, response);
	}

}