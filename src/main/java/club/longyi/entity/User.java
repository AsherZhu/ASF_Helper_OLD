package club.longyi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="asf_helper_user")
@JsonIgnoreProperties({"roles","password"})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {
	@Column(unique=true,nullable=false)
	private String username;



	@Column(nullable=false)
	private String password;
	@Column(unique = true, nullable = false)
	private String taobaoId;
	@Column(unique=true,nullable=false)

	private String email;		//用户邮箱，唯一
	private Date ct;			//注册时间
	private String ipAddr;      //注册的ip地址
	
	@Transient
	private List<String> roleNames; //不与数据库映射，可以存一些角色名字
	
	@ManyToMany(fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name="asf_helper_user_role",joinColumns= {@JoinColumn(name="user_id")},inverseJoinColumns= {@JoinColumn(name="role_id")})
	private List<Role> roles = new ArrayList<Role>();

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTaobaoId() {
		return taobaoId;
	}
	public void setTaobaoId(String taobaoId) {
		this.taobaoId = taobaoId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCt() {
		return ct;
	}
	public void setCt(Date ct) {
		this.ct = ct;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	
}
