package cn.kzm.service;

import cn.kzm.domain.User;

import java.sql.SQLException;

public interface UserService {
    void userReiget(User user)throws SQLException;

    boolean userActive(String code)throws SQLException;

    User userLogin(User user)throws Exception;
}
