package org.example.steps;

import io.restassured.response.Response;
import org.example.entities.Pet;
import org.example.service.PetStoreService;

import java.util.List;

import static org.example.service.uritemplate.PetServiceUri.*;

public class PetStoreSteps {
    private static final PetStoreService PET_STORE_SERVICE = PetStoreService.getInstance();

    public static Response getPetById(long id) {
        return PET_STORE_SERVICE.getRequest(PET_BY_ID, id);
    }

    public static List<Pet> getPetsByStatus(String status) {
        return PET_STORE_SERVICE.getRequest(PETS_BY_STATUS, status).jsonPath().getList("", Pet.class);
    }

    public static Response createPet(Pet pet) {
        return PET_STORE_SERVICE.postRequest(PETS, pet);
    }

    public static Response updatePet(Pet pet) {
        return PET_STORE_SERVICE.putRequest(PET_BY_ID, pet);
    }

    public static void deletePetById(long id) {
        PET_STORE_SERVICE.deleteRequest(PET_BY_ID, id);
    }
}
