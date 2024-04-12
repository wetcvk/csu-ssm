package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    @TableId(value = "itemid")
    private String itemId;
    @TableField(value = "productid")
    private String productId;
    @TableField(value = "listprice")
    private BigDecimal listPrice;
    @TableField(value = "unitcost")
    private BigDecimal unitCost;
    @TableField(value = "supplier")
    private int supplierId;
    private String status;
    @TableField(value = "attr1")
    private String attribute1;
    @TableField(value = "attr2")
    private String attribute2;
    @TableField(value = "attr3")
    private String attribute3;
    @TableField(value = "attr4")
    private String attribute4;
    @TableField(value = "attr5")
    private String attribute5;
}