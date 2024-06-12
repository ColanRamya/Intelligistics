package test.login;

import Pages.LoginPage.UserLogin;
import org.testng.annotations.Test;
import test.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void login()
    {
        UserLogin.signInWith();
    }

}
