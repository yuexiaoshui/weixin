package com.weixin.message.resp;
/**
* @Classname: TextMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:23:09
* @Todo: 文本消息 
* @version V1.0 
**/
public class TextMessage extends BaseMessage {
	// 回复的消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
