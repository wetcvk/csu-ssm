package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Profile {
    @TableId(value = "userid")
    private String username;
    @TableField("langpref")
    private String language;
    @TableField("favcategory")
    private String fav;
    @TableField("mylistopt")
    private boolean listOption;
    @TableField("banneropt")
    private boolean bannerOption;
}
