package club.longyi.action.manage;


import club.longyi.action.BaseAction;
import club.longyi.common.persistence.Paging;
import club.longyi.entity.Role;
import club.longyi.entity.User;
import club.longyi.service.CommonService;
import club.longyi.service.UserService;
import club.longyi.util.BeanHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ParentPackage("app-default")
public class UsermgAction extends BaseAction {
	
	@Autowired
	UserService userService;
	@Autowired
	CommonService service;
	
	public String save() throws Exception {
		User user = BeanHelper.bean(request,User.class);
		
		try {
			userService.save(user);
			out.print("success");
		} catch (Exception e) {
			out.print(e.getMessage());
		}
		
		
		return null;
	}
	
	public String update() throws Exception {
		User user = BeanHelper.bean(request,User.class);

		try {
			userService.update(user);
			out.print("success");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public String delete() throws Exception {
		String id = request.getParameter("id");
		try {
			userService.delete(id);
			out.print("success");
		} catch (Exception e) {
			out.print("error");
		}
		return null;
	}
	public String find() throws Exception {
		Paging<User> p = userService.find(page, rows);
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", p.getTotalCount());
		map.put("rows", p.getList());
		
		p.getList().forEach(o->{
			o.getRoles().forEach(r->{
				if(o.getRoleNames()==null)o.setRoleNames(new ArrayList<String>());
				o.getRoleNames().add(r.getName());
			});
		});
		
		String json = mapper.writeValueAsString(map);
		out.print(json);
		return null;
	}
	
	public String shouquan() throws Exception{
		String uid = request.getParameter("uid");
		String[] rids = request.getParameterValues("rid");
		try {
			User user = service.findById(User.class, uid);
			user.setRoles(new ArrayList<Role>());
			for(String rid : rids) {
				Role role = service.findById(Role.class, rid);
				user.getRoles().add(role);
			}
			service.update(user);
			out.print("success");
		} catch (Exception e) {
			out.print("error");
		}
		//整段只有1次修改，失败可以回滚
		return null;
	}
}
