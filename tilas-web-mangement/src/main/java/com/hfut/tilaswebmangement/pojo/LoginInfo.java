package com.hfut.tilaswebmangement.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//返回给前端的用户登录信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private String token;
    private Integer id;
    private String name;
    private String username;
}
