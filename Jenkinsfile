pipeline {
    agent any
    parameters {
        choice(
            name: 'project_name',
            choices: ['blog-gateway', 'blog-api', 'blog-article-service', 'blog-file-service', 'blog-search-service', 'blog-sms-service'],
            description: '项目名称'
        )
        choice(
            name: 'tag',
            choices: ['latest', 'snapshot'],
            description: '镜像版本号'
        )
        choice(
            name: 'branches',
            choices: ['main', 'release'],
            description: '分支'
        )
        choice(
            name: 'port',
            choices: ['28080', '28081', '28082', '28083', '28084', '28085'],
            description: '端口号'
        )
        choice(
            name: 'active',
            choices: ['dev', 'prod'],
            description: '开发环境'
        )
        choice(
            name: 'namespace',
            choices: ['89e0b3c7-a725-407f-a4a2-80f4e9ced5b1', 'ef4977dc-f6b9-4012-aa3c-e7921bd6bb3d'],
            description: 'nacos命名空间'
        )
    }
    stages {
        // 拉取代码
        stage('Pull') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/${branches}']], extensions: [], userRemoteConfigs: [[credentialsId: '0d8ecd4d-2706-4f33-8df5-5e32f9dfcace', url: 'git@github.com:ddgotxdy/blog.git']]])
            }
        }
        // 编译安装公共子工程
        stage('Install') {
            steps {
                sh 'mvn clean install -Dmaven.test.skip=true -N'
                sh 'mvn clean install -Dmaven.test.skip=true -pl blog-dal'
                sh 'mvn clean install -Dmaven.test.skip=true -pl blog-common'
            }
        }
        // 打包上传服务
        stage('Package') {
            steps {
                sh 'mvn -f ${project_name} clean package -Dmaven.test.skip=true dockerfile:build'
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
                            configName: 'ddgo-aliyun',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '/root/java/deploy.sh ${project_name} ${tag} ${port} ${active} ${namespace}',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '',
                                    remoteDirectory: '',
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