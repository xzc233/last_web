package cn.xzc.web.manager;
import java.io.*;
import java.util.Properties;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

import cn.xzc.domain.User;

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet{
    
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  
	  final Properties props = new Properties();
	  User user=(User)request.getSession().getAttribute("user");
	  if(user==null){
		  request.setAttribute("message", "请先登录！<meta http-equiv='refresh' content='2;url="+request.getContextPath()+"/LoginUIServlet'>");
		  request.getRequestDispatcher("/message.jsp").forward(request, response);
		  
	  }
      // 表示SMTP发送邮件，必须进行身份验证
      props.put("mail.smtp.auth", "true");
      //此处填写SMTP服务器
      props.put("mail.smtp.host", "smtp.qq.com");
      //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
      props.put("mail.smtp.port", "587");
      // 此处填写你的账号
      props.put("mail.user","827689391@qq.com");
      // 此处的密码就是前面说的16位STMP口令
      props.put("mail.password","oebtkrusgplvbahg");

      // 构建授权信息，用于进行SMTP进行身份验证
      Authenticator authenticator = new Authenticator() {

          protected PasswordAuthentication getPasswordAuthentication() {
              // 用户名、密码
              String userName = props.getProperty("mail.user");
              String password = props.getProperty("mail.password");
              return new PasswordAuthentication(userName, password);
          }
      };
      // 使用环境属性和授权信息，创建邮件会话
      Session mailSession = Session.getInstance(props, authenticator);
      // 创建邮件消息
      MimeMessage message = new MimeMessage(mailSession);
      // 设置发件人
      InternetAddress form;
		try {
			form = new InternetAddress(
			        props.getProperty("mail.user"));
		
			message.setFrom(form);
		
      // 设置收件人的邮箱
      InternetAddress to = new InternetAddress(user.getEmail());
      message.setRecipient(Message.RecipientType.TO, to);
      // 设置邮件标题
      message.setSubject("来自商城的一封邮件");
      // 设置邮件的内容体
      message.setContent("付款成功", "text/html;charset=UTF-8");
      // 最后当然就是发送邮件啦
      Transport.send(message);
      request.setAttribute("message", "邮件已成功发送！！！");
	  request.getRequestDispatcher("/message.jsp").forward(request, response);
	} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
} 