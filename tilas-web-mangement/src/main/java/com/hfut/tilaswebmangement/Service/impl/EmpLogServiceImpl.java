package com.hfut.tilaswebmangement.Service.impl;
import com.hfut.tilaswebmangement.Mapper.EmpLogMapper;
import com.hfut.tilaswebmangement.Service.EmpLogService;
import com.hfut.tilaswebmangement.pojo.EmpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //代表在事务传递过程中新建一个新事物不受到旧事务的影响
    // 默认 REQUIRES是加入到上一层事务
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
