package app.zuliangwang.zuliangnews.model;

import java.util.List;

/**
 * Created by zuliangwang on 15/7/21.
 */
public class PageBean {
    public String allNum;
    public String allPages;
    public List<Contentlist> contentlist;
    public String currentPage;
    public String maxResult;

    public List<Contentlist> getContentlist() {
        return contentlist;
    }
}
