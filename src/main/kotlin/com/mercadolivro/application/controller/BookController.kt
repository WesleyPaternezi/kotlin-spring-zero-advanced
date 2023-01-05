package com.mercadolivro.application.controller

import com.mercadolivro.application.controller.request.PostBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest){
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(): List<BookModel> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findActives(): List<BookModel>{
        return bookService.findActives()
    }

    @GetMapping("/vendido")
    fun findVendidos(): List<BookModel>{
        return bookService.findVendidos()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Int): BookModel{
        return bookService.findById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int): ResponseEntity<Int>{
        bookService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}


