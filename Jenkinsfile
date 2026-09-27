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
        stage('Push to ECR') {
            steps {
                sh '''
            aws ecr get-login-password --region ap-south-1 | \
            docker login --username AWS --password-stdin \
            540175642636.dkr.ecr.ap-south-1.amazonaws.com

            docker tag aws-3tier-java-app:1.0 \
            540175642636.dkr.ecr.ap-south-1.amazonaws.com/aws-3tier-java-app:1.0

            docker push \
            540175642636.dkr.ecr.ap-south-1.amazonaws.com/aws-3tier-java-app:1.0
        '''
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
