package club.longyi.action;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;
	protected PrintWriter out;
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = request.getSession();
		this.application = this.request.getServletContext();
	}
	public void setServletResponse(HttpServletResponse arg0) {
		try {
			this.response = arg0;
			this.out = this.response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printJson(String json) {
		response.setContentType("application/json;charset=utf-8");
		out.print(json);
	}
	public void printJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		System.out.println(json);
		this.printJson(json);
	}
	
	
	public int page;
	public int rows;
	
	
	public Map<String, Object> map = new HashMap<String, Object>(); //没什么特别含义，需要的时候用
}
