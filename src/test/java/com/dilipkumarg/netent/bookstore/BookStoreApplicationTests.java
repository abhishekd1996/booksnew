package com.dilipkumarg.netent.bookstore;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dilipkumarg.netent.bookstore.config.Constants;
import com.dilipkumarg.netent.bookstore.data.entities.Author;
import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class BookStoreApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void contextLoads() throws Exception {
        Book book = new Book();
        book.setIsbn("book1isbn");
        book.setTitle("book1title");
        book.setPrice(10.0);
        book.setAuthor(new Author(1, "author1"));

        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(book);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(Constants.BASE_PATH + "/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(bookRepository).save(any(Book.class));

        Book resultBook = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
        assertNotNull(resultBook);
        assertEquals("book1isbn", resultBook.getIsbn());
    }

}
