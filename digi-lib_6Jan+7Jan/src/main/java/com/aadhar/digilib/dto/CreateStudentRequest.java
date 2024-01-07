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
public class CreateStudentRequest {

    // controller got the createStudentRequest json -> java object
    // service gets the createStudentRequest java object
    // We need to convert the createStudentRequest to student object -> to
    // it does the processing and passes student object to Repository

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    // validity of the studet's lib card will 365 days from the day of redistration
    // 365 * 24 * 60 * 60 * 1000 = 31536000000

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                //.validity(new Date(System.currentTimeMillis() + // add millis of 365 days))
                .validity(new Date(System.currentTimeMillis() + 31536000000l))
                .build();
    }


}
