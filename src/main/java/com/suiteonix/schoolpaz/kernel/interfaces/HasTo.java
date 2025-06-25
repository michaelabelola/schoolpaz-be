package com.suiteonix.schoolpaz.kernel.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;

public interface HasTo<DTO> {
    @Transient
    @JsonIgnore
    To<DTO> to();

}
