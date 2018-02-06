package club.longyi.service.impl;


import club.longyi.common.persistence.BaseDAO;
import club.longyi.common.persistence.Paging;
import club.longyi.entity.User;
import club.longyi.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

	//引入各种DAO
	@Resource(name="baseDAOImpl")
	BaseDAO dao ;
	@Autowired SessionFactory fac;


	//BeforeMethod-AfterReturning
	public void save(User user) throws Exception {
		//检测IP是否北京
		//用户名是否存在
		//名字是否吉利
		//今天是否黄道吉日
		
		//User info = dao.findByUsername(user.getUsername());

		//username如果存在抛出“UsernameAlreadyExist”运行时异常
		if(dao.findOneByProperty(User.class, "username", user.getUsername())!=null)throw new RuntimeException("UsernameAlreadyExist");
		//email如果存在抛出“EmailAlreadyExist”运行时异常
		if(dao.findOneByProperty(User.class, "email", user.getEmail())!=null)throw new RuntimeException("EmailAlreadyExist");
		//email如果存在抛出“EmailAlreadyExist”运行时异常
		if(dao.findOneByProperty(User.class, "email", user.getTaobaoId())!=null)throw new RuntimeException("taobaoIdAlreadyExist");

		//密码md5加盐处理
//		user.setPassword(DigestHelper.digest(user.getPassword(), DigestHelper.MD5));
		//获取注册时间
		user.setCt(new Date());
		dao.save(user);
		
		//throw new RuntimeException("你瞅啥"); //Runtime异常会导致事务回滚

		
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return dao.findOneByProperty(User.class, "username", username);
	}

	@Override
	public User findByEmail(String email) throws Exception {
		return dao.findOneByProperty(User.class, "email", email);
	}

	@Override
	public User find(String username, String password) throws Exception {
		
		User user = dao.findOneByProperty(User.class, "username",username);
		if(user==null) {
			throw new RuntimeException("UsernameNotExist");
		}
		if(!user.getPassword().equals(password)) {
			throw new RuntimeException("PasswordError");
		}
		//做各种判断
		
		return user;
	}

	@Override
	public Paging<User> find(int page, int rows) throws Exception {
		return dao.find(User.class, page, rows);
	}

	@Override
	public void update(User form) throws Exception {
		User info = dao.findById(User.class, form.getId());
		info.setUsername(form.getUsername());
		if(form.getPassword()!=null&&!form.getPassword().trim().equals("")) {
			info.setPassword(club.longyi.util.DigestHelper.digest(form.getPassword(), "MD5"));
		}
		info.setEmail(form.getEmail());
		dao.update(info);
	}

	@Override
	public void delete(String id) throws Exception {
		User info = dao.findById(User.class, id);
		dao.delete(info);
	}

}
