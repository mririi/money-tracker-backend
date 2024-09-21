pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mririi/money-tracker-backend'.toLowerCase() // Ensures lowercase image name
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        DB_USER = ''
        DB_PASSWORD = ''
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://github.com/mririi/money-tracker-backend.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    withCredentials([
                        string(credentialsId: 'db-user', variable: 'DB_USER'),
                        string(credentialsId: 'db-password', variable: 'DB_PASSWORD')
                    ]) {
                        docker.image('maven:3.8.3-openjdk-17').inside {
                            sh 'mvn clean package -DskipTests -Pprod'
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    docker.withRegistry('', "${DOCKER_CREDENTIALS_ID}") {
                        docker.image("${DOCKER_IMAGE}:latest").push()
                    }
                }
            }
        }
    }
}
