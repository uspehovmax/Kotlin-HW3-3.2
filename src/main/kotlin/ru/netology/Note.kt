package ru.netology

import java.util.Collections.emptyList

data class Note(
    val id: Int = 0,
    val ownerId: Int = 0,
    val comments: MutableList<Comment> = emptyList<Comment>().toMutableList(),
    val title: String = "Заголовок",
    val text: String = "",
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val isDelete: Boolean = false,
)
