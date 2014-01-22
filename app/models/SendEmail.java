package models;
/**
 * 
 * @author qiang
 *客户向客服发邮件，统一使用scauMilkCustomer@163.com发送到scauMilk@163.com
 */
public class SendEmail {
	MailSenderInfo mailInfo = null;
/*	
	public SendEmail() {
		String fromEmail = "你好";
		String title = "test";
		String contentTexts = "asdfdsafasfdsf";
		
		 mailInfo = new MailSenderInfo();    
	     mailInfo.setMailServerHost("smtp.163.com");    //设置服务器
	     mailInfo.setMailServerPort("25");    //设置端口
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("scauMilk@163.com");    
	     mailInfo.setPassword("scauscau");//您的邮箱密码 
	     mailInfo.setFromAddress("scauMilk@163.com");		    
	     mailInfo.setToAddress("362341992@qq.com");    
	     mailInfo.setSubject("来自顾客" + fromEmail + title);    //设置邮箱标题
	     mailInfo.setContent(contentTexts); //设置邮箱内容
	}
*/
	public SendEmail(String fromEmail, String title, String contentTexts) {
		 mailInfo = new MailSenderInfo();    
	     mailInfo.setMailServerHost("smtp.163.com");    //设置服务器
	     mailInfo.setMailServerPort("25");    //设置端口
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("scauMilkCustomer@163.com");    
	     mailInfo.setPassword("scauscau");//您的邮箱密码 
	     mailInfo.setFromAddress("scauMilkCustomer@163.com");		    
	     mailInfo.setToAddress("362341992@qq.com");    
	     mailInfo.setSubject(title + "----from:" + fromEmail);    //设置邮箱标题
	     mailInfo.setContent(contentTexts); //设置邮箱内容
	}
	public void send() {		    
		 //这个类主要来发送邮件 
	     SimpleMailSender sms = new SimpleMailSender();   
	  //       sms.sendTextMail(mailInfo);//发送文体格式  
	         sms.sendHtmlMail(mailInfo);//发送html格式  
	}
}