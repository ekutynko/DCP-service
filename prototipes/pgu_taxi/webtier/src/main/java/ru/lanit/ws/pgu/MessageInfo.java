/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lanit.ws.pgu;

import ru.lanit.ws.pgu.beans.ws.MessageType;

/**
 *
 * @author user06
 */
public class MessageInfo {
    
    private MessageType srcMessage;
    
    private String messageId;

    public MessageType getSrcMessage() {
        return srcMessage;
    }

    public void setSrcMessage(MessageType srcMessage) {
        this.srcMessage = srcMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
}
