package org.kense.myproject.stepdefinitions;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kense.dto.OrderDTO;
import org.kense.myproject.support.OrdersRestHelper;
import org.kense.myproject.support.OrdersTestSession;
import org.kense.myproject.support.UserCredentials;
import org.kense.myproject.transforms.RoleToUserCredentialsConverter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Singleton
public class OrderStep {

    @Inject
    private OrdersRestHelper restHelper;

    @Inject
    private OrdersTestSession ordersTestSession;

    @Given("^I am logged in as \"([^\"]*)\"$")
    public void iAmLoggedInAs(@Transform(RoleToUserCredentialsConverter.class) UserCredentials credentials) throws Throwable {
        ordersTestSession.setUserCredentials(credentials);
        restHelper.login(credentials);
    }

    @Given("^In my shopping cart I have an order in status draft$")
    public void inMyShoppingCartIHaveAnOrderInStatusDraft() throws Throwable {
        ordersTestSession.setOrder(restHelper.requestDraftOrder());
    }

    @When("^I add these parts to the order and saves it$")
    public void iAddThesePartsToTheOrderAndSavesIt(List<Long> partsList) throws Exception {

        partsList.forEach(restHelper::addPartToOrder);
        ordersTestSession.setOrder(restHelper.getOrder());
    }

    @Then("^the order should be displayed having (\\d+) orderlines with the parts$")
    public void theOrderShouldBeDisplayedHavingOrderlinesWithTheParts(int numberOfLines, List<Long> expectedPartList) throws Throwable {
        OrderDTO orderDTO = ordersTestSession.getOrderDTO();
        assertThat(orderDTO.getPartIds().size(), is(numberOfLines));
        assertThat(orderDTO.getPartIds(), is(expectedPartList));
    }
}
