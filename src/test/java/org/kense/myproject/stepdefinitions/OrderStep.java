package org.kense.myproject.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kense.dto.OrderDTO;
import org.kense.myproject.support.OrdersRestHelper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Singleton
public class OrderStep {

    @Inject
    private OrdersRestHelper restHelper;

    @Given("^I am logged in as \"([^\"]*)\"$")
    public void iAmLoggedInAs(String userName) throws Throwable {
        restHelper.login(userName);
    }

    @Given("^In my shopping cart I have an order in status draft$")
    public void inMyShoppingCartIHaveAnOrderInStatusDraft() throws Throwable {
        restHelper.requestDraftOrder();
    }

    @When("^I add these parts to the order and saves it$")
    public void iAddThesePartsToTheOrderAndSavesIt(List<Long> partsList) throws Exception {

        partsList.forEach(restHelper::addPartToOrder);
    }

    @Then("^the order should be displayed having (\\d+) orderlines with the parts$")
    public void theOrderShouldBeDisplayedHavingOrderlinesWithTheParts(int numberOfLines, List<Long> expectedPartList) throws Throwable {
        OrderDTO orderDTO = restHelper.getOrder();
        assertThat(orderDTO.getPartIds().size(), is(numberOfLines));
        assertThat(orderDTO.getPartIds(), is(expectedPartList));
    }
}
