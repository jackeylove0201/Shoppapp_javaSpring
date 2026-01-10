package com.project.Shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    @NotBlank(message = "The product's name is not emty")
    @Size(min = 5, max = 200, message = "The product's name must to 5 from 200 characters")
    private String name;
    @Min(value = 0, message = "The price must be greater than 0")
    @Max(value = 1000000, message = "The price must be smaller than 1000000")
    private Float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private String categoryId;
}
