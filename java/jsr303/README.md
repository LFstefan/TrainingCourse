## bean validation
jsr303 规范了一套api，具体实现有hibernate，spring等

jakarta.validation-api-2.0.2.jar

- Jakarta项目是在Apache軟件基金會营运的开放源代码开发项目之一。开发着面向对象编程语言Java的程序库，框架等。
- Jakarta是Apache组织下的一套Java解决方案的开源软件的名称，它包括了很多子项目。Tomcat、Ant、Struts等等現在是Apache下的开源项目，也曾是Jakarta的关联项目。
- Jakarta的名称与印度尼西亚的首都雅加达（Jakarta）并无直接关系，实际上它是根据Sun Microsystems公司当时讨论创建这个项目时的会议室命名的。
- 其中，Jakarta项目所包括的相关工具、库以及框架等罗列如下：
    + BCEL - 处理Java字节码的类库
    + BSF - 脚本程序框架
    + Cactus - 服务器端Java类测试工具框架
    + ECS - 用来产生用于各种标记的Java API
    + HttpComponents- 超文本传输协议
    + JCS - 分布式缓存系统
    + JMeter - 压力测试工具
    + ORO - Java类库，提供与Perl5兼容的正则表达式功能
    + Regexp - 纯Java正则表达式包
    + Slide - 内容存储库，主要使用WebDAV
    + Taglibs - 一个代码库，用于支持开发定制化的JSP tag lib
- 以前隶属于Jakarta项目，但现在作为Apache软件基金的单独项目，有：
    + Ant - 构建工具
    + Commons - 一组使用类的合集，主要作为Java标准库的补充
    + HiveMind - 一个服务和配置的微内核
    + Maven - 一个项目构建和管理工具
    + POI - 一个纯java版本的函数库，用于操作Microsoft的常见文档格式，如Excel、Word、PowerPoint、Visio、Publisher、Outlook文件
    + Struts - 一种Web应用程序开发框架
    + Tapestry - 基于JavaBeans属性和强大的规格的组件对象模型
    + Tomcat - 服务器，提供JSP/Servlet相关容器类
    + Turbine - web应用快速开发框架
    + Velocity - 模板引擎

```
// 自定义注解校验

// @Valid注解提供了内部类等复杂结构校验的基础

// 使用脚本编写关联校验
@ScriptAssert(lang = "javascript", script = "_this.value == null || (_this.value != null && _this.value1 !=null)", message = "条件判断:如果conditionNull不为空时,则conditionNotNull也不能为空")
@ScriptAssert(lang = "javascript", script = "!_this.value2 || (_this.value > 5)", message = "基于某种条件的校验:value2要么为空,要么大于5")

// hibernate，jackson，Java 都各自有定义一些基本的注解校验
// 例如：hibernate的注释的适用范围，数字还是字符串，还是都可以；范围的大小，int支持范围的大小，还是long支持范围的大小；api看仔细了
@Range(max = Long.MAX_VALUE, min = 0L)
public long rangeNumber;
// 例如：Jackson中的JsonAnyGetter和JsonAnySetter用于接受反序列化时json字符串中含有，但是java对象中没有的属性键值对
@JsonAnyGetter
public Map<String, Object> getOther() {
    return other;
}
@JsonAnySetter
public void setOther(String key, Object value) {
    this.other.put(key, value);
}
// 例如：java的分组校验
public String validationTest(@RequestBody @Validated({Update.class, Insert.class}) JavaValidationApi validationAPI){}
@NotNull(groups = {Update.class, Insert.class})
public String name;
@NotBlank(groups = Update.class)
public String gender;
```
