package mahesh.Exam.CodingExam3;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import mahesh.Exam.CodingExam3.CommonValidations;

public class ValidateSnapdealEmailLogin extends CommonValidations {

	@Test
	public void loginWithEmail() {
		login("mkumavat31@gmail.com", "Password@1");
	}
}
