package org.kense.myproject.support;

import org.kense.dto.OrderDTO;

import javax.inject.Inject;
import java.util.List;

public class OrdersRestHelper {


    @Inject
    private LoggerHelper loggerHelper;

    private OrderDTO orderDTO;

    public void login(UserCredentials credentials) {

        loggerHelper.getLogger().info(String.format("Logged in with username %s", credentials.getUsername()));
    }

    public OrderDTO getOrder() {
        return this.orderDTO;
    }

    public List<OrderDTO> requestOrders() {

        return null;
    }

    public OrderDTO requestDraftOrder() {

        this.orderDTO = new OrderDTO();
        return getOrder();
    }

    public void addPartToOrder(Long partNumber) {
        addPartToOrder(partNumber, null);
    }

    public void addPartToOrder(Long partNumber, Long quantity) {

        if (quantity == null) {
            quantity = 1L;
        }

        orderDTO.getPartIds().add(partNumber);

        loggerHelper.getLogger().info(String.format("Added part %s to order", partNumber));
    }
}
