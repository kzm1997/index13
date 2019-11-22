package cn.kzm.service;

import cn.kzm.domain.Order;
import cn.kzm.domain.PageModel;
import cn.kzm.domain.User;

public interface OrderService {

    void saveOrder(Order order)throws Exception;

    PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    void updateOrder(Order order)throws Exception;
}
