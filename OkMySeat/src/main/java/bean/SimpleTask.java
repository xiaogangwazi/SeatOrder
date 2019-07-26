package bean;

import commonUtils.Response;
import core.Order.OrderAction;
/**；
 * 简单的任务对象
 */
public class SimpleTask implements Runnable ,Cloneable{
    private OrderForm orderForm;
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

/*    @Override
    public Object clone() throws CloneNotSupportedException {
        SimpleTask simpleTask =(SimpleTask) super.clone();
        simpleTask.setOrderForm((OrderForm) orderForm.clone());
        return simpleTask;
    }*/

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }



    @Override
    public void run() {
        Response response = OrderAction.orderSeat(orderForm, cookie);
    }

    @Override
    public String toString() {
        return "Task{" +
                "orderForm=" + orderForm +
                ", cookie='" + orderForm.getCookie() + '\'' +
                '}';
    }
}
