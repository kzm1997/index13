package cn.kzm.service.serviceImpl;

import cn.kzm.dao.OrderDao;
import cn.kzm.dao.daoImpl.OrderDaoImpl;
import cn.kzm.domain.Order;
import cn.kzm.domain.OrderItem;
import cn.kzm.domain.PageModel;
import cn.kzm.domain.User;
import cn.kzm.service.OrderService;
import cn.kzm.utils.JDBCUtils;

import javax.swing.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void saveOrder(Order order) throws Exception {

        Connection connection=null;
        try {
            //获取链接
            connection=JDBCUtils.getConnection();
           //开启事务
            connection.setAutoCommit(false);
            //保存订单
            OrderDao orderDao=new OrderDaoImpl();
            orderDao.saveOrder(connection,order);
            //保存订单项
            for (OrderItem item:order.getList()){
                orderDao.saveOrderItem(connection,item);
            }
            //提交
            connection.commit();
        } catch (Exception e) {
            //回滚
            e.printStackTrace();
            connection.rollback();
        }
    }

    @Override
    public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
        //创建pageModel对象，目的计算并且携带分页参数
        OrderDao orderDao=new OrderDaoImpl();
        //select count(*) from oders where uid=?
        int totalRecords=orderDao.getTotalRecords(user);
        PageModel pm=new PageModel(curNum,totalRecords,3);
        //关联集合
         //select * from orders where uid=? limit ?,?
       List list= orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //关联URL
        pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        OrderDao orderDao=new OrderDaoImpl();
        Order order=orderDao.findOrderByOid(oid);
        return order;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.updateOrder(order);
    }
}
