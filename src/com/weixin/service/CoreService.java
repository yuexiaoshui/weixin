package com.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.weixin.message.resp.Article;
import com.weixin.message.resp.NewsMessage;
import com.weixin.message.resp.TextMessage;
import com.weixin.util.MessageUtil;

/**
* @Classname: CoreService.java
* @author: yue
* @Time: 2018年3月30日--下午3:22:02
* @Todo: TODO
* @version V1.0 
**/
public class CoreService {
	/** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXML(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            List<Article> articleList = new ArrayList<Article>();
       	 	// 创建图文消息  
            NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            newsMessage.setFuncFlag(0); 
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	// 判断用户发送的是否是单个QQ表情  
            	String content = requestMap.get("Content");  
            	 
                if(isQqFace(content)) {  
                    // 回复文本消息   
                    // 用户发什么QQ表情，就返回什么QQ表情  
                	respContent = content;
                	textMessage.setContent(respContent);  
                    respMessage = MessageUtil.textMessageToXML(textMessage); 
                } // 单图文消息  
                else if ("1".equals(content)) {  
                        Article article = new Article();  
                        article.setTitle("微信公众帐号开发教程Java版");  
                        article.setDescription("柳峰，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");  
                        article.setPicUrl("http://img1.imgtn.bdimg.com/it/u=3319705027,4149442378&fm=27&gp=0.jpg");  
                        article.setUrl("http://blog.csdn.net/lyq8479");  
                        articleList.add(article);  
                        // 设置图文消息个数  
                        newsMessage.setArticleCount(articleList.size());  
                        // 设置图文消息包含的图文集合  
                        newsMessage.setArticles(articleList);  
                        // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    }  
                    // 单图文消息---不含图片  
                    else if ("2".equals(content)) {  
                        Article article = new Article();  
                        article.setTitle("微信公众帐号开发教程Java版");  
                        // 图文消息中可以使用QQ表情、符号表情  
                        article.setDescription("柳峰，80后，" + emoji(0x1F6B9)  
                                + "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");  
                        // 将图片置为空  
                        article.setPicUrl("");  
                        article.setUrl("http://blog.csdn.net/lyq8479");  
                        articleList.add(article);  
                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    }  
                    // 多图文消息  
                    else if ("3".equals(content)) {  
                        Article article1 = new Article();  
                        article1.setTitle("微信公众帐号开发教程\n引言");  
                        article1.setDescription("");  
                        article1.setPicUrl("http://img.zcool.cn/community/010a1b554c01d1000001bf72a68b37.jpg@1280w_1l_2o_100sh.png");  
                        article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
      
                        Article article2 = new Article();  
                        article2.setTitle("第2篇\n微信公众帐号的类型");  
                        article2.setDescription("");  
                        article2.setPicUrl("http://img.zcool.cn/community/01626057ff29a1a84a0d304f3b5991.jpg@1280w_1l_2o_100sh.jpg");  
                        article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
      
                        Article article3 = new Article();  
                        article3.setTitle("第3篇\n开发模式启用及接口配置");  
                        article3.setDescription("");  
                        article3.setPicUrl("http://img3.duitang.com/uploads/item/201503/14/20150314212812_kCLmy.thumb.700_0.jpeg");  
                        article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
      
                        articleList.add(article1);  
                        articleList.add(article2);  
                        articleList.add(article3);  
                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    }  
                    // 多图文消息---首条消息不含图片  
                    else if ("4".equals(content)) {  
                        Article article1 = new Article();  
                        article1.setTitle("微信公众帐号开发教程Java版");  
                        article1.setDescription("");  
                        // 将图片置为空  
                        article1.setPicUrl("");  
                        article1.setUrl("http://blog.csdn.net/lyq8479");  
      
                        Article article2 = new Article();  
                        article2.setTitle("第4篇\n消息及消息处理工具的封装");  
                        article2.setDescription("");  
                        article2.setPicUrl("http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg");  
                        article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
      
                        Article article3 = new Article();  
                        article3.setTitle("第5篇\n各种消息的接收与响应");  
                        article3.setDescription("");  
                        article3.setPicUrl("http://img1.imgtn.bdimg.com/it/u=1473119751,3573545354&fm=27&gp=0.jpg");  
                        article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
      
                        Article article4 = new Article();  
                        article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");  
                        article4.setDescription("");  
                        article4.setPicUrl("http://scimg.jb51.net/allimg/161205/106-161205161K5161.jpg");  
                        article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");  
      
                        articleList.add(article1);  
                        articleList.add(article2);  
                        articleList.add(article3);  
                        articleList.add(article4);  
                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    }  
                    // 多图文消息---最后一条消息不含图片  
                    else if ("5".equals(content)) {  
                        Article article1 = new Article();  
                        article1.setTitle("第7篇\n文本消息中换行符的使用");  
                        article1.setDescription("");  
                        article1.setPicUrl("http://img.zcool.cn/community/018b07595dc532a8012193a33d91dd.jpg@2o.jpg");  
                        article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
      
                        Article article2 = new Article();  
                        article2.setTitle("第8篇\n文本消息中使用网页超链接");  
                        article2.setDescription("");  
                        article2.setPicUrl("http://img3.imgtn.bdimg.com/it/u=406287140,2358534841&fm=27&gp=0.jpg");  
                        article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
      
                        Article article3 = new Article();  
                        article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");  
                        article3.setDescription("");  
                        // 将图片置为空  
                        article3.setPicUrl("");  
                        article3.setUrl("http://blog.csdn.net/lyq8479");  
      
                        articleList.add(article1);  
                        articleList.add(article2);  
                        articleList.add(article3);  
                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    } 
                else {
                	respContent = "<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>";
                	textMessage.setContent(respContent);  
                    respMessage = MessageUtil.textMessageToXML(textMessage); 
				}     
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXML(textMessage); 
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXML(textMessage); 
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXML(textMessage); 
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXML(textMessage); 
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";
                    textMessage.setContent(respContent);  
                    respMessage = MessageUtil.textMessageToXML(textMessage); 
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                	// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("开源中国");
						article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
						article.setPicUrl("");
						article.setUrl("http://m.oschina.net");
						articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage); 
					} else if (eventKey.equals("iteye")) {
						textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
						respMessage = MessageUtil.textMessageToXML(textMessage);
					}
                }  
            }  
   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
    /** 
     * 判断是否是QQ表情 
     *  
     * @param content 
     * @return 
     */  
    public static boolean isQqFace(String content) {  
        boolean result = false;  
      
        // 判断QQ表情的正则表达式  
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
        Pattern p = Pattern.compile(qqfaceRegex);  
        Matcher m = p.matcher(content);  
        if (m.matches()) {  
            result = true;  
        }  
        return result;  
    } 
    /** 
     * emoji表情转换(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    } 
}
