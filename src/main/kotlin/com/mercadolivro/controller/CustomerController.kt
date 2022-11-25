package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun customersAll(@RequestParam name: String?): List<CustomerModel>{
        return customerService.getAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest){
        println("controler: ${customer.name}, ${customer.email}")
        return customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun customerById(@PathVariable id : Int): CustomerModel {
        return customerService.getById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id : Int, @RequestBody customer: PutCustomerRequest) {
        return customerService.update(customer.toCustomerModel(id))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id : Int){
        return customerService.delete(id)
    }
}