package com.example.inlupp3;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path = "/book")
    @CrossOrigin()
    List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        for (Book p : bookRepository.findAll()) {
            books.add(p);
        }
        return books;
    }

    
    @GetMapping(path = "/book/{id}")
    Optional<Book> getBookById(@PathVariable Integer id) {
        return bookRepository.findById(id);
    }

    @PutMapping(path = "/book/{id}", consumes="application/json", produces="application/json")
    @CrossOrigin
        Book updateBook(@PathVariable Integer id, @RequestBody Book bookToUpdate){
            
            Book savedBook = bookRepository.findById(id).get();
            savedBook.setIsbn(bookToUpdate.getIsbn());
            savedBook.setTitle(bookToUpdate.getTitle());
            savedBook.setAuthor(bookToUpdate.getAuthor());
            bookRepository.save(savedBook);
            return savedBook;
    }

    @PostMapping(path = "/book", consumes="application/json", produces="application/json")
    @CrossOrigin
        ResponseEntity<?> createBook(@RequestBody Book bookToCreate){
            boolean isbnOk = bookToCreate.checkIsbn(bookToCreate.getIsbn());
            if(!isbnOk){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Isbn must be either 10 or 13 digits");
            }
         
            bookRepository.save(bookToCreate);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{Id}")
                .buildAndExpand(bookToCreate.getId())
                .toUri();
            return ResponseEntity.created(location).build();    

    }

    @DeleteMapping(path = "/book/{id}")
    void deleteBook(@PathVariable Integer id){
        bookRepository.deleteById(id);
}

    
}