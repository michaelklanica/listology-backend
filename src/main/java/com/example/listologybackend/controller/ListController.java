package com.example.listologybackend.controller;

import com.example.listologybackend.model.ListItem;
import com.example.listologybackend.model.User;
import com.example.listologybackend.repository.ListRepository;
import com.example.listologybackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@CrossOrigin(origins = "*")  // Allow all origins for testing
public class ListController {

    private final ListRepository listRepository;
    private final UserRepository userRepository;

    // Constructor to inject both repositories
    public ListController(ListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{username}")
    public List<ListItem> getListsByUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(user -> listRepository.findByUser(user))
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @PostMapping("/create")
    public ListItem createList(@RequestBody ListItem listItem, @RequestParam String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
        listItem.setUser(user);
        return listRepository.save(listItem);
    }
}
