pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                echo 'pull'
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '0ba32dfc-6af7-4ccc-aa2b-9cddd4bdcdc5', url: 'git@github.com:ddgotxdy/blog.git']]])
            }
        }
        stage('Build') {
            steps {
                echo 'build'
                sh 'mvn clean package'
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