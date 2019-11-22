package cn.kzm.dao.daoImpl;

import cn.kzm.dao.OrderDao;
import cn.kzm.domain.Order;
import cn.kzm.domain.OrderItem;
import cn.kzm.domain.Product;
import cn.kzm.domain.User;
import cn.kzm.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void saveOrder(Connection connection, Order order) throws Exception {
        String sql="insert into orders values(?,?,?,?,?,?,?,?)";
        QueryRunner queryRunner=new QueryRunner();
        Object []params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.
                getName(),order.getTelephone(),order.getUser().getUid()};
        queryRunner.update(connection,sql,params);

    }

    @Override
    public int getTotalRecords(User user) throws Exception {
        String sql="select count(*) from orders where uid=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return  ((Long)queryRunner.query(sql,new ScalarHandler(),user.getUid())).intValue();
    }

    @Override
    public void saveOrderItem(Connection connection, OrderItem item) throws Exception {
      String sql="insert  into orderitem values(?,?,?,?,?)";
        QueryRunner queryRunner=new QueryRunner();
        Object []params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
        queryRunner.update(connection,sql,params);
    }

    @Override
    public List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception {
        String sql="select * from orders where uid=? limit ? , ?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        List<Order>list =queryRunner.query(sql,new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);

        //遍历订单
        for (Order order:list){
            //获取每笔订单oid，查询每笔订单项以及订单项对应的商品信息
             String oid=order.getOid();
              sql="select * from orderitem o , product p where o.pid=p.pid and oid= ?";
              List<Map<String,Object>>list02=queryRunner.query(sql,new MapListHandler(),oid);
              //遍历list
            for(Map<String,Object>map:list02){
                 OrderItem orderItem=new OrderItem();
                Product product=new Product();
                //创建时间类型的转换器
                DateConverter dt=new DateConverter();
                //设置转换的格式
                dt.setPattern("yyyy-MM-dd");
                //注册转换器
                ConvertUtils.register(dt,java.util.Date.class);
                //将map中属于orderItem的数据自动填充到orderItem对象上
                BeanUtils.populate(orderItem,map);
                //将map中product的数据自动填充到product对象上
                BeanUtils.populate(product,map);

                orderItem.setProduct(product);
                order.getList().add(orderItem);

            }
        }
        return list;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        String sql="select * from orders where oid =?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Order order=queryRunner.query(sql,new BeanHandler<Order>(Order.class),oid);
        //根据订单ID查询订单下所有的订单项信息
        sql="select * from orderitem o , product p where o.pid=p.pid and oid= ?";
        List<Map<String,Object>>list02 =queryRunner.query(sql,new MapListHandler(),oid);
        //遍历list
        for (Map<String,Object>map:list02){
            OrderItem orderItem=new OrderItem();
            Product product=new Product();
            //创建时间类型的转换器
            DateConverter dt=new DateConverter();
            //设置转换的格式
            dt.setPattern("yyyy-MM-dd");
            //注册转换器
            ConvertUtils.register(dt,java.util.Date.class);
            //将map中属于orderItem的数据自动填充到orderItem对象上
            BeanUtils.populate(orderItem,map);
            //将map中product的数据自动填充到product对象上
            BeanUtils.populate(product,map);
            orderItem.setProduct(product);
            order.getList().add(orderItem);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
         String sql="update orders set ordertime=? ,total=? ,state=?,address=?, name=?,telephone=?  where oid=?";
         QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
         Object [] parms={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.
                 getName(),order.getTelephone(),order.getOid()};
         queryRunner.update(sql,parms);
    }
}
