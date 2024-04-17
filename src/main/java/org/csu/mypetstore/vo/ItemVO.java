package org.csu.mypetstore.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVO {
    private String itemId;

    private String productId;
    private String productName;

    private String descriptionImage;
    private String descriptionText;

    private String status;

    private BigDecimal listPrice;
    private String attributes;

    private Integer quantity;
}
