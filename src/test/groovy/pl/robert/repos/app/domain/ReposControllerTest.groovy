package pl.robert.repos.app.domain

import com.google.common.collect.Ordering
import com.jayway.restassured.response.Response
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import pl.robert.repos.app.ReposController

import static com.jayway.restassured.RestAssured.given

@WebMvcTest(ReposController.class)
class ReposControllerTest {

    @Autowired
    private WebApplicationContext context

    @Autowired
    private MockMvc mockMvc

    @BeforeEach
    void setupSpec() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    void 'Should find all repositories by existing owner'() {
        String existingOwner = 'RobertKrzywina'

        given().when()
                .get('/repositories/' + existingOwner)
                .then().statusCode(200)
    }

    @Test
    void 'Should not find any repositories cause owner do not exists'() {
        String notExistingOwner = UUID.randomUUID().toString()

        given().when()
                .get('/repositories/' + notExistingOwner)
                .then().statusCode(404)
    }

    @Test
    void 'Should find all repositories by existing owner and do not sort them cause sorting by other field than #stars is not supported'() {
        String sortingField = 'fullName'

        given().when()
                .get('/repositories/RobertKrzywina?sort=' + sortingField + ',asc')
                .then().statusCode(200).extract().response()
    }

    @Test
    void 'Should find all repositories by existing owner and sort them ascending by stars field'() {
        String sortingField = 'stars'

        Response response = given().when()
                .get('/repositories/RobertKrzywina?sort=' + sortingField + ',asc')
                .then().statusCode(200).extract().response()
        List<String> jsonResponse = response.jsonPath().getList('stargazers_count')

        Assert.assertTrue(Ordering.natural().isOrdered(jsonResponse))
    }

    @Test
    void 'Should find all repositories by existing owner and sort them descending by stars field'() {
        String sortingField = 'stars'

        Response response = given().when()
                .get('/repositories/RobertKrzywina?sort=' + sortingField + ',desc')
                .then().statusCode(200).extract().response()
        List<String> jsonResponse = response.jsonPath().getList('stargazers_count').reverse()

        Assert.assertTrue(Ordering.natural().isOrdered(jsonResponse))
    }
}
