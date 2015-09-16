package qrbws

import grails.rest.Resource

@Resource(uri = '/api/book', formats = ['json'])
class Book {

    String isbn
    String title
    String synopsis
    Integer pages
    Idiom idiom
    Category category
    // TODO: Implement cover for book

    static hasMany = [authors: Author, comments: Comment]

    static constraints = {
        isbn blank: false, nullable: false, maxSize: 17, unique: true, matches: '\\d+'
        title blank: false, maxSize: 254, unique: true
        synopsis nullable: true, blank: true, size: 5..5000
        pages nullable: true, max: 9999, validator: {
            if (it < 0 && it != null) return ['book.pages.negative']
        }
        idiom nullable: true
        category nullable: true
        comments nullable: true
    }
}
