pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mriri1/money-tracker-backend'.toLowerCase()
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

        stage('Docker Login and Push') {
            steps {
                script {
                    // Use withCredentials block to access username and password
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                        // Login to DockerHub
                        sh "echo ${DOCKERHUB_PASS} | docker login -u ${DOCKERHUB_USER} --password-stdin"

                        // Push the image
                        sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"

                        // Logout from DockerHub
                        sh "docker logout"
                    }
                }
            }
        }
    }
}
