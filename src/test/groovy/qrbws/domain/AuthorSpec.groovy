package groovy.qrbws.domain

import grails.test.mixin.TestFor
import org.apache.commons.lang.StringUtils
import qrbws.Author
import spock.lang.Specification

@TestFor(Author)
public class AuthorSpec extends Specification {

    Author author

    def setup() {
        author = new Author()
    }


    void "Test notes can be null"() {

        when: 'notes be null'
        author.name = 'ferran'
        author.notes = null

        then: 'validation should pass'
        author.validate()
    }

    void "Test name can't be null"() {

        when: 'name be null'
        author.name = null

        then: 'validation should fail'
        !author.validate()
    }

    void "Test name can't be blank"() {

        when: 'name be blank'
        author.name = ''

        then: 'validation should fail'
        !author.validate()
    }

    void "Test name can not exceed 254 characters"() {

        when: 'name be 255 characers'
        author.name = StringUtils.leftPad("*", 255, '*')

        then: 'validation should fail'
        !author.validate()
    }
}
