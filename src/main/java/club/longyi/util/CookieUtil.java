package club.longyi.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static Cookie getCookie(String name,HttpServletRequest request){
		Cookie[] cos = request.getCookies();
		if(cos!=null){
			for(Cookie co : cos){
				if(co.getName().equals(name)){
					return co;
				}
			}
		}
		return null;
	}
	public static void setCookie(String name,String value,String path,int age,HttpServletResponse response){
		Cookie co = new Cookie(name, value);
		co.setPath(path);
		co.setMaxAge(age); //秒
		response.addCookie(co);
	}
	public static void setCookie(String name,String value,int age,HttpServletResponse response){
		setCookie(name, value, "/", age,response);
	}
	public static void removeCookie(String name,HttpServletResponse response){
		setCookie(name, null, 0, response);
	}
}
