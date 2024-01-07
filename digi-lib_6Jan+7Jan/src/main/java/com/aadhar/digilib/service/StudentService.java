package com.aadhar.digilib.service;

import com.aadhar.digilib.dto.CreateStudentRequest;
import com.aadhar.digilib.dto.UpdateStudentRequest;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student create(CreateStudentRequest createStudentRequest){
        Student studentWithNullId = createStudentRequest.to();
        Student studentSavedInDB = studentRepository.save(studentWithNullId);
        return studentSavedInDB;
    }

    public Student get(int studentId){
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student delete(int studentId){
        Student student = this.get(studentId);
        studentRepository.deleteById(studentId);
        return student;
    }

    public void update(int studentId, UpdateStudentRequest updateStudentRequest){
        // take out the student object from db by id
        Student studentFromDB = studentRepository.findById(studentId).orElse(null);

        // convert the dto to student object
        Student studentFromDTO = updateStudentRequest.to();

        // now check fields of dto . If not null , set to the student from db object
        mapDtoToStudentObject(updateStudentRequest, studentFromDB);

        // call student Repo . save(studentFromDb)
        studentRepository.save(studentFromDB);
    }

    private void mapDtoToStudentObject(UpdateStudentRequest updateStudentRequest, Student studentFromDB) {
        if(updateStudentRequest.getName() != null){
            // set that to student from db
            studentFromDB.setName(updateStudentRequest.getName());
        }

        if(updateStudentRequest.getValidity() != null){
            studentFromDB.setValidity(updateStudentRequest.getValidity());
        }

        if(updateStudentRequest.getContact() != null){
            studentFromDB.setContact(updateStudentRequest.getContact());
        }
    }

}
