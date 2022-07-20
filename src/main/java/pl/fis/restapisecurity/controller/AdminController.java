package pl.fis.restapisecurity.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/admin")
@SecurityRequirement(name = "restapi")
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
