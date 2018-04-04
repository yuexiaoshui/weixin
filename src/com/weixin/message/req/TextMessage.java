package com.weixin.message.req;
/**
* @Classname: TextMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:15:49
* @Todo:  文本消息 
* @version V1.0 
**/
public class TextMessage extends BaseMessage {
	 // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
