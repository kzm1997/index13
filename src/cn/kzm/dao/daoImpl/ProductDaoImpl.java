package cn.kzm.dao.daoImpl;

import cn.kzm.dao.ProductDao;
import cn.kzm.domain.Product;
import cn.kzm.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findNews() throws Exception {
        String sql="select * from product where pflag=0 order by pdate desc limit 0, 9 ";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findHots() throws Exception {
        String sql="select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,9 ";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product findProductBypid(String pid) throws Exception {
        String sql="select * from product where pid= ? ";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    @Override
    public int finTotal(String cid) throws Exception {
        String sql="select count(*) from product where cid= ?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Long num=(Long)queryRunner.query(sql,new ScalarHandler(),cid);
        return num.intValue();
    }

    @Override
    public List findProductBycidWithPage(String cid, int startIndex, int pageSize) throws Exception {
        String sql="select * from product where cid = ? limit ? , ?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
    }
}
