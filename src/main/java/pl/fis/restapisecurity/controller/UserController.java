package pl.fis.restapisecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin')")
    public String getUser() {
        return "This is user!";
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:edit', 'admin')")
    public String updateUser() {
        return "User updated!";
    }
}
