package pl.robert.repos.app.domain

import com.google.common.collect.Ordering
import com.jayway.restassured.response.Response
import org.junit.Assert
import org.junit.Test
import pl.robert.repos.app.shared.BaseTest

import static com.jayway.restassured.RestAssured.given

class ReposControllerTest extends BaseTest {

    @Test
    void 'Should find all repositories by existing owner'() {
        String existingOwner = 'RobertKrzywina'

        given().when()
                .get(uri + '/repositories/' + existingOwner)
                .then().statusCode(200)
    }

    @Test
    void 'Should not find any repositories cause owner do not exists'() {
        String notExistingOwner = UUID.randomUUID().toString()

        given().when()
                .get(uri + '/repositories/' + notExistingOwner)
                .then().statusCode(404)
    }

    @Test
    void 'Should find all repositories by existing owner and do not sort them cause sorting by other field than #stars is not supported'() {
        String sortingField = 'fullName'

        given().when()
                .get(uri + '/repositories/RobertKrzywina?sort=' + sortingField + ',asc')
                .then().statusCode(200).extract().response()
    }

    @Test
    void 'Should find all repositories by existing owner and sort them ascending by stars field'() {
        String sortingField = 'stars'

        Response response = given().when()
                .get(uri + '/repositories/RobertKrzywina?sort=' + sortingField + ',asc')
                .then().statusCode(200).extract().response()
        List<String> jsonResponse = response.jsonPath().getList(sortingField)

        Assert.assertTrue(Ordering.natural().isOrdered(jsonResponse))
    }

    @Test
    void 'Should find all repositories by existing owner and sort them descending by stars field'() {
        String sortingField = 'stars'

        Response response = given().when()
                .get(uri + '/repositories/RobertKrzywina?sort=' + sortingField + ',desc')
                .then().statusCode(200).extract().response()
        List<String> jsonResponse = response.jsonPath().getList(sortingField).reverse()

        Assert.assertTrue(Ordering.natural().isOrdered(jsonResponse))
    }
}
