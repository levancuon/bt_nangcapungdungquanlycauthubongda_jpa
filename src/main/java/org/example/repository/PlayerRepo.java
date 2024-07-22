package org.example.repository;

import org.example.model.Player;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class PlayerRepo implements IPlayerRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("select c from Player c", Player.class);
        return query.getResultList();
    }

    @Override
    public Player findById(int id) {
        TypedQuery<Player> query = entityManager.createQuery("select c from Player c where c.id=:id", Player.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Player player) {
            entityManager.persist(player);
    }

    @Override
    public void edit(Player player) {
            entityManager.merge(player);
    }

    @Override
    public void remove(int id) {
        Player player = findById(id);
        if (player != null) {
            entityManager.remove(player);
        }
    }
    @Override
    public List<Player> findByName(String name) {
        List<Player> players = entityManager.createQuery("select p from Player p where p.name like :name",
                Player.class).setParameter("name","%"+name+"%" ).getResultList();
        return players;
    }


}
