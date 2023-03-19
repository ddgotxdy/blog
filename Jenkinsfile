pipeline {
    agent any
    parameters {
        string(name: 'tag', defaultValue: 'latest', description: '镜像版本号')
    }
    stages {
        stage('Pull') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '0ba32dfc-6af7-4ccc-aa2b-9cddd4bdcdc5', url: 'git@github.com:ddgotxdy/blog.git']]])
            }
        }
        stage('Compile&Install') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn -f ${project_name} clean package dockerfile:build'
                // 打包并推送镜像
                sh 'docker login --username=ddgotxdy --password=1314520ASD registry.cn-beijing.aliyuncs.com'
                sh 'docker tag ${project_name}:${tag} registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/${project_name}:${tag}'
                sh 'docker push registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/${project_name}:${tag}'
            }
        }
        stage('Deploy') {
            steps {
                echo 'deploy'
            }
        }
    }
    post {
      always {
         emailext (
             subject: '构建通知：${PROJECT_NAME} - BUILD # ${BUILD_NUMBER} - ${BUILD_STATUS} !',
             body: '${FILE,path="email.html"}',
             to: '1812805089@qq.com'
         )
      }
    }
}