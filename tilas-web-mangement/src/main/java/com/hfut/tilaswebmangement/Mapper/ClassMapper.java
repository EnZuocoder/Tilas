package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.ClassQueryParam;
import com.hfut.tilaswebmangement.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Clazz> find(ClassQueryParam param);
    void deleteById(Integer id);
    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> getAll();
}
