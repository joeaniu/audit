package net.thiki.game.audit;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import net.jodah.concurrentunit.Waiter;
import net.thiki.game.audit.Appender;
import net.thiki.game.audit.UserAudit;
import net.thiki.game.context.UserContext;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AuditTest {

    private UserAudit userLog;
    private UserContext userContext;
    private Appender appender;
    
    private Executor pool = Executors.newCachedThreadPool();
    
    @Before
    public void setUp(){
        userLog = new UserAudit();
        userContext = new UserContext();
//        appender = new LogBackAppender();
        appender = mock(Appender.class);
        
        userLog.setUserContext(userContext);
        userLog.setAppender(appender);
    }
    
    @Test
    public void test1(){
        // 假设是用户zhangsan111
        String userId = "zhangsan111";
        userContext.setUserId(userId);
        
        
        // 假设是进攻某个地块（12，32）
        String message = "{\"operation\":10011, \"coord\":1232}";
        userLog.operation(message);
        
        verify(appender).saveOperation(userId, message);
        
    }
    
    @Test
    public void example4LoggingUserOperation() throws TimeoutException{
        // pointcut @ command
        // parameters: IMessage, toString
        // 接口形式？内容？
        
        final Waiter waiter = new Waiter();
       
        pool.execute(new Runnable() {
            @Override
            public void run() {
                // 假设是用户zhangsan111
                String userId = "zhangsan111";
                userContext.setUserId(userId);
               
                //  let other thread to run
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    fail();
                } 

                // 假设是进攻某个地块（12，32）
                String message = "{\"operation\":10011, \"coord\":1232}";
                userLog.operation(message);  
                
                verify(appender).saveOperation(userId, message);
                
                waiter.resume();
            }
        }); 
        
        pool.execute(new Runnable() {
            @Override
            public void run() {
                // 假设是用户lisi
                String userId ="lisi";
                userContext.setUserId(userId);
                
                // 假设是进攻某个地块（12，32）
                String message = "{\"operation\":10011, \"coord\":1232}";
                userLog.operation(message);
                verify(appender).saveOperation(userId, message);
                
                waiter.resume();
            }
        }); 
        
        waiter.await(2000, 2);
       
    }
}
