package com.hfut.tilaswebmangement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassOption {
    private List clazzList;//班级名称列表
    private List dataList;//对应班级的学生数量列表
}
