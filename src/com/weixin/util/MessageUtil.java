package com.weixin.util;
/**
* @Classname: MessageUtil.java
* @author: yue
* @Time: 2018年3月30日--下午2:32:18
* @Todo: 消息工具类 
* @version V1.0 
**/
/**
 * @author yuexi
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.weixin.message.resp.Article;
import com.weixin.message.resp.MusicMessage;
import com.weixin.message.resp.NewsMessage;
import com.weixin.message.resp.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class MessageUtil {
	//返回消息类型：文本 
	public static final String RESP_MESSAGE_TYPE_TEXT ="text";
	//返回消息类型：音乐 
	public static final String RESP_MESSAGE_TYPE_MUSIC ="music";
	//返回消息类型：图文 
	public static final String RESP_MESSAGE_TYPE_NEWS ="news";
	//请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT ="text";
	//请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE ="image";
	//请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LINK ="link";
	//请求消息类型：位置
	public static final String REQ_MESSAGE_TYPE_LOCATION ="location";
	//请求消息类型：语音
	public static final String REQ_MESSAGE_TYPE_VOICE ="voice";
	//请求消息类型：推送
	public static final String REQ_MESSAGE_TYPE_EVENT ="event";
	//事件类型：subscribe(订阅) 
	public static final String EVENT_TYPE_SUBSCRIBE ="subscribe";
	//事件类型：unsubscribe(取消订阅) 
	public static final String EVENT_TYPE_UNSUBSCRIBE ="unsubscribe";
	//事件类型：CLICK(自定义菜单点击事件)
	public static final String EVENT_TYPE_CLICK ="click";
	
	/**  
	* @Title: parseXML  
	* @Description: 解析微信发来的请求（XML） 
	* @param request
	* @return Map<String, String>
	* @throws IOException, DocumentException
	*/ 
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXML(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		InputStream is = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		// 得到xml根元素 
		Element root = doc.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elements = root.elements();
		// 遍历所有子节点  
		for(Element e:elements) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源  
		is.close();
		is = null;
		return map;	
	}
	
	/**  
	* @Title: textMassageToXML  
	* @Description: 文本消息对象转换成xml 
	* @param textmassage 文本消息
	* @return String(XML)
	*/ 
	public static String textMessageToXML(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);
	}
	/**  
	* @Title: musicMessageToXML  
	* @Description: 音乐消息对象转换成xml
	* @param musicMessage 音乐消息
	* @return String(XML)
	*/ 
	public static String musicMessageToXML(MusicMessage musicMessage) {  
		xstream.alias("xml", musicMessage.getClass());  
	    return xstream.toXML(musicMessage);  
	} 
	
	/**  
	* @Title: newsMessageToXml  
	* @Description: 图文消息对象转换成xml 
	* @param newsMessage 图文消息
	* @return String(XML)
	*/ 
	public static String newsMessageToXml(NewsMessage newsMessage) {  
	    xstream.alias("xml", newsMessage.getClass());  
	    xstream.alias("item", new Article().getClass());  
	    return xstream.toXML(newsMessage);  
	}  
		
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static XStream xstream = new XStream(
			new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
	

}
