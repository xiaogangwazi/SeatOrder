package bean;


import com.alibaba.fastjson.JSON;

public class SearchForm {
    private String beginTime;
    private String duration;
    private String num;
    private String  category_id;
    private String content_id;
    public SearchForm(String beginTime,String duration,String num,String category_id,String content_id){
        this.content_id=content_id;
        this.category_id=category_id;
        this.beginTime=beginTime;
        this.duration=duration;
        this.num=num;
    }


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        Long l=Long.valueOf(beginTime);
        return
                "beginTime=" + l +
                "&duration=" + duration +
                "&num=" + num +
                "&space_category%5Bcategory_id%5D=" + category_id +
                "&space_category%5Bcontent_id%5D=" + content_id ;
    }
}
