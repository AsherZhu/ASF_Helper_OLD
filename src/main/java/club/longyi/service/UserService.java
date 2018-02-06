package club.longyi.service;


import club.longyi.common.persistence.Paging;
import club.longyi.entity.User;

/**
 * 业务逻辑层接口
 * @author Master.Xia
 *
 */
public interface UserService {

	void save(User user) throws Exception;
	User findByUsername(String username) throws Exception;
	User findByEmail(String email) throws Exception;
	User find(String username, String password) throws Exception;
	Paging<User> find(int page, int rows)throws Exception;
	void update(User user)throws Exception;
	void delete(String id)throws Exception;

}
