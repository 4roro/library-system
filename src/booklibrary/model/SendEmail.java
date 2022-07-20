package booklibrary.model;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmail {
	public void sendEmail(String email,String info) throws MessagingException, GeneralSecurityException{

		Properties prop = new Properties();
		// 开启debug调试，以便在控制台查看
		prop.setProperty("mail.debug", "true"); 
		// 设置邮件服务器主机名
		prop.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		prop.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		prop.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session1 = Session.getInstance(prop);
		// 通过session得到transport对象
		Transport ts = session1.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com","2186527424", "roygsfrrxlkydjjg");//后面的字符是授权码，用qq密码反正我是失败了（用自己的，别用我的，这个号是我瞎编的，为了。。。。）
		// 创建邮件
		MimeMessage message = new MimeMessage(session1);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("2186527424@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		// 邮件的标题
		message.setSubject("图书馆提醒还书通知:");
		// 邮件的文本内容
		String message1="尊敬的用户您好，你借阅的书籍："+info+"已逾期，请尽快归还!";
		message.setContent(message1, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}
	
}
