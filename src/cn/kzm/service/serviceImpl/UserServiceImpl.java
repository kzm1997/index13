package cn.kzm.service.serviceImpl;

import cn.kzm.dao.UserDao;
import cn.kzm.dao.daoImpl.UserDaoImpl;
import cn.kzm.domain.User;
import cn.kzm.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public void userReiget(User user)throws SQLException {
        //实现注册功能
        UserDao userDao=new UserDaoImpl();
        userDao.userReigst(user);
    }

    @Override
    public boolean userActive(String code)throws SQLException {
        UserDao userDao=new UserDaoImpl();
        User user=userDao.userActive(code);
        if(user!=null){
             //修改用户的状态
            user.setState(1);
            user.setCode(null);
            //对数据库更新数据
            userDao.updateUser(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User userLogin(User user) throws Exception {
        UserDao userDao=new UserDaoImpl();
      User user1= userDao.userLogin(user);
      if(user1==null){
          throw new RuntimeException("密码有误！");
      }else if(user1.getState()==0){
          throw new RuntimeException("用户未激活");
      }else {
          return user1;
      }
    }
}
