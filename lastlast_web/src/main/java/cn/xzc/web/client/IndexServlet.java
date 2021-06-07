package cn.xzc.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xzc.domain.Category;
import cn.xzc.domain.PageBean;
import cn.xzc.domain.QueryInfo;
import cn.xzc.service.Impl.BusinessServiceImpl;
import cn.xzc.utils.WebUtils;

//��ȡ��ҳ����
@WebServlet("/client/IndexServlet")
public class IndexServlet extends HttpServlet {
	
	private BusinessServiceImpl service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ȿ�������û�д���ѯ��������
		QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
		String category_id = request.getParameter("category_id");
		//IndexServlet������ĳ����������ķ�ҳ�����ִ������еķ�ҳ��������Ҫ�жϿͻ�����û�д�category_id����
		if (category_id != null && !category_id.trim().equals("")) {
			info.setQueryname("category_id");
			info.setQueryvalue(category_id);
		}
		
		List<Category> categories = service.getAllCategory();
		PageBean pageBean = service.bookPageQuery(info);
		
		request.setAttribute("categories", categories);
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

