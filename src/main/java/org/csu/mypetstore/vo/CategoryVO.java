package org.csu.mypetstore.vo;

import lombok.Data;
import org.csu.mypetstore.entity.Product;

import java.util.List;

@Data
public class CategoryVO {
    private String categoryId;
    private String categoryName;
    private List<Product> productList;
}
