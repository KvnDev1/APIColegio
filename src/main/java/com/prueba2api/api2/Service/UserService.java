package com.prueba2api.api2.Service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.prueba2api.api2.Models.User;
import com.prueba2api.api2.Models.Role;
import com.prueba2api.api2.Repository.UserRepository;


@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Usado para codificar la contraseña

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        // Asignar rol ADMIN siempre
        user.setRole(Role.ADMIN);
        // Codificar la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
