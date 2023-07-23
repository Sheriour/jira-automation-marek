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

    RequestSpecification reqSpec;

    public JiraApi(){
        String username = "sheriour@gmail.com";
        String secret = "ATATT3xFfGF00B8IKKUHbfg60cctSK84Cq2n7rbfFhoCAhDRCah0v93SwvCyPOK5Ij69kYugWUznmrDJowGqVcjr_dd" +
                "MEQMtByHajZztCQMxaNHnQ4Ntj_k_bANdKQNe6nTbRzqkH2sjS-FHkKN4sdQKbvCQr-nJ3sR-aKWzWNryYj7wRXAZ-Ys=8E39C895";

        RestAssured.baseURI = "https://marek-dziekan-automation.atlassian.net/rest/api";

        reqSpec = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, secret);
    }


    public Response sendGet(String path){
        return reqSpec.when().get(path);
    }

    //        JiraApi.GetInstance().sendDelete("/3/project/10002");
    public Response sendDelete(String path){
        return reqSpec.when().delete(path);
    }

    public void printGet(String path){
       System.out.println(reqSpec.when().get(path).body().asString());
    }
}
