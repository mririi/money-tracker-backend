pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mriri1/money-tracker-backend'.toLowerCase()
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://github.com/mririi/money-tracker-backend.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE}:latest .'
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIALS_ID) {
                    docker.image("${IMAGE_NAME}:${IMAGE_TAG}").push()
                }
            }
        }
    }
}
