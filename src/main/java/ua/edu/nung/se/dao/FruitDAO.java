package ua.edu.nung.se.dao;

import io.dropwizard.hibernate.AbstractDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.SessionFactory;
import ua.edu.nung.se.entity.Fruit;

import java.util.List;
import java.util.Optional;

public class FruitDAO extends AbstractDAO<Fruit> {
    public FruitDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Fruit> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public Fruit create(Fruit fruit) {
        System.out.println("Creating fruit: " + fruit);
        return persist(fruit); // persist() — метод із AbstractDAO, зберігає і повертає об'єкт
    }

    public List<Fruit> findAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Fruit> query = builder.createQuery(Fruit.class);
        query.from(Fruit.class);
        return list(query);
    }

    public void delete(Fruit fruit) {
        currentSession().remove(fruit);
    }

    public Fruit update(Fruit fruit) {
        return persist(fruit); // persist працює і для нових, і для оновлених
    }

}
