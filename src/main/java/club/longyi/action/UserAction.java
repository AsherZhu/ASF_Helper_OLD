package club.longyi.action;


import club.longyi.entity.User;
import club.longyi.service.UserService;
import club.longyi.util.IpUtil;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
//所有的action都已经托管给Spring，所以才能在里面自动装配

@ParentPackage("app-default")
public class UserAction extends BaseAction implements ModelDriven<User> {
	
	@Autowired
	UserService userService;


	
	private User user = new User(); //ModelDriven不需要getset，但是需要实例化


	/**
	 * 注册用户
	 * 参数：username,password,email,ok,cc
	 */
	public String save() throws Exception {
		
		String ok = request.getParameter("ok");
		String cc = request.getParameter("cc"); //用户填写的验证码
		
		if(ok==null)return "register";
		if(!cc.equals(session.getAttribute("code")))return "register";
		
		
		try {
			userService.save(user);
		} catch (Exception e) {
			if(e.getMessage().equals("UsernameAlreadyExist")) {
				//用户名已经存在
				System.out.println("用户名已经存在");
				response.sendRedirect(request.getContextPath()+"/register.html?msg=1");
			}
			if(e.getMessage().equals("EmailAlreadyExist")) {
				//邮箱地址已经存在
				System.out.println("邮箱已经存在");
			}
		}
		
		return "login";
		
	}
	/**
	 * 注册用户
	 * 参数：username,password,email,ok,cc
	 * 结果：0=未同意协议，1=验证码错误，2=用户名已经存在，3=邮箱已存在，4=淘宝id已存在，5=注册成功
	 */
	public String save2() throws Exception {
		
		String ok = request.getParameter("ok");
		String cc = request.getParameter("cc"); //用户填写的验证码
		
		if(ok==null) {
			out.print("0");
			return null;
		}
		System.out.println(cc);
		System.out.println(session.getAttribute("code"));
		if(!cc.equals(session.getAttribute("code"))) {
			out.print("1");
			return null;
		}
		
		
		try {
			user.setIpAddr(IpUtil.getRealIP(request));
			userService.save(user);
			out.print("5");
		} catch (Exception e) {
			System.out.println("------"+e.getMessage()+"------");
			if(e.getMessage().equals("UsernameAlreadyExist")) {
				//用户名已经存在
				out.print("2");
			}
			if(e.getMessage().equals("EmailAlreadyExist")) {
				//邮箱地址已经存在
				out.print("3");
			}
			if (e.getMessage().equals("taobaoIdAlreadyExist")) {
				//淘宝id已存在
				out.print("4");
			}
		}
		
		return null;
		
	}
	
	
	public String login() throws Exception {
		String a = request.getParameter("username");
		String b = request.getParameter("password");
		String c = request.getParameter("rem");
		try {
			User info = userService.find(a, club.longyi.util.DigestHelper.digest(b, "MD5")); //service实现中返回值一定不为null，如果null会抛异常
			session.setAttribute("LoginInfo", info);
			session.setAttribute("roles", info.getRoles());
			info.getRoles().size(); //调用一下，以确保执行查询
			
			if(c!=null) {
				club.longyi.util.CookieUtil.setCookie("login.username", a, Integer.MAX_VALUE, response);
				club.longyi.util.CookieUtil.setCookie("login.password", b, Integer.MAX_VALUE, response);
			}else {
				club.longyi.util.CookieUtil.removeCookie("login.username", response);
				club.longyi.util.CookieUtil.removeCookie("login.password", response);
			}
			out.print("login-success");
		} catch (Exception e) {
			if(e.getMessage().equals("UsernameNotExist")) {
				out.print("login-error-UsernameNotExist");
			}
			if(e.getMessage().equals("PasswordError")) {
				out.print("login-error-PasswordError");
			}
		}
		return null;
	}
	
	//检验用户名或密码是否存在，存在则返回1，不存在则返回0
	public String test() throws Exception {
		String ty = request.getParameter("ty"); //查询的类型：username,email两个值可选
		String val = request.getParameter("val");
		User info = null;
		if(ty.equals("username")) {
			info = userService.findByUsername(val);
		}
		if(ty.equals("email")) {
			info = userService.findByEmail(val);
		}
		if(ty.equals("taobaoId")) {
			info = userService.findByEmail(val);
		}
		if(info==null) {
			out.print("0");
		}else {
			out.print("1");
		}
		return null;
	}
	
	


	@Override
	public User getModel() {
		return user;
	}

}
