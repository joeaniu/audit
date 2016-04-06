package net.thiki.game.context;
/**
 * 分线程容器
 * @author Zhu Zeng
 *
 */
public class UserContext {

    private String userId;

    /**
     * 
     * @param userId
     */
    public void setUserId(String userId) {
       this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
