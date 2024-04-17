package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lineitem")
public class LineItem {
    @TableId("orderid")
    private int orderId;
    @TableField("linenum")
    private int lineNum;
    @TableField("itemid")
    private String itemId;
    private int quantity;
    @TableField("unitprice")
    private double unitPrice;
}
