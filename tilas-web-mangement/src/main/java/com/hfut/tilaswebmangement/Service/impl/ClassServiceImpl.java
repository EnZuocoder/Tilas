package com.hfut.tilaswebmangement.Service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hfut.tilaswebmangement.Mapper.ClassMapper;
import com.hfut.tilaswebmangement.Service.ClassService;
import com.hfut.tilaswebmangement.pojo.ClassQueryParam;
import com.hfut.tilaswebmangement.pojo.Clazz;
import com.hfut.tilaswebmangement.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper mapper;

    @Override
    public PageResult<Clazz> show(ClassQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Clazz> clazzList = mapper.find(param);
        for(Clazz z:clazzList)
        {
            //比较两个时间LocalDate.now() z.getBeginDate()
            LocalDate now=LocalDate.now();
            if(now.isBefore(z.getBeginDate()))
                z.setStatus("未开班");
            if(now.isAfter(z.getEndDate()))
                z.setStatus("已结课");
            else
                z.setStatus("在读中");
        }
        Page<Clazz>page=(Page<Clazz>) clazzList;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void DeleteById(Integer id) {
        mapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        //添加班级时，设置创建时间为当前时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        mapper.add(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz= mapper.getById(id);
        //比较两个时间LocalDate.now() z.getBeginDate()
        LocalDate now=LocalDate.now();
        if(now.isBefore(clazz.getBeginDate()))
            clazz.setStatus("未开班");
        if(now.isAfter(clazz.getEndDate()))
            clazz.setStatus("已结课");
        else
            clazz.setStatus("在读中");
        return clazz;
    }
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        mapper.update(clazz);

    }
    @Override
    public List<Clazz> getAll() {
        //按理来讲我应该为每个班级设置状态，
        //但是这个方法主要是给前端下拉框用的，没必要设置状态
        return mapper.getAll();
    }
}
