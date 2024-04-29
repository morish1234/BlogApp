package com.blogApp.payload;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.validation.annotation.Validated;

@Data

public class DtoPost {
    private long id;
     @NotBlank(message = "title should not blank")
    private String title;
     @NotBlank(message = "description  should is blank")
     @Size(min = 50,max = 1000,message = "min size should be 50 words")
    private String description;
     @NotBlank(message = "content would be more then 1000 words limit")
     @Size(min = 50,max = 1000,message = "min size should be 50 words")
    private String content;
}
