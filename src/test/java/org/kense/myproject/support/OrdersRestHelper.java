package org.kense.myproject.support;

import org.kense.dto.OrderDTO;
import org.kense.myproject.automation.rest.RestHandler;
import org.kense.myproject.automation.selenium.SeleniumHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

public class OrdersRestHelper {


    @Inject
    private LoggerHelper loggerHelper;

    private RestHandler<OrderDTO> restHandler;

    private OrderDTO orderDTO;

    public void login(UserCredentials credentials) {

        String payload = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", credentials.getUsername(), credentials.getPassword());

        restHandler.performPostRequest(getResourceURI(), payload, null);

        loggerHelper.getLogger().info(String.format("Logged in with username %s", credentials.getUsername()));
    }

    public OrderDTO getOrder() {
        restHandler.performGetRequest(getResourceURI(), null, null);
        return this.orderDTO;
    }

    public List<OrderDTO> requestOrders() {
        return null;
    }

    public OrderDTO requestDraftOrder() {

        this.orderDTO = restHandler.performGetRequest(getResourceURI(), null, null);

        // cheating for demo purpose here
        this.orderDTO = new OrderDTO();

        return getOrder();
    }

    private String getResourceURI() {
        return null;
    }

    public void addPartToOrder(Long partNumber) {
        addPartToOrder(partNumber, null);
    }

    public void addPartToOrder(Long partNumber, Long quantity) {

        if (quantity == null) {
            quantity = 1L;
        }

        String payload = String.format("{'partnumber': '%s', 'quantity': %d}", partNumber, quantity);

        restHandler.performPutRequest(getResourceURI(), payload, null);

        // cheating for demo purpose here
        orderDTO.getPartIds().add(partNumber);

        loggerHelper.getLogger().info(String.format("Added part %s to order", partNumber));
    }

    @PostConstruct
    private void setupRestHandler() {
        this.restHandler = new RestHandler<>();
    }
}
