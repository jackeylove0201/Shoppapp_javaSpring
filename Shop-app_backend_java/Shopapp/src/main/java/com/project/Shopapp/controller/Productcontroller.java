package com.project.Shopapp.controller;

import com.project.Shopapp.DTO.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("api/v1/products")
@RestController
//@Validated
public class Productcontroller {

    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    )
    {
          return ResponseEntity.ok(String.format("This is products list with page = %d and limit = %d",page,limit));

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id){
        return ResponseEntity.ok("This is product with id = "+id);
    }

    @PostMapping("")
    public ResponseEntity<?> InsertProduct(@Valid @RequestBody ProductDTO product,
                                           BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
             return ResponseEntity.badRequest().body(errorMessage);




        }
        return ResponseEntity.ok("Insert successfully product"+product);
    }



}
