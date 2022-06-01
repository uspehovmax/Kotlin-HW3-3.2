package ru.netology

import ru.netology.NotesService.getSizeNotes


fun main() {
    val noteService = NotesService

    //--- Исходные данные ---
    val note1 = Note(title = "Заголовок заметки 1", text = "Текст заметки 1", ownerId = 111)
    val note2 = Note(title = "Заголовок заметки 2", text = "Текст заметки 2", ownerId = 222)
    val note3 = Note(title = "Заголовок заметки 3", text = "Текст заметки 3", ownerId = 333)
    val comment1 = Comment(textComment = "Текст комментария 1")
    val comment2 = Comment(textComment = "Текст комментария 2")
    val comment3 = Comment(textComment = "Текст комментария 3")
    val comment4 = Comment(textComment = "Текст комментария 4")
    val comment5 = Comment(textComment = "Текст комментария 5")

    // fun noteService.add - добавляем 4 заметки
    noteService.add(note1)
    noteService.add(note2)
    noteService.add(note3)
    noteService.add(note1)
    println("--- Вывод заметок ---")
    println("Кол-во заметок: ${getSizeNotes()}")
    println(noteService.getNotes()[0])
    println(noteService.getNotes()[1])
    println(noteService.getNotes()[2])

    // fun noteService.createComment - добавляем комментарии к заметкам через fun noteService.getNotes
    noteService.createComment(noteService.getNotes()[0], comment1)
    noteService.createComment(noteService.getNotes()[1], comment2)
    noteService.createComment(noteService.getNotes()[1], comment3)
    noteService.createComment(noteService.getNotes()[1], comment4)
    noteService.createComment(noteService.getNotes()[2], comment5)
    println()
    println("--- Вывод заметок с комментариями ---")
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

    // fun noteService.edit - редактируем заметки
    noteService.edit(noteService.getNotes()[1], "Отредактировано 1 Заметка 2")
    noteService.edit(noteService.getNotes()[2], "Отредактировано 1 Заметка 3")
    println()
    println("--- Вывод заметок с измененными заметками (заметки Id=1 и Id=2) ---")
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

    // fun noteService.editComment - редактируем комментарии
    val note2Comment1 = noteService.getComments(noteService.getNotes()[1])[0]
    val note2Comment3 = noteService.getComments(noteService.getNotes()[1])[2]
    noteService.editComment(noteService.getNotes()[1], note2Comment1, "Отредактировано Комментарий 1 к Заметке 2")
    noteService.editComment(noteService.getNotes()[1], note2Comment3, "Отредактировано Комментарий 3 к Заметке 2")
    println()
    println("--- Вывод заметок с измененными комментариями (комментарии Id=0 и Id=2 заметки Id=1) ---")
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

    // fun noteService.get - списко заметок пользователя
    println()
    println("--- Вывод заметок пользователя 222 ---")
    println(noteService.get(222))

    // fun noteService.delete - удаление заметки
    println()
    println("--- Вывод после удаления заметки Id=0 ---")
    noteService.delete(noteService.getNotes()[0])
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

    // fun noteService.deleteComment - удаление комментария к заметке
    println()
    println("--- Вывод после удаления комментария Id=0 заметки Id=1 ---")
    noteService.deleteComment(noteService.getNotes()[1], noteService.getNotes()[1].comments[0])
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))
    println()
    println("--- Вывод после попытки удаления уже удаленного комментария Id=0 заметки Id=1 ---")
    noteService.deleteComment(noteService.getNotes()[1], noteService.getNotes()[1].comments[0])
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

    // fun noteService.restoreComment - восстановление комментария к заметке
    println()
    println("--- Вывод после восстановление комментария Id=0 заметки Id=1 ---")
    noteService.restoreComment(noteService.getNotes()[1], noteService.getNotes()[1].comments[0])
    println(noteService.getById(0))
    println(noteService.getById(1))
    println(noteService.getById(2))

}