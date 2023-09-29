package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.Base;

public class ProductAddPage extends Base {

	public void user_add_a_product() {
		WebElement AddToCartBtn = driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
		AddToCartBtn.click();
	}

	public void validate_item_add_to_sucessfully() {
		WebElement ValidateCardItem = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		validateText(ValidateCardItem, "1");
	}

}
