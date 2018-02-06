package club.longyi.action;

import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage("app-default")
public class VerifyAction extends BaseAction {
	
	public String execute() throws Exception {
		
		response.reset();
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		club.longyi.util.VerifyCode vCode = new club.longyi.util.VerifyCode(300, 90, 4, 10); //一个验证码，和一个图片
		session.setAttribute("code", vCode.getCode());
		vCode.write(response.getOutputStream());
		
		return null;
	}
}
