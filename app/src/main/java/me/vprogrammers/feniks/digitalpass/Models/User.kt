package me.vprogrammers.feniks.digitalpass.Models

class User {
    var firstName: String? = null
    var lastName: String? = null
    var image: String? = null

    constructor() {}
    constructor(firstName: String?, lastName: String?, image: String?) {
        this.firstName = firstName
        this.lastName = lastName
        this.image = image
    }
}