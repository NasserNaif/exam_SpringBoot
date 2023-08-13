package com.example.exam1.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotNull(message = "age must not be null")
    @Positive(message = "age must be positive value")
    private Integer age;

    @NotEmpty(message = "major must not be empty")
    private String major;
}
