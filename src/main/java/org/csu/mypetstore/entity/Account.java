package org.csu.mypetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {
    @TableId(value = "userid")
    private String username;
//    private String password;
    private String email;
    @TableField("firstname")
    private String firstName;
    @TableField("lastname")
    private String lastName;
    private String status;
    @TableField("addr1")
    private String address1;
    @TableField("addr2")
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
//    private String favouriteCategoryId;
//    private String languagePreference;
//    private boolean listOption;
//    private boolean bannerOption;
//    private String bannerName;
}
