package com.weixin.message.resp;
/**
* @Classname: MusicMessage.java
* @author: yue
* @Time: 2018年3月30日--下午2:23:53
* @Todo: 音乐消息 
* @version V1.0 
**/
public class MusicMessage extends BaseMessage {
	// 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}
