# 基础镜像使用java
FROM java:8

# 作者
MAINTAINER ddgo

#定义变量
ARG JAR_FILE

# 复制修改名字
COPY ${JAR_FILE} article.jar

# 运行jar包
ENTRYPOINT java -jar article.jar

#暴露8080端口作为微服务
EXPOSE 8080