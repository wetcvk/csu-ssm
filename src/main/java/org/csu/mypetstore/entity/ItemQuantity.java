package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("inventory")
@Data
public class ItemQuantity {
    @TableId(value = "itemid")
    private String itemId;
    @TableField("qty")
    private Integer quantity;
}
