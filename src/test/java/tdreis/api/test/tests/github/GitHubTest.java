package tdreis.api.test.tests.github;

import com.jayway.restassured.response.Response;
import tdreis.api.test.common.Property;
import tdreis.api.test.datadriven.githubData.queryRequest;
import tdreis.api.test.steps.github.githubStep;
import tdreis.api.test.supporttests.githubsupport.validUriGithub;
import tdreis.api.test.supporttests.githubsupport.keysResponse;
import tdreis.api.test.tests.Base;
import org.junit.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.baseURI;

public class GitHubTest {

    @Parameters({"url_api_github", "environment"})
    public GitHubTest(@Optional String url, @Optional String environment) {
        if (url == null) {
            url = Property.SITE_ADDRESS_GITHUB;
        }

        if (environment == null) {
            environment = Property.ENVIRONMENT_TEST;
        }

        Base.environmentConf = environment;
        baseURI = url;
    }

    @Test(dataProvider = "repoName", dataProviderClass = queryRequest.class)
    public void testGetRepo(String[][] pathRepo, int statusCode) {
        Response request = githubStep.getUriGitHub(pathRepo, validUriGithub.validUriGitHubRepos);
        int status = request.getStatusCode();
        HashMap response = request.path(keysResponse.keysResponseOwner);

        githubStep.assertResponseRepo(response);
        Assert.assertEquals(statusCode, status);
    }
}
