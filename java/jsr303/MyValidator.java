package com.huice.middleware.distributor.sharding;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class MyValidator {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyValidator myValidator = new MyValidator();
        myValidator.validatorBeanProperties();
        myValidator.validatorMethodArgs();
        myValidator.validatorReturnValue();
    }

    public void validatorBeanProperties() {
        // 构造被验证对象，继承，内部类等复杂结构同样支持
        ValidatorBean validatorBean = new ValidatorBean();
        validatorBean.setName("");
        validatorBean.setAge(-2);
        // 获取bean validation验证类(单例即可)
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        // 验证对象属性
        Set<ConstraintViolation<ValidatorBean>> set = validator.validate(validatorBean);
        // 输出验证结果
        for (ConstraintViolation<ValidatorBean> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

    }

    /**
     * 使用Bean Validation验证方法入参，方法返回值
     */
    class TestServiceImpl {

        // @Valid注解实现内部参数递归校验
        public ValidatorBean validMethodArgs(@NotNull @Valid ValidationSonBean bean, @NotBlank String channel) {
            return null;
//            return new ValidatorBean();
        }

        public @NotNull @Valid ValidatorBean validReturnArgs() {
//            return null;
            return new ValidatorBean() {
                {
                    setAge(3);
                    setName("");
                }
            };
        }
    }

    public void validatorMethodArgs() throws NoSuchMethodException {
        Class<TestServiceImpl> clazz = TestServiceImpl.class;
        // 获取目标类需要验证的方法
        Method method = clazz.getMethod("validMethodArgs", ValidationSonBean.class, String.class);

        // 校验快速失败策略，第一个失败终止流程返回，构建Validator等方式有多种
        Configuration<?> configure = Validation.byDefaultProvider().configure();
        configure.addProperty("hibernate.validator.fail_fast", "true");
        Validator validator = configure.buildValidatorFactory().getValidator();

        // 验证目标方法的入参
        Set<ConstraintViolation<TestServiceImpl>> set = validator.forExecutables().validateParameters(new TestServiceImpl(), method, buildMethodArgs());
        // 输出验证结果
        for (ConstraintViolation<TestServiceImpl> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }

    private Object[] buildMethodArgs() {
        ValidatorBean bean = new ValidatorBean();
        ValidatorBean.InnerClazz innerClazz = bean.new InnerClazz();
        innerClazz.setInnerName("");
        bean.setName("outclass");
        bean.setAge(10);
        bean.setInnerClazz(innerClazz);

        ValidationSonBean sonBean = new ValidationSonBean();
        sonBean.setSonName("");
        sonBean.setAge(0);
        sonBean.setName("");
        sonBean.setInnerClazz(innerClazz);
        return new Object[]{sonBean, "string"};
    }

    public void validatorReturnValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<TestServiceImpl> clazz = TestServiceImpl.class;
        Method method = clazz.getMethod("validReturnArgs");
        // 获取目标方法的返回值
        Object returnValue = method.invoke(new TestServiceImpl());
        // 获取bean validation验证类(单例即可)
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        // 验证目标方法的返回值
        Set<ConstraintViolation<TestServiceImpl>> set = validator.forExecutables().validateReturnValue(new TestServiceImpl(), method, returnValue, new Class<?>[0]);
        // 输出验证结果
        for (ConstraintViolation<TestServiceImpl> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}
