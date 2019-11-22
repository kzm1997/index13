package cn.kzm.service.serviceImpl;

import cn.kzm.dao.CategoryDao;
import cn.kzm.dao.daoImpl.CategoryDaoImpl;
import cn.kzm.domain.Category;
import cn.kzm.service.CategoryService;
import cn.kzm.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public void addCategory(Category category) throws Exception {
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.addCategory(category);
        //更新redis缓存
        Jedis jedis=JedisUtil.getJedis();
        jedis.del("allCats");
        JedisUtil.closeJedis(jedis);
    }

    @Override
    public List<Category> getAllCats() throws Exception {
        CategoryDao categoryDao=new CategoryDaoImpl();
        return categoryDao.getALLCats();

    }
}
