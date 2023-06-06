package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created By Ronak Patel
 */
@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBase {
    static int storeid;
    static String name = "Prime" + TestUtils.getRandomValue();

    static String UpdatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "London";
    static String address2 = "UK";
    static String city = "Abc";
    static String state = "Gujarat";
    static String zip = "A380061";

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        Response response = storeSteps.createStore(name, type, address, address2, city, state, zip);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeid = jsonPath.getInt("id");
        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(address, jsonPath.getString("address"));
        Assert.assertEquals(address2, jsonPath.getString("address2"));
        Assert.assertEquals(city, jsonPath.getString("city"));
        Assert.assertEquals(state, jsonPath.getString("state"));
        Assert.assertEquals(zip, jsonPath.getString("zip"));
    }

    @Title("This will get information by Store ID")
    @Test
    public void test002() {
        storeSteps.getStoreInformationByStoreId(storeid);

    }

    @Title("Update store information")
    @Test
    public void test003() {
        ValidatableResponse response = storeSteps.updateStoreInformation(UpdatedName, type, address, address2, city, state, zip, storeid);
        Assert.assertEquals(UpdatedName, response.extract().path("name"));
    }

    @Title("This will Delete the store information")
    @Test
    public void test004() {
        storeSteps.deleteStoreId(storeid);
        storeSteps.getStoreId(storeid);
    }


}
