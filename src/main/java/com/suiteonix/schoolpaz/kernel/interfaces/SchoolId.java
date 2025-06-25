package com.suiteonix.schoolpaz.kernel.interfaces;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record SchoolId(

        /*
         * The Nix id.
         * This is the entity id.
         * */
        String id,

        /*
         * The school.
         * This is the id of the school that owns the entity.
         * */
        String schoolId
) implements Serializable {
}