package org.example.repository;

import org.example.model.Player;

import java.util.List;

public interface IPlayerRepo {
    List<Player> findAll();

    Player findById(int id);

    void save(Player player);

    void edit(Player player);
    void remove(int id);
    List<Player> findByName(String name);
}
