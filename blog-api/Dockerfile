# 基础镜像使用java
FROM java:8

# 作者
MAINTAINER ddgo

#定义变量
ARG JAR_FILE
# 配置环境变量
ENV ACTIVE="dev"
ENV NAMESPACE="89e0b3c7-a725-407f-a4a2-80f4e9ced5b1"

# 复制修改名字
#COPY ${JAR_FILE} api.jar

# 运行jar包
ENTRYPOINT java -jar blog-api-1.0-SNAPSHOT.jar -Dspring.profiles.active=$ACTIVE -Dspring.cloud.nacos.config.namespace=$NAMESPACE -Dspring.cloud.nacos.discovery.namespace=$NAMESPACE

#暴露28081端口作为微服务
EXPOSE 28081