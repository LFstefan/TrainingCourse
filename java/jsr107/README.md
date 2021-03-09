# JSR107

## 依赖jar包
```
<!-- https://mvnrepository.com/artifact/javax.cache/cache-api -->
<dependency>
    <groupId>javax.cache</groupId>
    <artifactId>cache-api</artifactId>
    <version>1.1.1</version>
</dependency>
```

## 依赖位置
- /Users/stefan/Documents/Tools/apache-maven-3.6.3/repository/javax/cache/cache-api/1.1.1/cache-api-1.1.1.jar

## 编译命令
- cp参数指定额外加载依赖jar包
- d参数编译class文件到指定目录下（该目录必须提前创建好）
- 如果源文件使用了package关键字，执行 java 命令需要退出到 package 的上一级目录，然后执行 java com.package.name.FileName
- 如果没有使用package关键字，直接在class文件的当前目录执行 java 命令即可
```
javac -cp /Users/stefan/Documents/Tools/apache-maven-3.6.3/repository/javax/cache/cache-api/1.1.1/cache-api-1.1.1.jar **/*.java -d target
cd target
java jsr107.CacheTest
```