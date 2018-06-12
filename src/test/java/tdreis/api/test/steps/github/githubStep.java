package tdreis.api.test.steps.github;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import tdreis.api.test.supporttests.Common;
import tdreis.api.test.util.Request;
import tdreis.api.test.util.Util;

import java.util.Map;

import static tdreis.api.test.supporttests.githubsupport.dataToRequests.repoOwnerName;

public class githubStep {

    /**
     * Send get method to URL
     *
     * @param param string array query to get
     * @param uri   path to request
     * @return response
     */
    public static Response getUriGitHub(String[][] param, String uri) {

        String queryUri = uri + "/" + param[0][0] + "/" + param[0][1];

        return Request.getRequest(queryUri);
    }

    public static void assertResponseRepo(Map response) {

        Map shouldInt = Util.isInt(response.get("id").toString());

        Assert.assertEquals(response.get("login"), repoOwnerName);
        Assert.assertTrue(shouldInt.get(Common.keyException).toString(), (Boolean) shouldInt.get(Common.keyBoolean));
    }
}
