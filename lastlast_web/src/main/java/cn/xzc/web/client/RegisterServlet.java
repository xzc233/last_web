package cn.xzc.web.client;

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

@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//在实际开发中，一定要进行表单校验
			User user = WebUtils.request2Bean(request, User.class);
			user.setId(UUID.randomUUID().toString());
			
			if(user.getPermission() == null)user.setPermission("normal");
			BusinessService service = new BusinessServiceImpl();
			if(service.findUsername(user.getUsername())!=null)request.setAttribute("message", "用户名重复！");
			else {service.addUser(user);
			request.setAttribute("message", "恭喜您，注册成功！！！1秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='1;url="+request.getContextPath()+"/client/IndexServlet'");
				}
			} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "注册用户失败！！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
