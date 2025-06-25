package com.suiteonix.schoolpaz.kernel.interfaces;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;

public interface To<DTO> {
    @Transient
    @JsonIgnore
    DTO dto();

    @Transient
    @JsonIgnore
    DTO fullDto();

    @Transient
    @JsonIgnore
    DTO fullDtoMax();

}
