package com.UCDC.web.controller;

import com.UCDC.entities.Student;
import com.UCDC.utils.Storage;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.repository.query.Param;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import javax.validation.Valid;

@Controller
public class DefaultController {

    private static RedirectView rv = new RedirectView();


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public void students(Model model) throws NoSuchFieldException {
        Student student = new Student();
        Storage<Student> storage = new Storage<Student>(student);
        storage.beginTransaction();
        List<Student> studentList = storage.getAll();
        model.addAttribute("list", studentList);
        storage.commit();
    }

    @RequestMapping(value = "/studentCreation", method = RequestMethod.GET)
    public String studentForm(Student student) {
        return "/studentCreation";
    }

    @ModelAttribute("Student")
    @RequestMapping(value = "/studentCreation", method = RequestMethod.POST)
    public RedirectView student(@Valid Student student, final BindingResult bindingResult) {
        Storage<Student> storage = new Storage<Student>(student);
        storage.beginTransaction();
        storage.insert(student);
        storage.commit();
        rv.setContextRelative(true);
        rv.setUrl("/students");
        return rv;
    }

    @RequestMapping(value = "/updateStudent", method = {RequestMethod.GET} ,params = "studentId")
    public String updateStudent(@Param("studentId") int studentId, Model model) throws NotFoundException {
        Student student = new Student();
        Storage<Student> storage = new Storage<Student>(student);
        storage.beginTransaction();
        student = storage.getById(studentId);
        storage.commit();
        if (student == null) {
            throw new NotFoundException("Not found user with ID " + studentId);
        }
        model.addAttribute("student", student);
        return "/updateStudent";
    }

    @ModelAttribute("Student")
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, params = "studentId")
    public RedirectView studentUpdate(@Param("studentId") int studentId, @Valid Student student, final BindingResult bindingResult) {
        student.setId(studentId);
        Storage<Student> storage = new Storage<Student>(student);
        storage.beginTransaction();
        storage.update(student);
        storage.commit();
        rv.setContextRelative(true);
        rv.setUrl("/students");
        return rv;
    }

}
