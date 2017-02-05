package org.kense.myproject.support;

import org.kense.dto.OrderDTO;

public class OrdersTestSession extends TestSession {

    private OrderDTO orderDTO;

    private String cheeseResult;

    public void setOrder(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public String getCheeseResult() {
        return cheeseResult;
    }

    public void setCheeseResult(String cheeseResult) {
        this.cheeseResult = cheeseResult;
    }
}
