package net.thiki.game.audit;

import net.thiki.game.context.UserContext;

/**
 * 用户log
 * @author Zhu Zeng
 *
 */
public class UserAudit {
    
    private Appender appender;
    private UserContext userContext;

    public void setAppender(Appender recorder) {
        this.appender = recorder;
    }
    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }


    /**
     * 
     * @param message
     */
    public void operation(String message) {
       appender.saveOperation(userContext.getUserId(), message); 
    }

}
