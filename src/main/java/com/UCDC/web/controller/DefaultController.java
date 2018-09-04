package com.UCDC.web.controller;

import com.UCDC.entities.Student;
import com.UCDC.utils.Storage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DefaultController {



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) throws NoSuchFieldException {
        Student student = new Student();
        Storage<Student> storage = new Storage<Student>(student);
        storage.beginTransaction();
        List<Student> studentList = storage.getAll();
        model.addAttribute("list", studentList);
        storage.commit();
        return "hello";
    }

}
