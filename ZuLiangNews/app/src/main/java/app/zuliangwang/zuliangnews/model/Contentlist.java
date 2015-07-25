package app.zuliangwang.zuliangnews.model;

import java.util.List;

/**
 * Created by zuliangwang on 15/7/20.
 */
public class Contentlist {
    public String channelId;
    public String channelName;
    public String desc;
    public String link;
    public String pubDate;
    public String source;

    public String getTitle() {
        return title;
    }

    public List<ImageUrl> getImageUrls() {
        return imageurls;
    }

    public String getLink() {
        return link;
    }

    public String getDesc() {
        return desc;
    }

    public String title;

    public List<ImageUrl> imageurls;
}
