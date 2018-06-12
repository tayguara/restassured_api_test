package tdreis.api.test.util;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class Request {

    /**
     * Simple get request
     * @param uri string
     * @return Response
     */
    public static Response getRequest(String uri) {

        return given()
                .when().get(uri);
    }

    /**
     * Post request with base auth and json body
     *
     * @param user       string
     * @param pass       String
     * @param uri         String
     * @param jsonRequest Map
     * @return Response
     */
    public static Response postRequestAuthBody(String user, String pass, String uri, Map jsonRequest) {

        return given().auth().preemptive().basic(user, pass)
                .contentType(ContentType.JSON)
                .body(jsonRequest).
                        when().post(uri);
    }

    /**
     * Delete request with base auth
     *
     * @param user String
     * @param pass String
     * @param uri String
     * @return Response
     */
    public static Response deleteRequestAuth(String user, String pass, String uri) {

        return given().auth().preemptive().basic(user, pass)
                .contentType(ContentType.JSON)
                .when().delete(uri);
    }
}
