package cn.kzm.service;

import cn.kzm.domain.PageModel;
import cn.kzm.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findNews()throws Exception;

    List<Product> findHots()throws Exception;

    Product findProductBypid(String pid)throws Exception;

    PageModel findProductBycidWithPage(String cid, int curNum)throws Exception;
}
