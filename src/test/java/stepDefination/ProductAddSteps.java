package stepDefination;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ProductAddPage;

public class ProductAddSteps {
	ProductAddPage prod = new ProductAddPage();

	@When("user add a product")
	public void user_add_a_product() {
prod.user_add_a_product();
	}

	@Then("Validate item add to sucessfully")
	public void validate_item_add_to_sucessfully() {
prod.validate_item_add_to_sucessfully();
	}

}
