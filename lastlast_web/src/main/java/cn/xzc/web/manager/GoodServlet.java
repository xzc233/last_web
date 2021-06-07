package cn.xzc.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Good;
import cn.xzc.domain.User;
import cn.xzc.domain.Category;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;
import cn.xzc.utils.WebUtils;

@WebServlet("/manager/GoodServlet")
public class GoodServlet extends HttpServlet {
	
	private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","请先登录！");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		if ("forAddUI".equals(method)) {
			forAddUI(request, response);
		}
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("list".equals(method)) {
			if(!user.getPermission().equals("root")) {
				request.setAttribute("message",user.getPermission()+"你无此权限操作！");
				request.getRequestDispatcher("/message.jsp").forward(request,response);
				return;
			}
			list(request, response);
		}
		if ("listbyUserid".equals(method)) {
			listbyUserid(request, response);
		}
		if ("del".equals(method)) {
			deleteGood(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Good> list = service.getAllGood();
		request.setAttribute("list", list);
		saveOp(request,response,"getAllGood");
		request.getRequestDispatcher("/manager/listgood.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data=request.getParameter("num");
			Good good = WebUtils.upload(request, this.getServletContext().getRealPath("/images"));
			String num="0";
			if(data!=null)num=data;
			User user=(User)request.getSession().getAttribute("user");
			good.setUserid(user.getId());
			good.setNum(num);
			service.addGood(good);
			saveOp(request,response,"addGood");
			request.setAttribute("message", "添加成功！！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void forAddUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addgood.jsp").forward(request, response);
	}
	private void deleteGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			service.deleteGood(id);
			request.setAttribute("message", "删除成功");
			saveOp(request,response,"deleteGood");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void listbyUserid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String userid=user.getId();
		List<Good> list = service.getGoodbyUserid(userid);
		saveOp(request,response,"checkOwnGood");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listgood.jsp").forward(request, response);
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

