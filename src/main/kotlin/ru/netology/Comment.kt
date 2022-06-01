package ru.netology

data class Comment(
    val noteId: Int = 0,
    val commentId: Int = 0,
    val textComment: String = "",
    val isDelete: Boolean = false,

    )