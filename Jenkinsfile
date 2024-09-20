pipeline {
    agent any

    environment {
        // Docker image name
        DOCKER_IMAGE = 'mririi/money-tracker-backend'
        // DockerHub credentials ID stored in Jenkins
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        DB_USER = ''
        DB_PASSWORD = ''
    }

    stages {
        stage('Initialize'){
            steps{
                script {
                    def dockerHome = tool 'docker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                    sh 'usermod -a -G docker jenkins'
                }
             }
        }
        stage('Checkout') {
            steps {
                // Clone the repository
                git 'https://github.com/mririi/money-tracker-backend.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    // Fetch credentials and set them as environment variables
                    withCredentials([
                        string(credentialsId: 'db-user', variable: 'DB_USER'),
                        string(credentialsId: 'db-password', variable: 'DB_PASSWORD')
                    ]) {
                        // Run Maven build inside Docker container
                        sh 'docker run --rm -v "$PWD:/workspace" -v "$HOME/.m2:/root/.m2" maven:3.8.3-openjdk-17 mvn -f /workspace/pom.xml clean package -DskipTests -Pprod'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                sh 'docker build -t ${DOCKER_IMAGE}:latest .'
            }
        }

        stage('Push to DockerHub') {
            steps {
                // Push the Docker image to DockerHub
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    sh 'docker login -u $DOCKERHUB_USER -p $DOCKERHUB_PASS'
                    sh 'docker push ${DOCKER_IMAGE}:latest'
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