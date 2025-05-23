package com.school.controllers;

import com.school.models.SchoolClass;
import com.school.services.ClassService;
import com.school.utils.OperationReturnObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/class")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping()
    public OperationReturnObject fetchAllClasses() {
        List<SchoolClass> classes = classService.findAll();
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "fetching all classes", classes);
        return returnObject;
    }

    @PostMapping()
    public OperationReturnObject createSchoolClass(@RequestBody SchoolClass schoolClassRequest) {
        SchoolClass savedClass = classService.saveClass(schoolClassRequest);
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("classId", savedClass.getId());
        responseData.put("className", savedClass.getName());

        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(201, "Created a class", responseData);
        return returnObject;
    }

}
