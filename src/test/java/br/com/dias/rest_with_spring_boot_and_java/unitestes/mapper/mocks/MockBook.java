package br.com.dias.rest_with_spring_boot_and_java.unitestes.mapper.mocks;

import br.com.dias.rest_with_spring_boot_and_java.dto.v1.BookDTO;
import br.com.dias.rest_with_spring_boot_and_java.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public BookDTO mockBookDTO() {
        return mockBookDTO(0);
    }

    public Book mockBookEntity() {
        return mockBookEntity(0);
    }

    public BookDTO mockBookDTO(Integer number) {
        BookDTO dto = new BookDTO();
        dto.setId(number.longValue());
        dto.setAuthor("Author Name" + number);
        dto.setTitle("Title Name" + number);
        dto.setLaunchDate("Launch Date" + number);
        dto.setPrice(20.00);
        return dto;
    }

    public List<BookDTO> mockBookDTOList() {
        List<BookDTO> bookDTO = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bookDTO.add(mockBookDTO(i));
        }
        return bookDTO;
    }

    public Book mockBookEntity(Integer number) {
        Book entity = new Book();
        entity.setId(number.longValue());
        entity.setAuthor("Author Name" + number);
        entity.setTitle("Title Name" + number);
        entity.setLaunchDate("Launch Date" + number);
        entity.setPrice(20.00);

        return entity;
    }

    public List<Book> mockBookEntityList() {
        List<Book> book = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            book.add(mockBookEntity(i));
        }
        return book;
    }
}