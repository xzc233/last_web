package cn.xzc.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Category;
import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet("/client/browseTimeServlet")
public class browseTimeServlet extends HttpServlet {
	
	private BusinessServiceImpl service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		Category cate=(Category)request.getSession().getAttribute("categories");
		if(user == null||cate == null) {
			return;
		}
		try{
			String time = request.getParameter("time");
			BusinessService service=new BusinessServiceImpl();
			service.saveBrowse(time,user.getUsername(),cate.getName());
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}