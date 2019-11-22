package cn.kzm.dao;

import cn.kzm.domain.Order;
import cn.kzm.domain.OrderItem;
import cn.kzm.domain.User;


import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    void saveOrder(Connection connection, Order order)throws Exception;

    void saveOrderItem(Connection connection, OrderItem item)throws Exception;

    int getTotalRecords(User user)throws Exception;

    List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    void updateOrder(Order order)throws Exception;
}
