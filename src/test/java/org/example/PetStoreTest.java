package org.example;

import io.restassured.response.Response;
import org.example.entities.Pet;
import org.example.steps.PetStoreSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.http.HttpStatus.*;

public class PetStoreTest {
    Pet createdPet = createPet();

    @Test
    public void getPetByIdTest() {
        createPetTest();
        Assert.assertEquals(getPetById().as(Pet.class).getId(), createdPet.getId());
        Assert.assertEquals(getPetById().getStatusCode(), SC_OK);
    }

    @Test
    public void getPetsByStatus() {
        String statusToFind = "sold";
        List<Pet> pets = PetStoreSteps.getPetsByStatus(statusToFind);
        Assert.assertEquals(pets.get(1).getStatus(), statusToFind);
    }
    @Test
    public void createPetTest() {
        Response expectedPet = PetStoreSteps.createPet(createdPet);
        Assert.assertEquals(expectedPet.getStatusCode(), SC_OK,
                "New pet was not created");
        Assert.assertEquals(expectedPet.as(Pet.class).getName(), createdPet.getName(),
                "Created pet name is not the same as expected");
    }

    @Test
    public void deletePetTest() {
        createPetTest();
        PetStoreSteps.deletePetById(createdPet.getId());
        Assert.assertEquals(getPetById().getStatusCode(), SC_NOT_FOUND,
                "Pet has not been deleted");
    }

    @Test
    public void updatePetTest() {
        createPetTest();
        Pet update = updatePet();
        Assert.assertEquals(PetStoreSteps.updatePet(update).getStatusCode(), SC_OK,
                "Pet was not changed");
        Assert.assertEquals(getPetById().as(Pet.class).getName(), "Phoenix",
                "Pet name is not changed");
    }

    public Response getPetById() {
        return PetStoreSteps.getPetById(createdPet.getId());
    }

    private Pet createPet() {
        return new Pet()
                .setName("Dana")
                .setId(3)
                .setStatus("available")
                .setPhotoUrls(List.of("https://www.instagram.com/p/B_fT49cptMb/"));

    }

    private Pet updatePet() {
        return new Pet().setId(3).
                setName("Phoenix").
                setStatus("available");
    }
}
