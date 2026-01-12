package com.project.Shopapp.controller;

import com.project.Shopapp.DTO.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@Validated
public class Categorycontroller {
    // hiển thị tất cả các categories
    @GetMapping("")
    public ResponseEntity<String> getCategories(
            @RequestParam("page") int page,  // page = 1
            @RequestParam("limit") int limit // limit = 12
    ){ // http://localhost:8088/api/v1/categories?page=1&limit=12
        return ResponseEntity.ok(String.format("Hello, I'm Cateogy with page = %d and limit = %d",page,limit));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> putCategories(@PathVariable Long id){
        return ResponseEntity.ok("put Categories with id = " + id );
    }
   // neu gui len post bang 1 object
    @PostMapping("")
    public ResponseEntity<?>
    CreateCategories(@Valid @RequestBody CategoryDTO category,
                     BindingResult result){
        if(result.hasErrors()){
           List<String> errorMessage =  result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);

        }
        


        return ResponseEntity.ok("Name category is"+category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCategories(@PathVariable Long id){
        return ResponseEntity.ok("Delete Categories with id = " + id );
    }




}
