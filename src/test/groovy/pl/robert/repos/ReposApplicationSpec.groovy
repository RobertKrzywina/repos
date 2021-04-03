package pl.robert.repos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class ReposApplicationSpec extends Specification {

    @Autowired
    private ApplicationContext context

    def 'Should load Spring context'() {
        expect:
        context != null
    }
}
