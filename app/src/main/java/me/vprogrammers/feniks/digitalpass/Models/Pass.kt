package me.vprogrammers.feniks.digitalpass.Models

class Pass {
    var name: String? = null
    var description: String? = null
    var icon: String? = null

    constructor() {}
    constructor(name: String?, description: String?, icon: String?) {
        this.name = name
        this.description = description
        this.icon = icon
    }
}