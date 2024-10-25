package com.example.listologybackend.repository;

import com.example.listologybackend.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListItem, Long> {}
