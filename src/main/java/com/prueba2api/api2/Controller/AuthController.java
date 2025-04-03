package com.prueba2api.api2.Controller;

import com.prueba2api.api2.Models.User;
import com.prueba2api.api2.Models.DTOs.LoginRequestDTO;
import com.prueba2api.api2.Models.DTOs.RegisterRequestDTO;
import com.prueba2api.api2.Security.JWTUtil;
import com.prueba2api.api2.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
        return userOptional.filter(user ->
                    passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Credenciales inválidas")));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO registerRequest) {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "El usuario ya existe"));
        }
        // Crear nuevo usuario a partir del DTO de registro
        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());
        
        // El UserService se encargará de codificar la contraseña y asignar el rol ADMIN
        User savedUser = userService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario registrado exitosamente", "id", savedUser.getId().toString()));
    }
}
