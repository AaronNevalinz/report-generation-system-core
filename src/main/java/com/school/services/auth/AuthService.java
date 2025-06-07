package com.school.services.auth;

import com.school.Repository.ClassRepository;
import com.school.Repository.SchoolRepository;
import com.school.Repository.SystemUserRepository;
import com.school.models.SchoolClass;
import com.school.models.enums.ClassLevel;
import com.school.models.enums.Role;
import com.school.models.School;
import com.school.models.SystemUser;
import com.school.payload.SchoolRegistrationDto;
import com.school.security.JwtUtil;
import com.school.utils.OperationReturnObject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final SystemUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final SchoolRepository schoolRepository;
    private final ClassRepository classRepository;

    @Transactional
    public OperationReturnObject register(SchoolRegistrationDto userDetail) {
        School school = new School();
        school.setName(userDetail.getSchoolName());
        school.setName(userDetail.getSchoolName());
        schoolRepository.save(school);
        validateUser(userDetail.getUsername(), userDetail.getEmail());
        SystemUser user = new SystemUser();
        user.setUsername(userDetail.getUsername());
        user.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        user.setEmail(userDetail.getEmail());
        user.setFirstName(userDetail.getAdminFirstName());
        user.setLastName(userDetail.getAdminLastName());
        user.setRole(Role.ADMIN);
        user.setActive(1);
        user.setSchool(school);
        userRepository.save(user);

//        add all classes to school
        for(ClassLevel classLevel : ClassLevel.values()) {
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setName(classLevel);
            schoolClass.setSchool(school);
            classRepository.save(schoolClass);
        }

        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setReturnCodeAndMessage(201, "User registered Successfully");
        return operationReturnObject;
    }

    //    validate if username or email already exists
    private void validateUser(String username, String email) {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username is already in use");
        }
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email is already in use");
        }
    }

    public OperationReturnObject login(SystemUser userDetail) {
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        try{
//            will throw badCredentialsException if invalid credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetail.getUsername(), userDetail.getPassword()
                    )
            );

            var user = userRepository.findByUsername(userDetail.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Username not found"));
            var jwt = jwtUtil.generateToken(user);

            Map<String, Object> tokenMap = Map.of("token", jwt);

            operationReturnObject.setCodeAndMessageAndReturnObject(200, "Login Success", tokenMap);
            return operationReturnObject;
        }catch (BadCredentialsException e){
            Map<String, Object> errors = Map.of(
                    "error", "Invalid Username or Password"
            );
            operationReturnObject.setCodeAndMessageAndReturnObject(400, e.getMessage(), errors);
            return operationReturnObject;
        }catch (IllegalArgumentException e){
            Map<String, Object> errors = Map.of(
                    "error", e.getMessage()
            );
            operationReturnObject.setCodeAndMessageAndReturnObject(400, e.getMessage(), errors);
            return operationReturnObject;
        }
    }
}
