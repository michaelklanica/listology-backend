package com.example.listologybackend.service;

import com.example.listologybackend.model.ListItem;
import com.example.listologybackend.model.User;
import com.example.listologybackend.repository.ListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListItemService {

    private final ListRepository listRepository;

    public ListItemService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<ListItem> findAllByUser(User user) {
        return listRepository.findByUser(user);
    }

    public ListItem createListItem(ListItem listItem) {
        return listRepository.save(listItem);
    }

    public List<ListItem> findAllByCategory(String category) {
        return listRepository.findByCategory(category);
    }
}
