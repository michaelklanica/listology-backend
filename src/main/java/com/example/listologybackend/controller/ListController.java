package com.example.listologybackend.controller;

import com.example.listologybackend.model.ListItem;
import com.example.listologybackend.repository.ListRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/lists")
public class ListController {
    private final ListRepository listRepository;

    public ListController(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping
    public List<ListItem> getAllLists() {
        return listRepository.findAll();
    }
}
