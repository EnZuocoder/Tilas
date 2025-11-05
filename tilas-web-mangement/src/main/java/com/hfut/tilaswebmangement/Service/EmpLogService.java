package com.hfut.tilaswebmangement.Service;
import com.hfut.tilaswebmangement.pojo.EmpLog;
import org.springframework.transaction.annotation.Transactional;

public interface EmpLogService {
    public void insertLog(EmpLog empLog);

}
