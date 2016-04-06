package net.thiki.game.audit;

public interface Appender {

    void saveOperation(String userId, String message);

}
