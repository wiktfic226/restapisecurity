package pl.fis.restapisecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String getAdmin() {
        return "This is admin!";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createAdmin() {
        return "Admin created!";
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAdmin() {
        return "Admin deleted!";
    }
}
