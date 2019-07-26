package bean;

public class OrderForm implements Cloneable{

    private String beginTime;
    private String duration;
    private String seats;
    private String seatBookers;
    private String name;
    private String content_id;
    private String password;
    private String cookie;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getSeatBookers() {
        return seatBookers;
    }

    public void setSeatBookers(String seatBookers) {
        this.seatBookers = seatBookers;
    }

    @Override
    public String toString() {
        return
                "beginTime=" + beginTime +
                "&duration=" + duration +
                "&seats%5B0%5D=" + seats +
                "&seatBookers%5B0%5D=" + seatBookers ;
    }
}
