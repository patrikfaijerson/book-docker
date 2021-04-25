package com.example.inlupp3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String isbn;
    private String title;
    private String author;
   
    

    public Book(){}

    public Book(String isbn, String title, String author){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    /**
     * Isbn must be either 10 or 13 characters
     * @param isbn the isbn
     * @return if isbn is valid
     */
    public boolean checkIsbn(String isbn){
        if (isbn != null){
        
        int length = isbn.length();
        if(length == 10 || length == 13){
            return true;
        }
    }
        return false;
        
    } 

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    } 

    public String getTitle() {
        return title;
    } 

    public String getAuthor() {
        return author;
    }

   public void setId(Integer id) {
       this.id = id;
   }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
   public void setTitle(String title) {
       this.title = title;
   } 

    public void setAuthor(String author) {
        this.author = author;
    }

 


}