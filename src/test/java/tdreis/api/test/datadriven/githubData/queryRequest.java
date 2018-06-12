package tdreis.api.test.datadriven.githubData;

import org.testng.annotations.DataProvider;
import tdreis.api.test.supporttests.githubsupport.dataToRequests;

public class queryRequest {

    @DataProvider(name = "repoName")
    public static Object[][] getRepo() {
        return new Object[][]{
                //Valid user status code 200
                {dataToRequests.teamDetailsQuery, 200},
        };
    }
}
