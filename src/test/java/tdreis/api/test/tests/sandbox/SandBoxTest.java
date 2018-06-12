package tdreis.api.test.tests.sandbox;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tdreis.api.test.common.Property;
import tdreis.api.test.datadriven.sandboxData.bodyToRequest;
import tdreis.api.test.steps.sandbox.sandboxStep;
import tdreis.api.test.supporttests.sandboxsupport.keysResponseSandbox;
import tdreis.api.test.supporttests.sandboxsupport.validUriSandbox;
import tdreis.api.test.tests.Base;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.baseURI;

public class SandBoxTest {

    private String[][] userBody;

    @Parameters({"url_api_sandbox", "environment"})
    public SandBoxTest(@Optional String url, @Optional String environment) {
        if (url == null) {
            url = Property.SITE_ADDRESS_SANDBOX;
        }

        if (environment == null) {
            environment = Property.ENVIRONMENT_TEST;
        }

        Base.environmentConf = environment;
        baseURI = url;
    }

    @Test(dataProvider = "setUser", dataProviderClass = bodyToRequest.class)
    public void testSetUser(String[][] body, String message, int statusCode) {
        Response request = sandboxStep.setUser(validUriSandbox.validUriSandBoxSetUser, body);
        int status = request.getStatusCode();

        sandboxStep.assertSuccessResponse(request.path(keysResponseSandbox.keysResponseStatus), message);
        Assert.assertEquals(statusCode, status);

        userBody = body;
    }

    @Test(dependsOnMethods = "testSetUser")
    public void testGetUser() {
        Response request = sandboxStep.getUser(userBody[0][1], validUriSandbox.validUriSandBoxListUsers);
        int status = request.getStatusCode();

        sandboxStep.assertUserCreated(request, userBody);
        Assert.assertEquals(200, status);
    }

    @Test(dependsOnMethods = "testGetUser", dataProvider = "deleteUser", dataProviderClass = bodyToRequest.class)
    public void testDeleteUser(String message, int statusCode) {
        Response request = sandboxStep.deleteUser(userBody[0][1], validUriSandbox.validUriSandBoxDeleteUser);
        int status = request.getStatusCode();

        sandboxStep.assertSuccessResponse(request.path(keysResponseSandbox.keysResponseStatus), message);
        Assert.assertEquals(statusCode, status);
    }

    @Test(dependsOnMethods = "testDeleteUser", dataProvider = "invalidUser", dataProviderClass = bodyToRequest.class)
    public void testInvalidUser(String message, int statusCode) {
        Response request = sandboxStep.getUser(userBody[0][1], validUriSandbox.validUriSandBoxListUsers);
        int status = request.getStatusCode();
        HashMap response = request.path(keysResponseSandbox.keysResponseError);

        sandboxStep.assertFailResponse(response, message);
        Assert.assertEquals(statusCode, status);
    }

    @Test(dataProvider = "failAuth", dataProviderClass = bodyToRequest.class)
    public void testFailAuth(String[][] body, String message, int statusCode) {
        Response request = sandboxStep.failAuthToSetUser(validUriSandbox.validUriSandBoxSetUser, body);
        int status = request.getStatusCode();
        HashMap response = request.path(keysResponseSandbox.keysResponseError);

        sandboxStep.assertFailResponse(response, message);
        Assert.assertEquals(statusCode, status);
    }

    @Test(dataProvider = "emptyKeys", dataProviderClass = bodyToRequest.class)
    public void testTrySetUser(String[][] body, String message, int statusCode) {
        Response request = sandboxStep.setUser(validUriSandbox.validUriSandBoxSetUser, body);
        int status = request.getStatusCode();
        HashMap response = request.path(keysResponseSandbox.keysResponseError);

        sandboxStep.assertFailResponse(response, message);
        Assert.assertEquals(statusCode, status);
    }
}
