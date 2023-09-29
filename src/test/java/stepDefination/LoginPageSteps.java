package stepDefination;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

public class LoginPageSteps {

	LoginPage login = new LoginPage();

	@When("user enter {string} and {string}")
	public void user_enter_and(String uname, String pass) {
		login.user_enter_and(uname, pass);
	}

	@When("user click on login button")
	public void user_click_on_login_button() {
		login.user_click_on_login_button();
	}

	@Then("Validate user logged in sucessfully")
	public void validate_user_logged_in_sucessfully() {
		login.validate_user_logged_in_sucessfully();
	}

	@Then("Validate login error message")
	public void validate_login_error_message() {
	  login.validate_login_error_message();
	}

	@Then("Validate correct username and invalid password")
	public void validate_correct_username_and_invalid_password() {
	login.validate_correct_username_and_invalid_password();
	}

	@Then("Validate correct username and blank password")
	public void validate_correct_username_and_blank_password() {
	login.validate_correct_username_and_blank_password();
	}

	
}
