package com.weixin.message.req;
/**
* @Classname: LinkMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:19:12
* @Todo: 链接消息
* @version V1.0 
**/
public class LinkMessage extends BaseMessage {
	// 消息标题  
    private String Title;  
    // 消息描述  
    private String Description;  
    // 消息链接  
    private String Url;  
  
    public String getTitle() {  
        return Title;  
    }  
  
    public void setTitle(String title) {  
        Title = title;  
    }  
  
    public String getDescription() {  
        return Description;  
    }  
  
    public void setDescription(String description) {  
        Description = description;  
    }  
  
    public String getUrl() {  
        return Url;  
    }  
  
    public void setUrl(String url) {  
        Url = url;  
    }  
}
