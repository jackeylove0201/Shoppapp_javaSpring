package com.project.Shopapp.controller;

import ch.qos.logback.core.util.StringUtil;
import com.project.Shopapp.DTO.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.swing.plaf.multi.MultiLabelUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> InsertProduct(@Valid @ModelAttribute ProductDTO product,

                                           BindingResult result
                                           ){

        try {
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            MultipartFile file = product.getFile();
            if(file != null){
                if(file.getSize() > 10 * 1024 * 1024){
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large maximum");
                }
                String contentType = file.getContentType();
                if(contentType == null || contentType.startsWith("images/")){
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                // luu file
                String fileName = storedFile(file);
                // luu vao doi tung trong DB
            }


            return ResponseEntity.ok("Insert successfully product"+product);
        }
        catch ( Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
    private String storedFile(MultipartFile file) throws IOException{
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
             //Them ID truoc ten file de dam bao do la file duy nhat
            String fileNameunique = UUID.randomUUID().toString() + "_"+ fileName;
            // Duong dan den file thu muc luu file
            Path UploardDir = Paths.get(("uploads"));
            if(!Files.exists(UploardDir)){
                Files.createDirectories(UploardDir);
            }
            Path destinationpath = Paths.get(UploardDir.toString(),fileNameunique);

            Files.copy(file.getInputStream(),destinationpath, StandardCopyOption.REPLACE_EXISTING);
            return fileNameunique;
    }



}
