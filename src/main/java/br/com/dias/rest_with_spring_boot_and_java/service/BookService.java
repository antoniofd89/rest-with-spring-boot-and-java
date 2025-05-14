package br.com.dias.rest_with_spring_boot_and_java.service;

import br.com.dias.rest_with_spring_boot_and_java.controllers.BooksController;
import br.com.dias.rest_with_spring_boot_and_java.controllers.PersonController;
import br.com.dias.rest_with_spring_boot_and_java.dto.v1.BookDTO;
import br.com.dias.rest_with_spring_boot_and_java.dto.v1.PersonDTO;
import br.com.dias.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.dias.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.dias.rest_with_spring_boot_and_java.model.Book;
import br.com.dias.rest_with_spring_boot_and_java.model.Person;
import br.com.dias.rest_with_spring_boot_and_java.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dias.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObject;
import static br.com.dias.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> findAll() {
        logger.info("Finding all books!");
        var books = parseListObject(bookRepository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);

        return books;
    }

    public BookDTO findById(Long id) {

        logger.info("Finding one Book");

        var entity = bookRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Create one Book");
        var entity = parseObject(book, Book.class);
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book");
        Book entity = bookRepository.findById(book.getId()).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one Book");

        Book entity = bookRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));
        bookRepository.delete(entity);
    }


    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BooksController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BooksController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BooksController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }
}
