package ru.netology

abstract class BaseService<T> {
    protected val items = mutableListOf<T>()
    private var lastId = 0

    fun getNextId() = lastId++

    abstract fun add(note: T): Int

    abstract fun edit(note: T, textNote: String): Int

    abstract fun delete(note: T): Int

    abstract fun get(ownerId: Int): MutableList<T>

    abstract fun getById(noteId: Int): T

    abstract fun createComment(note: T, comment: Comment): Int

    abstract fun editComment(note: T, comment: Comment, textComment: String): Int

    abstract fun deleteComment(note: T, comment: Comment): Int

    abstract fun getComments(note: T): List<Comment>

    abstract fun restoreComment(note: T, comment: Comment): Int

    fun clear() {
        items.clear()
    }

}