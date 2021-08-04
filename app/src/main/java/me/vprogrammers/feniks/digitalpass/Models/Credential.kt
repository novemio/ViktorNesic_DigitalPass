package me.vprogrammers.feniks.digitalpass.Models

class Credential {
    var jwt: String? = null
    var type: String? = null

    constructor() {}
    constructor(jwt: String?, type: String?) {
        this.jwt = jwt
        this.type = type
    }
}