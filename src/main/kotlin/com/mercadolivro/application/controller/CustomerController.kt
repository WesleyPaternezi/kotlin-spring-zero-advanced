package com.mercadolivro.application.controller

import com.mercadolivro.application.controller.request.PostCustomerRequest
import com.mercadolivro.application.controller.request.PutCustomerRequest
import com.mercadolivro.commons.metrics.AppMetrics
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService,
    val metrics: AppMetrics
) {
    @GetMapping("/welcome")
    fun welcomeCustomer(): String{
        metrics.incrementMetricAction("success", "welcome")
        return "Welcome Customer"
    }

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