package pl.fis.restapisecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public String getAdmin() {
        return "This is admin!";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public String createAdmin() {
        return "Admin created!";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public String deleteAdmin() {
        return "Admin deleted!";
    }
}
