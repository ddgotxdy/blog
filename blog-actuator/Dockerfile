# 基础镜像使用java
FROM openjdk:8-jre

# 作者
MAINTAINER ddgo

#定义变量
ARG JAR_FILE
# 配置环境变量
ENV ACTIVE="dev"
ENV NAMESPACE="89e0b3c7-a725-407f-a4a2-80f4e9ced5b1"
ENV PROT=28087

# 复制修改名字
COPY ${JAR_FILE} actuator.jar

# 运行jar包
ENTRYPOINT java -jar actuator.jar -Dspring.profiles.active=$ACTIVE -Dspring.cloud.nacos.config.namespace=$NAMESPACE -Dspring.cloud.nacos.discovery.namespace=$NAMESPACE -Dserver.port=$PROT

#暴露28087端口作为微服务
EXPOSE $PROT