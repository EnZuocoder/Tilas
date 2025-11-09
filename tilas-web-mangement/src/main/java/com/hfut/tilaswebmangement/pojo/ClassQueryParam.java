package com.hfut.tilaswebmangement.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
@Data
public class ClassQueryParam {
    // 班级名称，非必须
    private String name;
    // 范围匹配的开始时间，可选，格式 yyyy-MM-dd
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    // 范围匹配的结束时间，可选，格式 yyyy-MM-dd
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    // 分页页码，必须，默认 1，最小值 1
    private Integer page = 1;
    // 每页条数，必须，默认 10，最小值 1
    private Integer pageSize = 10;
}
