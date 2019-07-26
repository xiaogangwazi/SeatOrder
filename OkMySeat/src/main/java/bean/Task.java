package bean;

import commonUtils.Response;
import core.Order.OrderAction;
import core.login.LoginAction;

import java.awt.image.renderable.RenderableImage;
import java.util.Map;

/**
 * r任务对象，包含用户的cookie和申请信息
 */
public class Task implements Runnable ,Cloneable{
    private OrderForm orderForm;
    private String cookie;
    private User user;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Task clone =(Task) super.clone();
        clone.orderForm=(OrderForm) orderForm.clone();
        clone.user=(User)user.clone();
        return clone;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        Response response = OrderAction.orderSeat(orderForm, cookie);
    }

    @Override
    public String toString() {
        return "Task{" +
                "orderForm=" + orderForm +
                ", cookie='" + cookie + '\'' +
                ", user=" + user +
                '}';
    }
}
