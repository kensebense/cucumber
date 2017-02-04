package org.kense.myproject.support;

import org.kense.dto.OrderDTO;

import javax.inject.Inject;
import java.util.List;

public class OrdersRestHelper {

    @Inject
    private OrdersTestSession ordersTestSession;

    public void login(String userName) {
        UserCredentials credentials = new UserCredentials(userName, "abc");
        ordersTestSession.setUserCredentials(credentials);
    }

    public OrderDTO getOrder() {
        return ordersTestSession.getOrderDTO();
    }

    public List<OrderDTO> requestOrders() {

        return null;
    }

    public void requestDraftOrder() {

        ordersTestSession.setOrder(new OrderDTO());
    }

    public void addPartToOrder(Long partNumber) {
        addPartToOrder(partNumber, null);
    }

    public void addPartToOrder(Long partNumber, Long quantity) {

        if (quantity == null) {
            quantity = 1L;
        }

        // Get hold of the draft order in test session
        OrderDTO orderDTO = ordersTestSession.getOrderDTO();
        orderDTO.getPartIds().add(partNumber);

    }
}
