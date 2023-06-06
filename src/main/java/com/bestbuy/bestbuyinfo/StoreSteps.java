package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created By Ronak Patel
 */
public class StoreSteps {
    @Step("Creating store with name : {0},type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}")
    public Response createStore(String name, String type, String address, String address2,
                                String city, String state, String zip) {
        StorePojo storePojo = StorePojo.getStorePojo(name,type,address,address2,city,state,zip);
       return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_STORE);
    }

    @Step("Getting store with storeid : {0}")
    public ValidatableResponse getStoreInformationByStoreId(int storeid) {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID + storeid)
                .then().log().all().statusCode(200);
    }

    @Step("Update store with name : {0},type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}")
    public ValidatableResponse updateStoreInformation(String name, String type, String address,String address2,
                                                      String city,String state, String zip, int storeid){

        StorePojo storePojo = StorePojo.getStorePojo(name,type,address,address2,city,state,zip);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID + storeid)
                .then().log().all().statusCode(200);
    }
    @Step("Delete store information with storeid : {0}")
    public ValidatableResponse deleteStoreId(int storeid){
        return SerenityRest.given().log().all()
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID + storeid)
                .then().statusCode(200);
    }

    @Step("Getting store information with storeid : {0}")
    public ValidatableResponse getStoreId(int storeid){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID + storeid)
                .then().statusCode(404);
    }




    }



