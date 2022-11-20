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
}