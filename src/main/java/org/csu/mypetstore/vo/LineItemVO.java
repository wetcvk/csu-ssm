package org.csu.mypetstore.vo;

import lombok.Data;
import org.csu.mypetstore.entity.Item;

@Data
public class LineItemVO {
    private int orderId;
    private int lineNum;
    private String itemId;
    private int quantity;
    private double unitPrice;

    private Item item;
}
