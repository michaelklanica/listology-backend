package com.example.listologybackend.controller;

import com.example.listologybackend.model.ListItem;
import com.example.listologybackend.model.User;
import com.example.listologybackend.repository.ListRepository;
import com.example.listologybackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@CrossOrigin(origins = "*")
public class ListController {

    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public ListController(ListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ListItem>> getListsByUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(user -> ResponseEntity.ok(listRepository.findByUser(user)))
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ListItem> createList(
            @RequestParam String username, @RequestBody ListItem listItem) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
        listItem.setUser(user);
        return ResponseEntity.ok(listRepository.save(listItem));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ListItem>> getListsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(listRepository.findByCategory(category));
    }
}
