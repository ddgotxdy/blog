#!/bin/sh
#外部参数
project_name=$1
tag=$2
port=$3
active=$4
namespace=$5
image_name="$project_name":"$tag"

# 查询容器是否存在，存在则删除
container_id=$(docker ps -a | grep -w "$project_name":"$tag" | awk '{print $1}')
if [ "$container_id" != "" ] ; then
  docker stop "$container_id"
  docker rm "$container_id"
fi

# 查询镜像是否存在，存在则删除
image_id=$(docker images | grep -w "$project_name" | awk '{print $3}')
if [ "$image_id" != "" ] ; then
  docker rmi "$image_id"
fi

# 拉取代码
docker login --username=ddgotxdy --password=1314520ASD registry.cn-beijing.aliyuncs.com
docker pull registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/"$image_name"

# 启动容器
docker run -d -p "$port":"$port" registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/"$image_name" \
-e ACTIVE="$active" \
-e NAMESPACE="$namespace"

docker run -d -p 28081:28081 registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/blog-api:latest \
-e ACTIVE=dev \
-e NAMESPACE=89e0b3c7-a725-407f-a4a2-80f4e9ced5b1
