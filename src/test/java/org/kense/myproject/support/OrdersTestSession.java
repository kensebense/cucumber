package org.kense.myproject.support;

import org.kense.dto.OrderDTO;

public class OrdersTestSession extends TestSession {

    private OrderDTO orderDTO;

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

}
