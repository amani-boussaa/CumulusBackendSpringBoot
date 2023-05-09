package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.React;
import com.example.cumulusspringboot.interfaces.IComment;
import com.example.cumulusspringboot.interfaces.IReact;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/react")
public class ReactController {

    @Autowired
    private IReact iReact;

    @PostMapping("/CreateReact")
    public React CreateReact(@RequestBody React R) {
        return iReact.CreateReact(R);
    }

    @GetMapping("/ReadReact")
    public List<React> ReadReact() {
        return iReact.ReadReact();
    }

    @PutMapping("/UpdateReact")
    public React UpdateReact(@RequestBody React R) {
        return iReact.UpdateReact(R);
    }

    @DeleteMapping("/DeleteReact/{id}")
    public void DeleteReact(@PathVariable Integer id) {
        iReact.DeleteReact(id);
    }
}
