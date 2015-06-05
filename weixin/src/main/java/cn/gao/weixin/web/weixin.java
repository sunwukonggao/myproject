package cn.gao.weixin.web;

import cn.gao.weixin.domain.VerifyModel;
import cn.gao.weixin.tools.WXBizMsgCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by gaojc on 2015/6/5.
 */
@Controller
public class weixin {
    private String token = "pamtest";
    private String appId = "wxb11529c136998cb6";
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(VerifyModel verifyModel) throws Exception {

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, verifyModel.getEncodingAesKey(), appId);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(request.getAttribute("HTTP_RAW_POST_DATA").toString());
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
        NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

        String encrypt = nodelist1.item(0).getTextContent();
        String msgSignature = nodelist2.item(0).getTextContent();

        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);
        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg(msgSignature, verifyModel.getTimestamp(), verifyModel.getNonce(), fromXML);
        return result2;

    }

}
