package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BannerData {
    @TableId("favcategory")
    private String fav;
    @TableField("bannername")
    private String name;
}
