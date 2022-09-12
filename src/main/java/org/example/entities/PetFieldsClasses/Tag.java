package org.example.entities.PetFieldsClasses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag {
        private String id;
        private String name;
    }
