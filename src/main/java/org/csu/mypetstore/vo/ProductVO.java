package org.csu.mypetstore.vo;

import lombok.Data;
import org.csu.mypetstore.entity.Item;

import java.util.List;

@Data
public class ProductVO {
    private String productId;
    private String categoryId;
    private String productName;

    private List<Item> itemList;
}