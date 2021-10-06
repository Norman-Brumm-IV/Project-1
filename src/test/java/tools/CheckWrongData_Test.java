package tools;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import models.Users;

public class CheckWrongData_Test {
	private String rsbf = "result should be false";
	private String rsbt = "result should be true";
	
	@Mock
	Users user;

	@Test
	public void checkIsEmpty() {
		Assert.assertEquals(false, CheckWrongData.isEmptyString("1","22","333","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","333","444", null));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","333",""));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","","333","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("","22","333","444"));
	}
	
	@Test
	public void checkUser() {
		String cPassword = "password";
		String wPassword = "wrongPassword";
		String ePassword = "";
		String nPassword;
		
		Assert.assertEquals(rsbt, true, CheckWrongData.checkUsersPassword(null, rsbf));
	}
	
	@Test
	public void checksCanParse() {
		Assert.assertEquals(rsbt, true, CheckWrongData.canParseAsDouble("123"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsDouble("asf"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsDouble("25a4"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsDouble("123", null));
	}
}
