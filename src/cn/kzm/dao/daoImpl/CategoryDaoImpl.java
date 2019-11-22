package cn.kzm.dao.daoImpl;

import cn.kzm.dao.CategoryDao;
import cn.kzm.domain.Category;
import cn.kzm.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getALLCats() throws Exception {
        String sql = "select * from category";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void addCategory(Category category) throws Exception {
        String sql="insert into category values(?,?)";
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql,category.getCid(),category.getCname());
    }
}
