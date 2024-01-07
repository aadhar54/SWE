package com.aadhar.digilib.dto;

import com.aadhar.digilib.model.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String contact;

    private Date validity;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .validity(this.validity)
                .build();
    }
}
