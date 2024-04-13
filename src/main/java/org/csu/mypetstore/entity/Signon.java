package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Signon {
    @TableId
    private String username;
    private String password;
}
