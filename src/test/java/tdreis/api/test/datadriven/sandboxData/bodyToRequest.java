package tdreis.api.test.datadriven.sandboxData;

import org.testng.annotations.DataProvider;
import tdreis.api.test.supporttests.sandboxsupport.keysResponseSandbox;
import tdreis.api.test.supporttests.sandboxsupport.messagesOfRequest;

public class bodyToRequest {

    private static String[][] validUser = dataToRequest.body;

    @DataProvider(name = "setUser")
    public static Object[][] setUser() {
        return new Object[][]{
                //Valid user status code 200
                {validUser, messagesOfRequest.messageSuccessUserCreated, 200},
        };
    }

    @DataProvider(name = "deleteUser")
    public static Object[][] deleteUser() {
        return new Object[][]{
                //Delete user status code 200
                {messagesOfRequest.messageSuccessUserDeleted, 200},
        };
    }

    @DataProvider(name = "failAuth")
    public static Object[][] failAuth() {
        return new Object[][]{
                //Invalid auth status code 401
                {validUser, messagesOfRequest.messageErrorAuth, 401},
        };
    }

    @DataProvider(name = "invalidUser")
    public static Object[][] invalidUser() {
        return new Object[][]{
                //Invalid user status code 404
                {messagesOfRequest.messageErrorUserNotFound, 404},
        };
    }

    @DataProvider(name = "emptyKeys")
    public static Object[][] emptyKeys() {
        return new Object[][]{
                //Empty keys status code 400
                {dataToRequest.invalidBody, messagesOfRequest.messageErrorEmptyKey + keysResponseSandbox.allKeys, 400},
        };
    }
}
