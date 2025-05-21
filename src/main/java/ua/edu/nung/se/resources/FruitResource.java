package ua.edu.nung.se.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ua.edu.nung.se.dao.FruitDAO;
import ua.edu.nung.se.entity.Fruit;

import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {
    private final FruitDAO fruitDAO;

    public FruitResource(FruitDAO fruitDAO) {
        this.fruitDAO = fruitDAO;
    }

    @GET
    @UnitOfWork
    public List<Fruit> getAllFruits() {
        return fruitDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Fruit getFruitById(@PathParam("id") int id) {
        return fruitDAO.findById(id).orElseThrow(
                () -> new NotFoundException("Fruit with id " + id + " not found")
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Fruit addFruit(Fruit fruit) {
        return fruitDAO.create(fruit);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteFruit(@PathParam("id") int id) {
        Fruit fruit = fruitDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit with id " + id + " not found"));
        fruitDAO.delete(fruit);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Fruit updateFruit(@PathParam("id") int id, Fruit updatedFruit) {
        Fruit existing = fruitDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit with id " + id + " not found"));

        // оновлюємо поля
        existing.setName(updatedFruit.getName());
        existing.setDescription(updatedFruit.getDescription());
        existing.setImageUrl(updatedFruit.getImageUrl());
        existing.setRefrigerationRequired(updatedFruit.isRefrigerationRequired());

        return fruitDAO.update(existing);
    }


}
