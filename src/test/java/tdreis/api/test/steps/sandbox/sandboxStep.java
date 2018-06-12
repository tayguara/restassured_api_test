package tdreis.api.test.steps.sandbox;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import tdreis.api.test.supporttests.sandboxsupport.keysResponseSandbox;
import tdreis.api.test.util.Request;
import tdreis.api.test.util.Util;

import java.util.ArrayList;
import java.util.Map;

public class sandboxStep {

    private static String pass = "api-87em5po9-y012-0915-6l18-656aq";
    private static String user = "tdreis_api";

    /**
     * Send get method to URL
     *
     * @param param string query to get
     * @param uri   path to request
     * @return response
     */
    public static Response getUser(String param, String uri) {

        String queryUri = uri + param;

        return Request.getRequest(queryUri);
    }

    public static Response setUser(String uri, String[][] body) {
        Map objVal = Util.getArrayObj(body);

        return Request.postRequestAuthBody(user, pass, uri, objVal);
    }

    public static Response failAuthToSetUser(String uri, String[][] body) {
        String passInvalid = "y012-0915-6l18-656aq";
        String userInvalid = "api";
        Map objVal = Util.getArrayObj(body);

        return Request.postRequestAuthBody(userInvalid, passInvalid, uri, objVal);
    }

    public static Response deleteUser(String userDeleted, String uri) {
        String requestUri = uri + userDeleted;
        return Request.deleteRequestAuth(user, pass, requestUri);
    }

    public static void assertFailResponse(Map response, String message) {
        Assert.assertEquals(response.get(keysResponseSandbox.keysResponseMessage), message);
    }

    public static void assertSuccessResponse(String response, String message) {
        Assert.assertEquals(response, message);
    }

    public static void assertUserCreated(Response response, String[][] body) {
        ArrayList<String> user = response.path(keysResponseSandbox.keysResponseUsername);
        ArrayList<String> age = response.path(keysResponseSandbox.keysResponseAge);
        ArrayList<String> active = response.path(keysResponseSandbox.keysResponseActive);

        assertValueObj(user, body[0][1]);
        assertValueObj(age, body[1][1]);
        assertValueObj(active, body[2][1]);
    }

    private static void assertValueObj(ArrayList<String> value, String request) {
        for (Object val : value) {
            Assert.assertEquals(val, request);
        }
    }
}
