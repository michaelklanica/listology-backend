package com.example.listologybackend.repository;

import com.example.listologybackend.model.ListItem;
import com.example.listologybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListRepository extends JpaRepository<ListItem, Long> {
    List<ListItem> findByUser(User user);

    List<ListItem> findByCategory(String category);
}
