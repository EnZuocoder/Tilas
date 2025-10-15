package com.hfut.tilaswebmangement;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;   //定义了一套接口
import org.slf4j.LoggerFactory;
public class LogTest {
    public static final Logger logger= LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){
        //日志级别:
/*
trace<debug<info<warn<error
 */
        logger.debug("开始记录日志:");
        logger.info("加载中...");
        logger.info("加载中...");
        logger.info("加载中...");
        logger.info("加载成功!");
        logger.debug("结束!");
    }
}
