package com.hfut.tilaswebmangement.pojo;
import lombok.Data;
import java.util.List;
//分页查询返回的结果:total总记录数
//rows:该页展示的结果
@Data
public class PageResult<T> {
    Long total;
    List<T> rows;
}
