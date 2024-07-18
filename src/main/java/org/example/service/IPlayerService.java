package org.example.service;

import org.example.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void save(Player player);

    void edit(Player player);
}
