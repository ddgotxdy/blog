# 基础镜像使用java
FROM java:8

# 作者
MAINTAINER ddgo

#定义变量
ARG JAR_FILE

# 复制修改名字
COPY ${JAR_FILE} file.jar

# 运行jar包
ENTRYPOINT java -jar file.jar

#暴露8080端口作为微服务
EXPOSE 28083