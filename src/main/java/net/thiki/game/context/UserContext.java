package net.thiki.game.context;
/**
 * 分线程容器
 * @author Zhu Zeng
 *
 */
public class UserContext {

    private ThreadLocal<String> userId;
    
    public UserContext() {
        userId = new ThreadLocal<>();
    }
    
    /**
     * 
     * @param userId
     */
    public void setUserId(String userId) {
       this.userId.set(userId);
    }

    public String getUserId() {
        return userId.get();
    }

}
