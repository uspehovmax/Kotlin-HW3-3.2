package ru.netology.service

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.netology.Comment
import ru.netology.Note
import ru.netology.NotesService
import ru.netology.NotesService.getSizeNotes

class NotesServiceTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        NotesService.clear()
    }

    @Test
    fun add() {
        NotesService.clear()
        val expectedNoteId = 2
        val note = Note(5, 1, text = "Test")
        val actualId = NotesService.add(note)
        assertEquals(expectedNoteId, actualId)
    }

    @Test
    fun edit() {
        val expectedResult = 1
        val note1 = Note(5, 1, text = "Test")
        val note2 = Note(8, 1, text = "Test")
        NotesService.add(note1)
        NotesService.add(note2)
        NotesService.add(note2)
        val textNote = "Test Edit"
        val actualResult = NotesService.edit(NotesService.getNotes()[1], textNote)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun delete() {
        val expectedResult = 1
        val note1 = Note(5, 1, text = "Test")
        NotesService.add(note1)
        val actualResult = NotesService.delete(NotesService.getNotes()[0])
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getById() {
        val expectedResult = NotesService.getNotes()[0]
        val actualResult = NotesService.getById(0)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun get() {
        val expectedResult = NotesService.getNotes()
        val actualResult = NotesService.get(1)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun createComment() {
        val expectedResult = 1
        val comment1 = Comment(5, 10, textComment = "Test comment")
        val actualResult = NotesService.createComment(NotesService.getNotes()[0], comment1)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun editComment() {
        val expectedResult = 1
        NotesService.add(Note(100, 1, text = "Test"))
        NotesService.createComment(NotesService.getNotes()[0], Comment(textComment = "Текст комментария 1. Тест"))
        val actualResult = NotesService.editComment(
            note = NotesService.getById(0),
            comment = NotesService.getComments(NotesService.getNotes()[0])[0],
            textComment = "Edit comment"
        )
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun deleteComment() {
        val expectedResult = 1
        NotesService.add(Note(100, 1, text = "Test"))
        NotesService.createComment(NotesService.getNotes()[0], Comment(textComment = "Текст комментария 1. Тест"))
        val actualResult = NotesService.deleteComment(
            note = NotesService.getById(0),
            comment = NotesService.getComments(NotesService.getNotes()[0])[0]
        )
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun restoreComment() {
        val expectedResult = 183
        val actualResult = NotesService.restoreComment(
            note = NotesService.getById(0),
            comment = NotesService.getComments(NotesService.getNotes()[0])[0]
        )
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getComments() {
        val expectedResult = NotesService.getComments(NotesService.getById(0))
        val actualResult = NotesService.getComments(note = NotesService.getById(0))
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getSize() {
        val expectedSize = 2
        val actualSize = getSizeNotes()
        assertEquals(expectedSize, actualSize)
    }


}