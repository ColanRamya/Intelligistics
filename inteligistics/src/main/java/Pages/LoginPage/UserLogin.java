package Pages.LoginPage;
import BaseSteps.BaseClass;
import org.testng.Assert;

public class UserLogin extends BaseClass {

public static void signInWith(){

setText("login", "userNameInputBox" ,getDataFromPropertyFile("file","username") );
setText("login", "passwordInputBox" ,getDataFromPropertyFile("file","password") );
clickControl( "login" , "signInButton");
Assert.assertTrue(BaseClass.isControlDispalyed("homepage", "logoutButton"),"Login was unSuccessfull");

    }
}
