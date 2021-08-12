package serenitybase.rest.requestcalls;

import static net.serenitybdd.rest.SerenityRest.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseCalls {

  private static RequestSpecification getRequestSpecFromBuilder(RequestSpecBuilder builder) {
    return builder.build();
  }

  public static Response makeGetCall(RequestSpecBuilder builder, String url) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .log()
        .all()
        .when()
        .get(url)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response makePostCall(RequestSpecBuilder builder, String url) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .log()
        .all()
        .when()
        .post(url)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response makePostUserAuthCall(RequestSpecBuilder builder, String url) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .when()
        .post(url)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response makePutCall(RequestSpecBuilder builder, String url) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .log()
        .all()
        .when()
        .put(url)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response makeDeleteCall(RequestSpecBuilder builder, String url) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .log()
        .all()
        .when()
        .delete(url)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response makePatchCall(RequestSpecBuilder builder, String uri) {
    return rest()
        .given()
        .spec(getRequestSpecFromBuilder(builder))
        .log()
        .all()
        .when()
        .patch(uri)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }
}
