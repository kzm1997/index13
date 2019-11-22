package cn.kzm.service.serviceImpl;

import cn.kzm.dao.ProductDao;
import cn.kzm.dao.daoImpl.ProductDaoImpl;
import cn.kzm.domain.PageModel;
import cn.kzm.domain.Product;
import cn.kzm.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao=new ProductDaoImpl();
    @Override
    public List<Product> findNews() throws Exception {
        return productDao.findNews();
    }

    @Override
    public List<Product> findHots() throws Exception {
        return productDao.findHots();
    }

    @Override
    public Product findProductBypid(String pid) throws Exception {
        return productDao.findProductBypid(pid);
    }

    @Override
    public PageModel findProductBycidWithPage(String cid, int curNum) throws Exception {
        //创建一个pagemodel
        //统计当前分类下商品个数
        int total=productDao.finTotal(cid);
        PageModel pm=new PageModel(curNum,total,12);
        //关联集合 select * from product where cid=? limit ?,?
        List list=productDao.findProductBycidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //关联url
        pm.setUrl("ProductServlet?method=findProductBycidWithPage&cid="+cid);
        return pm;
    }
}
