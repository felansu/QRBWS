package qrbws.domain

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.apache.commons.lang.StringUtils
import qrbws.Comentario
import qrbws.Livro
import qrbws.Pessoa
import qrbws.ContaUsuario
import spock.lang.Specification

@TestFor(Comentario)
@Mock([Livro, ContaUsuario, Pessoa])
class ComentarioSpec extends Specification {

    Comentario comentario
    def book, user, person, status

    def setup() {
        Comentario.withNewSession() { session ->
            book = new Livro(titulo: 'Livro Test', isbn: '123').save()
            person = new Pessoa(nome: 'Pessoa Name', email: 'pessoa@email.com').save()
            user = new ContaUsuario(login: 'User Login', senha: '12345', pessoa: person).save()
            comentario = new Comentario(descricao: 'Comentario Test', book: book, userAccount: user, avaliacao: 2)
        }
    }

    void "Test avaliation max value is 5"() {

        when: 'value is 6'
        comentario.avaliacao = 6

        then: 'validation should fail'
        !comentario.validate()

        when: 'value is negative'
        comentario.avaliacao = -1

        then: 'validation should fail'
        !comentario.validate()

        when: 'value is 3'
        comentario.avaliacao = 3

        then: 'validation should pass'
        comentario.validate()
    }

    void "Test avaliation can be 0"() {

        when: 'value is null'
        comentario.avaliacao = 0

        then: 'validation should pass'
        comentario.validate()
    }

    void "Test description can be null"() {

        when: 'value is null'
        comentario.descricao = null

        then: 'validation should pass'
        comentario.validate()
    }

    void "Test description max value is 500"() {

        when: 'value is 501'
        comentario.descricao = StringUtils.leftPad("*", 501, '*')

        then: 'validation should fail'
        !comentario.validate()

        when: 'value is 500'
        comentario.descricao = StringUtils.leftPad("*", 500, '*')

        then: 'validation should pass'
        comentario.validate()
    }

    void "Test recommendation is false by default"() {

        when: 'save an comentario without recomendacao'
        comentario.save()

        then: 'validation should pass'
        !Comentario.find(comentario).recomendacao
    }

    void "Test comentario need an UserAccount for save"() {

        when: 'save an comentario without userAccount'
        comentario.userAccount = null

        then: 'validation should fail'
        !comentario.validate()

        when: 'save an comentario whit userAccount'
        comentario.userAccount = user

        then: 'validation should pass'
        comentario.validate()
    }
}
