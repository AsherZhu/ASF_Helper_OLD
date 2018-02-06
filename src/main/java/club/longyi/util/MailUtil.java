package club.longyi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 快速发送邮件
 * @author Mr.Xia v1.0 create:2016.03.29 http://www.zhenzhigu.com
 * @author Mr.Xia v2.0 create:2017.01.11 http://www.zhenzhigu.com
 */
@Component
public class MailUtil {
	
	//Spring不支持静态属性注入
	private static JavaMailSenderImpl sender;
	private static TaskExecutor executor;
	
	@Autowired
	public void setSender(JavaMailSenderImpl jmsi){
		MailUtil.sender = jmsi;
	}
	@Resource(name="mailTaskExecutor")
	public void setExecutor(TaskExecutor executor){
		MailUtil.executor = executor;
	}
	
	public static void addSendMailTask(final MimeMessage mimeMessage) {
        try {
            executor.execute(new Runnable() {
                public void run(){
                    sender.send(mimeMessage);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 发送带附件的Mime邮件，可指定附件名
	 * @param to
	 * @param subject
	 * @param text
	 * @param fileNames[]
	 * @param files[]
	 * @param asyn 是否异步
	 * @throws MessagingException
	 */
	public static void send(String to,String subject,String text,String[] fileNames,File[] files,boolean asyn) throws MessagingException{
		//使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
	    MimeMessage msg = sender.createMimeMessage();

	    //创建MimeMessageHelper对象，处理MimeMessage的辅助类
	    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	    //使用辅助类MimeMessage设定参数
	    helper.setFrom(sender.getUsername());
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text,true);
	    
	    //加入附件
	    if(files!=null&&files.length>0){
	    	for(int i=0;i<files.length;i++){
	    		if(fileNames!=null&&i<fileNames.length&&fileNames[i]!=null){
	    			helper.addAttachment(fileNames[i], files[i]);
	    		}else{
	    			helper.addAttachment(files[i].getName(), files[i]);
	    		}
		    }
	    }
	    if(asyn){
	    	//使用线程池异步发送
	    	addSendMailTask(msg);
	    }else{
	    	sender.send(msg);
	    }
	}
	
	/**
	 * 发送带单个附件的Mime邮件，可指定附件名
	 * @param to
	 * @param subject
	 * @param text
	 * @param file
	 * @param asyn 是否异步
	 * @throws MessagingException
	 */
	public static void send(String to,String subject,String text,String fileName,File file,boolean asyn) throws MessagingException{
		send(to, subject, text, new String[]{fileName}, new File[]{file},asyn);
	}
	
	/**
	 * 发送带附件的Mime邮件
	 * @param to
	 * @param subject
	 * @param text
	 * @param fileNames[]
	 * @param files[]
	 * @param asyn 是否异步
	 * @throws MessagingException
	 */
	public static void send(String to,String subject,String text,File[] files,boolean asyn) throws MessagingException{
		send(to, subject, text, null, files,asyn);
	}
	
	/**
	 * 发送带单个附件的Mime邮件，使用默认附件名
	 * @param to
	 * @param subject
	 * @param text
	 * @param file
	 * @param asyn 是否异步
	 * @throws MessagingException
	 */
	public static void send(String to,String subject,String text,File file,boolean asyn) throws MessagingException{
		send(to, subject, text, file==null? null:file.getName(), file,asyn);
	}
	

	/**
	 * 发送无附件的mime邮件
	 * @param to
	 * @param subject
	 * @param text
	 * @param asyn 是否异步
	 * @throws MessagingException
	 */
	public static void send(String to,String subject,String text,boolean asyn) throws MessagingException{
		send(to, subject, text, new File[]{},asyn);
	}
	
}
