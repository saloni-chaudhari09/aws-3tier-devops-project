pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t aws-3tier-java-app:1.0 .'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                    docker stop aws-3tier-java-container || true
                    docker rm aws-3tier-java-container || true
                    docker run -d \
                        --name aws-3tier-java-container \
                        -p 8081:8081 \
                        aws-3tier-java-app:1.0
                '''
            }
        }

        stage('Verify') {
            steps {
                sh 'docker ps'
            }
        }
    }
}
