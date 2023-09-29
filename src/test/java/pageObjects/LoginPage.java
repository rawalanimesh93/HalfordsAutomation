//here we will define all webelements and methods

package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.Base;

public class LoginPage extends Base {

	public void user_enter_and(String uname, String pass) {
		WebElement username = driver.findElement(By.cssSelector("#user-name"));
		cleanAndEnterText(username, uname);

		WebElement password = driver.findElement(By.cssSelector("#password"));
		cleanAndEnterText(password, pass);
				}
	
	public void user_click_on_login_button() {
		WebElement logbtn = driver.findElement(By.cssSelector("#login-button"));
		logbtn.click();
	}
	
	public void validate_user_logged_in_sucessfully() {
		WebElement tittle = driver.findElement(By.xpath("//span[@class='title']"));
		validateText(tittle, "Products");
	}

	public void validate_login_error_message() {
		WebElement tittle = driver.findElement(By.xpath("//h3[@data-test='error']"));
		validateText(tittle, "Epic sadface: Username and password do not match any user in this service");
	}
	
	public void validate_correct_username_and_invalid_password() {
		WebElement tittle = driver.findElement(By.xpath("//h3[@data-test='error']"));
		validateText(tittle, "Epic sadface: Username and password do not match any user in this service");
	}
	
	public void validate_correct_username_and_blank_password() {
		WebElement tittle = driver.findElement(By.xpath("//h3[@data-test='error']"));
		validateText(tittle, "Epic sadface: Password is required");
	}
}