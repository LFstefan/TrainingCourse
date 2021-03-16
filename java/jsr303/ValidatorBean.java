package com.huice.middleware.distributor.sharding;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ValidatorBean {

    @NotBlank
    public String name;

    @Positive
    public int age;

    @NotNull @Valid
    // @Valid注解表示该字段类型为非基本类型，类型实例内部还包含其他的可校验字段属性；简单来说就是该注解会触发递归校验
    public InnerClazz innerClazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public InnerClazz getInnerClazz() {
        return innerClazz;
    }

    public void setInnerClazz(InnerClazz innerClazz) {
        this.innerClazz = innerClazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidatorBean that = (ValidatorBean) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(innerClazz, that.innerClazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, innerClazz);
    }

    public class InnerClazz{
        @NotBlank
        public String innerName;

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InnerClazz that = (InnerClazz) o;
            return Objects.equals(innerName, that.innerName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(innerName);
        }
    }
}
