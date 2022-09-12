package org.example.service;

import io.restassured.response.Response;
import org.example.service.uritemplate.UriTemplate;

public class PetStoreService extends CommonService {
    private static PetStoreService instance;

    public static PetStoreService getInstance() {
        if (instance == null) {
            instance = new PetStoreService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, int id) {
        return super.getRequest(uri.getUri(id));
    }

    public Response getRequest(UriTemplate uri, String status) {

        return super.getRequest(uri.getUri(status));
    }

    public Response putRequest(UriTemplate uri, Object body) {
        return super.putRequest(uri.getUri(), body);
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public void deleteRequest(UriTemplate uri, int id) {
        super.deleteRequest(uri.getUri(id));
    }
}
