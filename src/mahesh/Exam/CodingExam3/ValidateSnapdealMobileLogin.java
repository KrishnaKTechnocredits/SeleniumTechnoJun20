package mahesh.Exam.CodingExam3;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import mahesh.Exam.CodingExam3.CommonValidations;

public class ValidateSnapdealMobileLogin extends CommonValidations{
	
	@Test
	public void loginWithMobile() {
		login("9167755341", "Password@1");
	}
}
