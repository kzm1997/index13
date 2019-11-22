package cn.kzm.dao;

import cn.kzm.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findNews()throws Exception;

    List<Product> findHots()throws Exception;

    Product findProductBypid(String pid)throws Exception;

    int finTotal(String cid)throws Exception;

    List findProductBycidWithPage(String cid, int startIndex, int pageSize)throws Exception;
}
