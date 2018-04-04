package com.weixin.message.req;
/**
* @Classname: VoiceMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:19:53
* @Todo: 语音消息
* @version V1.0 
**/
public class VoiceMessage extends BaseMessage {
	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}
