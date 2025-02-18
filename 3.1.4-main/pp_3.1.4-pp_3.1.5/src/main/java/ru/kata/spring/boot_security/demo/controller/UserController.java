package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.GetUsersResponse;
import ru.kata.spring.boot_security.demo.util.PersonErrorResponse;
import ru.kata.spring.boot_security.demo.util.PersonNotCreatedException;
import ru.kata.spring.boot_security.demo.util.ResponseMessage;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;


@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Email и пароль обязательны"));
        }

        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage("Пользователь с таким email уже зарегистрирован"));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(email);
        user.setRoles(Collections.singletonList(roleService.findById(1L)));

        userService.add(user);

        // Сохранение пользователя в сессии
        session.setAttribute("user", user);

        // Возвращаем сообщение о успешной регистрации
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Пользователь успешно зарегистрирован"));
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Пользователь не авторизован"));
        }

        // Возвращаем данные пользователя
        return ResponseEntity.ok(user);
    }


    @GetMapping("/user/api")
    public ResponseEntity<GetUsersResponse> getCurrentUser(Principal principal) {
        GetUsersResponse response = new GetUsersResponse();
        Optional<User> currentUser = userService.findByEmail(principal.getName());
        response.setAllUsers(userService.getAll());
        response.setCurrentUser(currentUser.orElse(null));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/admin/api/users")
    public ResponseEntity<GetUsersResponse> getUsers(Principal principal, @RequestParam(value = "id", required = false) Long id) {
        GetUsersResponse response = new GetUsersResponse();
        Optional<User> currentUser = userService.findByEmail(principal.getName());
        response.setAllUsers(userService.getAll());
        response.setCurrentUser(currentUser.orElse(null));
        response.setAllRoles(roleService.findAll());

        if (id != null) {
            response.setShowUser(userService.showUser(id));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/admin/api/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userService.showUser(id), HttpStatus.OK);

    }


    @PostMapping("/admin/api/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/admin/api/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @RequestParam(value = "id", required = false) long id) {
        user.setId(id);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/api/users")
    public ResponseEntity<HttpStatus> deletedUser(@RequestParam(value = "id") long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(e.getMessage());
        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }
}