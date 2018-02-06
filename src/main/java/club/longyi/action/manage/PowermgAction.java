package club.longyi.action.manage;


import club.longyi.action.BaseAction;
import club.longyi.common.persistence.Paging;
import club.longyi.entity.Power;
import club.longyi.service.CommonService;
import club.longyi.util.BeanHelper;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("app-default")
public class PowermgAction extends BaseAction {
	
	@Autowired
	CommonService service;
	
	public String save() throws Exception {
		Power info = BeanHelper.bean(request, Power.class);
		try {
			service.save(info);
			out.print("success");
		} catch (Exception e) {
			out.print("error");
		}
		return null;
	}
	
	public String update() throws Exception {
		Power info = BeanHelper.bean(request, Power.class); //id,name,flag
		try {
			Power power = service.findById(Power.class, info.getId());
			power.setName(info.getName());
			power.setFlag(info.getFlag());
			service.update(power);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		return null;
	}
	public String delete() throws Exception {
		String id = request.getParameter("id");
		
		try {
			service.delete(Power.class, id);
			out.print("success");
		} catch (Exception e) {
			out.print("error");
		}
		return null;
	}
	public String find() throws Exception {
		Paging<Power> p = service.find(Power.class, page, rows);
		map.put("total", p.getTotalPage());
		map.put("rows", p.getList());
		this.printJson(map);
		return null;
	}
	
	public String findByRoleId() throws Exception {
		String roleid = request.getParameter("rid");
		String hql="select p from Power p inner join p.roles r where r.id=?";
		Paging<Power> p = service.find(Power.class,hql, page, rows,new Object[] {roleid});
		this.printJson(p.getList());
		return null;
	}
	
}
