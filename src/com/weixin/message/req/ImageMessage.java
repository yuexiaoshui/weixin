package com.weixin.message.req;
/**
* @Classname: ImageMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:16:52
* @Todo: 图片消息
* @version V1.0 
**/
public class ImageMessage extends BaseMessage {
	// 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    } 
}
