package com.hfut.tilaswebmangement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {
    private List jobList;//职位名称列表
    private List dataList;//对应职位的员工数量列表
}
