package org.csu.mypetstore.service;

import org.csu.mypetstore.vo.CategoryVO;
import org.csu.mypetstore.vo.ItemVO;
import org.csu.mypetstore.vo.ProductVO;

public interface CatalogService {
    public CategoryVO getCategory(String categoryId);

    public ProductVO getProduct(String productId);

    public ItemVO getItem(String itemId);
}
