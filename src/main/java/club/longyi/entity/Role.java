package club.longyi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 角色
 *
 * @author Master.Xia
 */
@Entity
@Table(name = "asf_helper_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"users", "powers"})
public class Role extends IdEntity {
    private String name; //如：管理员，技术总监，总经理
    private String flag; //如：admin,cto,zjl

    @Transient
    private List<String> powerNames;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<User> users = new ArrayList<User>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Power> powers = new ArrayList<Power>();

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getPowerNames() {
        return powerNames;
    }

    public void setPowerNames(List<String> powerNames) {
        this.powerNames = powerNames;
    }

}
