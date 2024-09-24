pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mriri1/money-tracker-backend'.toLowerCase()
        BUILD_TAG = "latest"
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository
                git branch: 'master', url: 'https://github.com/mririi/money-tracker-backend.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    try {
                        // Build the Docker image and tag it with the build number
                        sh "docker build -t ${DOCKER_IMAGE}:${BUILD_TAG} ."
                    } catch (Exception e) {
                        // Handle failure and mark the stage as failed
                        currentBuild.result = 'FAILURE'
                        error("Docker image build failed: ${e.message}")
                    }
                }
            }
        }

        stage('Docker Login and Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                        try {
                            // Log in to DockerHub
                            sh "echo ${DOCKERHUB_PASS} | docker login -u ${DOCKERHUB_USER} --password-stdin"

                            // Push the image with the unique build tag
                            sh "docker push ${DOCKER_IMAGE}:${BUILD_TAG}"
                            // Optionally push the 'latest' tag as well
                            sh "docker tag ${DOCKER_IMAGE}:${BUILD_TAG} ${DOCKER_IMAGE}:latest"
                            sh "docker push ${DOCKER_IMAGE}:latest"
                        } catch (Exception e) {
                            // Handle failure and mark the stage as failed
                            currentBuild.result = 'FAILURE'
                            error("Docker push failed: ${e.message}")
                        } finally {
                            // Always logout from Docker
                            sh "docker logout"
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build and push successful!'
        }
        failure {
            echo 'Build or push failed!'
        }
        always {
            // Clean up dangling images
            sh "docker image prune -f"
            // Clean the workspace
            cleanWs()
        }
    }
}
