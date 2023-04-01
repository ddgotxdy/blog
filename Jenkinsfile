pipeline {
    agent any
    parameters {
        string(name: 'project_name', defaultValue: 'blog-api', description: '项目名称')
        string(name: 'tag', defaultValue: 'latest', description: '镜像版本号')
        string(name: 'branches', defaultValue: '*/main', description: '分支')
        string(name: 'port', defaultValue: '28081', description: '端口号')
        string(name: 'active', defaultValue: 'dev', description: '开发环境')
        string(name: 'namespace', defaultValue: '89e0b3c7-a725-407f-a4a2-80f4e9ced5b1', description: 'nacos命名空间')
        string(name: 'configName', defaultValue: 'ddgo-aliyun', description: '远程的机器')
    }
    stages {
        // 拉取代码
        stage('Pull') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '${branches}']], extensions: [], userRemoteConfigs: [[credentialsId: '0d8ecd4d-2706-4f33-8df5-5e32f9dfcace', url: 'git@github.com:ddgotxdy/blog.git']]])
            }
        }
        // 编译安装公共子工程
        stage('Install') {
            steps {
                sh 'mvn clean install -Dmaven.test.skip=true -pl blog-common'
                sh 'mvn clean install -Dmaven.test.skip=true -pl blog-dal'
            }
        }
        // 打包上传服务
        stage('Package') {
            steps {
                sh 'mvn -f ${project_name} clean package dockerfile:build'
                // 打包并推送镜像
                sh 'docker login --username=ddgotxdy --password=1314520ASD registry.cn-beijing.aliyuncs.com'
                sh 'docker tag ${project_name}:${tag} registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/${project_name}:${tag}'
                sh 'docker push registry.cn-beijing.aliyuncs.com/ddgotxdy-blog/${project_name}:${tag}'
            }
        }
        // 部署服务
        stage('Deploy') {
            steps {
                //先上传文件，后执行命令，命令和源文件，两者必须有一个有值
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: '$configName',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '/root/java/deploy.sh $project_name $tag $port $active $namespace',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '',
                                    remoteDirectory: '/root/java',
                                    remoteDirectorySDF: false,
                                    removePrefix: '',
                                    sourceFiles: ''
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
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