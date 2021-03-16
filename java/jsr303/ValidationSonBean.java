package com.huice.middleware.distributor.sharding;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ValidationSonBean extends ValidatorBean{
    @NotBlank
    public String sonName;

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValidationSonBean that = (ValidationSonBean) o;
        return Objects.equals(sonName, that.sonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sonName);
    }
}
