package app.zuliangwang.zuliangnews.api;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zuliangwang on 15/7/20.
 */
public class NewsApi {
    public static final String baseNewsUrl = "http://route.showapi.com/109-35";
    public static final String appid = "3770";
    public static final String secret = "65d24de8874d4117ac2b33533293ba2c";
    public static final String completeNewsUrl = baseNewsUrl+","+appid+","+secret+",";
//    public static String params = "channelId=&channelName=&page=1&showapi_appid=3770&showapi_timestamp=20150720190255&title=足球&showapi_sign=65d24de8874d4117ac2b33533293ba2c";
//    public static final String testApi = "http://fanyi.youdao.com/openapi.do?keyfrom=bb8bbbbbbb3&key=1547316023&type=data&doctype=json&version=1.1&q=";
//    public static final String showapi_time = "20150721101855";

    public static String getiCurTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        String string = format.format(curDate);
      return string;
    };
}
