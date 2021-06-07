package cn.xzc.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.xzc.domain.Cart;
import cn.xzc.domain.User;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message","���ȵ�¼��");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return;
		}
		//�õ��û��Ĺ��ﳵ
		try{
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessService service=new BusinessServiceImpl();
			service.saveOrder(cart,user);
			request.setAttribute("message","�������ɳɹ�����ȴ��̼ҷ�����");
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message","��������ʧ�ܣ������²�����");
		}
		request.getRequestDispatcher("/message.jsp").forward(request,response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}