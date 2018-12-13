package ru.kazakova_net.notelin.mvp.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import ru.kazakova_net.notelin.utils.formatDate
import java.util.*

class Note : Model {
    @Column(name = "title")
    public var title: String? = null

    @Column(name = "text")
    public var text: String? = null

    @Column(name = "create_date")
    public var createDate: Date? = null

    @Column(name = "change_date")
    public var changeDate: Date? = null

    constructor(title: String, createDate: Date, changeDate: Date) {
        this.title = title
        this.createDate = createDate
        this.changeDate = changeDate
    }

    constructor()
    constructor(title: String, createDate: Date)

    fun getInfo(): String = """Название:
        |$title
        |Время создания:
        |${formatDate(createDate)}
        |Время изменения:
        |${formatDate(changeDate)}
    """.trimMargin()
}