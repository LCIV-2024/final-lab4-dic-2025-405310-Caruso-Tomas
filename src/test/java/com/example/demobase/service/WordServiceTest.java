package com.example.demobase.service;

import com.example.demobase.dto.WordDTO;
import com.example.demobase.model.Word;
import com.example.demobase.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    private Word word1;
    private Word word2;
    private Word word3;

    @BeforeEach
    void setUp() {
        word1 = new Word(1L, "PROGRAMADOR", true);
        word2 = new Word(2L, "COMPUTADORA", false);
        word3 = new Word(3L, "TECNOLOGIA", false);
    }

    @Test
    void testGetAllWords() {
        // TODO: Implementar el test para getAllWords
        List<Word> palabras = Arrays.asList(word1, word2, word3);
        when(wordRepository.findAllOrdered()).thenReturn(palabras);
        List<WordDTO> result = wordService.getAllWords();
        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(word1.getId(), result.get(0).getId());
        assertEquals(word2.getId(), result.get(1).getId());
        assertEquals(word3.getId(), result.get(2).getId());
        assertEquals(word1.getUtilizada(), result.get(0).getUtilizada());
        assertEquals(word1.getPalabra(), result.get(0).getPalabra());
        verify(wordRepository, times(1)).findAllOrdered();
    }

    @Test
    void testGetAllWords_EmptyList() {
        // TODO: Implementar el test para getAllWords_EmptyList
        when(wordRepository.findAllOrdered()).thenReturn(List.of());
        List<WordDTO> result = wordService.getAllWords();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(wordRepository, times(1)).findAllOrdered();

    }
}

