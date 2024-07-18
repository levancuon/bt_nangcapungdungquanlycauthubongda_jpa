package org.example.service;

import org.example.model.Player;
import org.example.repository.IPlayerRepo;
import org.example.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerRepo playerRepo;

    @Override
    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player findById(int id) {
        Player player = playerRepo.findById(id);
        if (player == null) {
            return null;
        }
        return playerRepo.findById(id);
    }

    @Override
    public void save(Player player) {
        playerRepo.save(player);
    }

    @Override
    public void edit(Player player) {
        playerRepo.edit(player);
    }

    @Override
    public List<Player> findByName(String search) {
        return playerRepo.findByName(search);
    }
}
