package dcits.liufein.utils.builder;

/**
 * @author liufei
 * @date 10/27/2019
 */
public class BeanBuilder {
    private String name;
    private Integer age;

    public BeanBuilder() {
    }

    public BeanBuilder(BeanBuilder.Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "BeanBuilder{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder implements dcits.liufein.utils.builder.Builder<BeanBuilder> {
        private String name;
        private Integer age;

        public Builder() {
        }

        public BeanBuilder.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public BeanBuilder.Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        @Override
        public BeanBuilder build() {
            BeanBuilder beanBuilder = new BeanBuilder(this);
            this.reset();
            return beanBuilder;
        }

        @Override
        public void reset() {
            this.name = null;
            this.age = null;
        }
    }

    public static void main(String[] args) {
        BeanBuilder bean = new BeanBuilder.Builder().setName("liufei").setAge(23).build();
        System.out.println(bean.toString());
    }
}
