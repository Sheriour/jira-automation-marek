package system;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraApi {

    static JiraApi instance;

    public static JiraApi GetInstance(){
        if (instance == null) instance = new JiraApi();
        return instance;
    }

    String username;
    String secret;

    public JiraApi(){
        username = PropertyManager.GetInstance().getProperty("api.username");
        secret = PropertyManager.GetInstance().getProperty("api.secret");

        RestAssured.baseURI = "https://marek-dziekan-automation.atlassian.net/rest/api";
    }

    private RequestSpecification getReqSpec() {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, secret);
    }

    public Response sendGet(String path){
        return getReqSpec().when().get(path);
    }

    public Response sendDelete(String path){
        return getReqSpec().when().delete(path);
    }

    public String getProjectIdByName(String projectName){
        Response res =  getReqSpec().param("query", projectName).when().get("/3/project/search");
        String projectId = res.jsonPath().getString("values[0].id");
        if (projectId == null){
            System.out.println(
                    "Could not find find project id in response for project '"+projectName+"': " + res.body().asString()
            );
        }
        return projectId;
    }

    public void printGet(String path){
       System.out.println(getReqSpec().param("query", "ME").when().get(path).body().asString());
    }


}
