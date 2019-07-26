package bean;

public class Order {
    private String roomName;
    private String seatNum;
    private String time;
    private String duration;
    private String status;
    private String orderTime;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "roomName='" + roomName + '\'' +
                ", seatNum='" + seatNum + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                ", status='" + status + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
