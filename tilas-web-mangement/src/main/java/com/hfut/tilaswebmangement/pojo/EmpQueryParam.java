package com.hfut.tilaswebmangement.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
//记录查询参数的实体类
public class EmpQueryParam {
    String name;
    Integer gender;
    //形如yyyy-MM-dd这种格式 不管我在这设定的格式如何,都能请求成功
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate end;
    Integer page=1;
    Integer pageSize=10;

}
