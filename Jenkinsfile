pipeline {
    agent any
    tools {
        dockerTool "docker"
    }

    environment {
        // Docker image name
        DOCKER_IMAGE = 'mririi/money-tracker-backend'
        // DockerHub credentials ID stored in Jenkins
        DB_USER = ''
        DB_PASSWORD = ''
    }

    stages {
       stage('Clone Repository') {
           steps {
               git branch: 'master', url: 'https://github.com/mririi/money-tracker-backend.git'
           }
       }

        stage("Build") {
            steps {
                script {
                    docker.build('mriri1/${DOCKER_IMAGE}:latest')
                }
            }
        }
        stage("Push") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        docker.image('mriri1/${DOCKER_IMAGE}:latest').push()
                        docker.image('mriri1/${DOCKER_IMAGE}:latest').push("latest")
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images to save space on Jenkins
            sh 'docker rmi ${DOCKER_IMAGE}:latest || true'
        }
    }
}