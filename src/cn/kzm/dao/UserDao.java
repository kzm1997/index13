package cn.kzm.dao;

import cn.kzm.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void userReigst(User user)throws SQLException;

    User userActive(String code) throws SQLException;

    void updateUser(User user) throws SQLException;

    User userLogin(User user)throws SQLException;
}
