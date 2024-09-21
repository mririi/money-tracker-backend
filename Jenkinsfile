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