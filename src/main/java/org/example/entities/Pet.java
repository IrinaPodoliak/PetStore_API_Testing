package org.example.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.entities.PetFieldsClasses.Category;
import org.example.entities.PetFieldsClasses.Tag;

import java.util.List;

@Data
@Accessors(chain = true)
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}
