# 基础镜像使用java
FROM openjdk:8-jre

# 作者
MAINTAINER ddgo

#定义变量
ARG JAR_FILE

# 复制修改名字
COPY ${JAR_FILE} article.jar

# 运行jar包
ENTRYPOINT java -jar article.jar

#暴露28082端口作为微服务
EXPOSE 28082