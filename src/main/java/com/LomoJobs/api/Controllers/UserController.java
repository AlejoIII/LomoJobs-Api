package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Auth.JwtUtil;
import com.LomoJobs.api.Models.User;
import com.LomoJobs.api.Repositories.UserRepository;
import com.LomoJobs.api.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/me")
    public User getProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Falta el token");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.getEmailFromToken(token);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PutMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam(value = "image", required = false) MultipartFile image,
            HttpServletRequest request
    ) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falta el token");
            }

            String token = authHeader.substring(7);
            String emailFromToken = jwtUtil.getEmailFromToken(token);

            User existingUser = userRepository.findByEmail(emailFromToken)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            existingUser.setName(name);
            existingUser.setEmail(email);

            User updated = userRepository.save(existingUser);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el perfil: " + e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}