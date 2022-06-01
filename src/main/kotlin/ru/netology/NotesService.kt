package ru.netology

import ru.netology.NotesService.lastCommentId
import ru.netology.exception.NotFoundException
import ru.netology.exception.RestoreException

object NotesService : BaseService<Note>() {
    private const val success = 1
    private const val notFound = 180
    private const val alreadyDelete = 182
    private const val notDelete = 183
    private val notes = mutableListOf<Note>()
    private var lastCommentId = 0

    // --- Реализация Notes ---
    override fun add(note: Note): Int {
        val getNoteId = note.copy(id = getNextId())
        notes.add(getNoteId)
        return getNoteId.id
    }

    override fun edit(note: Note, textNote: String): Int {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                return if (item.isDelete) {
                    throw NotFoundException("Заметка Id=${note.id} не найдена!!!")
                    notFound
                } else {
                    notes[index] = item.copy(text = textNote)
                    success
                }
            }
        }
        throw NotFoundException("Заметка для редактирования не найдена")
        return notFound
    }

    // В данной реализации удаление заметки не происходит. Только делаем isDelete = true
    override fun delete(note: Note): Int {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                return if (item.isDelete) {
                    throw NotFoundException("Заметка уже удалена")
                    notFound
                } else {
                    notes[index] = item.copy(isDelete = true)
                    success
                }
            }
        }
        throw NotFoundException("Заметка для удаления не найдена")
        return notFound
    }

    // Если пользователь не найден или у него нет заметок - будет возвращен пустой список
    override fun get(ownerId: Int): MutableList<Note> {
        var ownerNotesList = mutableListOf<Note>()
        for ((index, item) in notes.withIndex()) {
            if (item.ownerId == ownerId) {
                ownerNotesList += notes[index].copy()
            }
        }
        return ownerNotesList
    }

    override fun getById(noteId: Int): Note {
        for ((index, item) in notes.withIndex()) {
            if (item.id == noteId) {
                return item
            }
        }
        throw NotFoundException("Заметка по Id не найдена")
    }

    fun getSizeNotes(): Int {
        return notes.size
    }

    // --- Реализация Comments ---
    override fun createComment(note: Note, comment: Comment): Int {
        var nextCommentId = 0
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                nextCommentId = if (item.comments.isEmpty()) {
                    0
                } else {
                    notes[index].comments.last().commentId + 1
                }
                val nextComment = comment.copy(noteId = note.id, commentId = nextCommentId)
                notes[index] = item.copy(comments = (item.comments + nextComment) as MutableList<Comment>)
                return notes[index].comments.last().commentId
            }
        }
        throw NotFoundException("Заметка для создания комментария не найдена")
    }

    override fun editComment(note: Note, comment: Comment, textComment: String): Int {
        for (item in notes) {
            if (item.id == comment.noteId) {
                for ((index, item) in item.comments.withIndex()) {
                    if (item.commentId == comment.commentId) {
                        note.comments[index] = item.copy(textComment = textComment)
                        return success
                    }
                }
                //Вывод сообщениея вместо Exception
                println("Комментарий Id=${comment.commentId} заметки Id=${comment.noteId} не найден")
                return notFound
                //throw NotFoundException("Комментарий не найден")
            }
        }
        //Вывод сообщениея вместо Exception
        println("Заметка ${comment.noteId} не найдена")
        return notFound
        //throw NotFoundException("Заметка не найдена")
    }

    override fun deleteComment(note: Note, comment: Comment): Int {
        for (item in notes) {
            if (item.id == comment.noteId) {
                for ((index, item) in item.comments.withIndex()) {
                    if (item.commentId == comment.commentId) {
                        return if (comment.isDelete) {
                            //Вывод сообщениея вместо Exception
                            println("Комментарий Id=${comment.commentId} заметки Id=${comment.noteId} уже удален")
                            //throw NotFoundException("Комментарий уже удален")
                            alreadyDelete
                        } else {
                            note.comments[index] = item.copy(isDelete = true)
                            println("Комментарий Id=${comment.commentId} заметки Id=${comment.noteId} удален ")
                            success
                        }
                    }
                }
                //Вывод сообщениея вместо Exception
                println("Комментарий Id=${comment.commentId} Id=заметки ${comment.noteId} не найден ")
                return notFound
                //throw NotFoundException("Комментарий не найден")
            }
        }
        //Вывод сообщениея вместо Exception
        println("Заметка ${comment.noteId} не найдена")
        return notFound
        //throw NotFoundException("Заметка не найдена")
    }

    override fun getComments(note: Note): List<Comment> {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                return item.comments
            }
        }
        throw NotFoundException("Заметка не найдена")
    }

    override fun restoreComment(note: Note, comment: Comment): Int {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                for ((index, item) in note.comments.withIndex()) {
                    if (item.commentId == comment.commentId) {
                        return if (comment.isDelete) {
                            note.comments[index] = comment.copy(isDelete = false)
                            println("Комментарий Id=${comment.commentId} заметки Id=${comment.noteId} восстановлен ")
                            success
                        } else {
                            //Вывод сообщениея вместо Exception
                            println("Попытка восстановить не удаленный комментарий Id=${comment.commentId} Id=заметки ${comment.noteId}")
                            notDelete
                            //throw RestoreException("Попытка восстановить не удаленный комментарий")
                        }
                    }
                }
                //Вывод сообщениея вместо Exception
                println("Комментарий Id=${comment.commentId} Id=заметки ${comment.noteId} не найден ")
                return notFound
                //throw NotFoundException("Комментарий не найден")
            }
        }
        //Вывод сообщениея вместо Exception
        println("Заметка ${comment.noteId} не найдена")
        return notFound
        //throw NotFoundException("Заметка не найдена")
    }

    fun getNotes(): MutableList<Note> {
        return notes
    }

}


