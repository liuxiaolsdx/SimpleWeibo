package com.weibo.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class WeiboLogger {
//	private static Logger logger = Logger.getLogger(WeiboLogger.class);
	
	static {
		PropertyConfigurator.configure(WeiboLogger.class.getResourceAsStream("log4j.properties"));
	}

    public static void debug(Object msg) {  
        StackTraceElement stack[] = (new Throwable()).getStackTrace();  
  
        Logger logger = Logger.getLogger(stack[1].getClassName());  
        logger.log(WeiboLogger.class.getName(), Level.DEBUG, msg, null);  
    }  

    public static void info(Object msg) {  
        StackTraceElement stack[] = (new Throwable()).getStackTrace();  
  
        Logger logger = Logger.getLogger(stack[1].getClassName());  
        logger.log(WeiboLogger.class.getName(), Level.INFO, msg, null);  
    }  
    
    public static void warn(Object msg) {  
        StackTraceElement stack[] = (new Throwable()).getStackTrace();  
  
        Logger logger = Logger.getLogger(stack[1].getClassName());  
        logger.log(WeiboLogger.class.getName(), Level.WARN, msg, null);  
    }  

    public static void error(Object msg) {  
        StackTraceElement stack[] = (new Throwable()).getStackTrace();  
  
        Logger logger = Logger.getLogger(stack[1].getClassName());  
        logger.log(WeiboLogger.class.getName(), Level.ERROR, msg, null);  
    }  
    
    public static void exception(Exception e) {  
        StackTraceElement stack[] = (new Throwable()).getStackTrace();  
  
        Logger logger = Logger.getLogger(stack[1].getClassName());  
        logger.log(WeiboLogger.class.getName(), Level.ERROR, ExceptionUtil.toString(e), null);  
    }  

    public static void main(String[] args){
    	WeiboLogger.debug("This is debug.");
    	WeiboLogger.info("This is info.");
    	WeiboLogger.warn("This is warn.");
    	WeiboLogger.error("This is error.");
    	WeiboLogger.exception(new Exception("This is exception."));
    }
}
