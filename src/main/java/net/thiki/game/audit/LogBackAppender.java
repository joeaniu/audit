package net.thiki.game.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackAppender implements Appender{

    public void saveOperation(String userId, String message) {
       Logger logger = LoggerFactory.getLogger(userId);
    }
  
}
