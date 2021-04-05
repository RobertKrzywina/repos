package pl.robert.repos.app.shared

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import javax.annotation.PostConstruct

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class BaseTest {

    @Autowired
    protected MockMvc mockMvc

    protected String uri

    @LocalServerPort
    private int port

    @PostConstruct
    void init() {
        uri = 'http://localhost:' + port
    }
}
