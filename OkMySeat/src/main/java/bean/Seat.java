package bean;

public class Seat {
    private String category_id;
    private String group_id;
    private String  id;
    private String h;
    private String locker;
    private String recommend;
    private String state;
    private String title;
    private String w;
    private String x;
    private String y;
    private String gender;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getLocker() {
        return locker;
    }

    public void setLocker(String locker) {
        this.locker = locker;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "category_id='" + category_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", id='" + id + '\'' +
                ", h='" + h + '\'' +
                ", locker='" + locker + '\'' +
                ", recommend='" + recommend + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", w='" + w + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
