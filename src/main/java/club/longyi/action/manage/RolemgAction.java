package club.longyi.action.manage;


import club.longyi.action.BaseAction;
import club.longyi.common.persistence.Paging;
import club.longyi.entity.Power;
import club.longyi.entity.Role;
import club.longyi.service.CommonService;
import club.longyi.service.UserService;
import club.longyi.util.BeanHelper;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@ParentPackage("app-default")
public class RolemgAction extends BaseAction {

    @Autowired
    CommonService service;
    @Autowired
    UserService userService;

    public String save() throws Exception {
        Role r = BeanHelper.bean(request, Role.class);
        try {
            service.save(r);
            out.print("success");
        } catch (Exception e) {
            out.print("error");
        }

        return null;
    }

    public String update() throws Exception {
        Role r = BeanHelper.bean(request, Role.class);
        try {
            Role entity = service.findById(Role.class, r.getId());
            entity.setName(r.getName());
            entity.setFlag(r.getFlag());
            service.update(entity);
            out.print("success");
        } catch (Exception e) {
            out.print("error");
        }

        return null;
    }

    public String delete() throws Exception {
        String id = request.getParameter("id");
        try {
            service.delete(Role.class, id);
            out.print("success");
        } catch (Exception e) {
            out.print("error");
        }
        return null;
    }

    public String find() throws Exception {
        Paging<Role> p = service.find(Role.class, page, rows);
        map.put("total", p.getTotalCount());
        map.put("rows", p.getList());


        p.getList().forEach(role -> {
            role.setPowerNames(new ArrayList<String>());
            role.getPowers().forEach(power -> {
                role.getPowerNames().add(power.getName());
            });
        });


        this.printJson(map);
        return null;
    }

    public String findByUserId() throws Exception {
        String userid = request.getParameter("uid");
        String hql = "select r from Role r inner join r.users u where u.id=?";
        Paging<Role> p = service.find(Role.class, hql, page, rows, new Object[]{userid});
        this.printJson(p.getList());
        return null;
    }

    public String shouquan() throws Exception {
        String roleid = request.getParameter("r");
        String[] powerids = request.getParameterValues("p");
        try {
            Role role = service.findById(Role.class, roleid);
            role.setPowers(new ArrayList<Power>());
            for (String pid : powerids) {
                Power power = service.findById(Power.class, pid);
                role.getPowers().add(power);
            }
            service.update(role);
            out.print("success");
        } catch (Exception e) {
            out.print("error");
        }
        return null;
    }
}
