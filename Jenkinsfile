pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "mohitiiitb/calculator-spe:latest"
        ANSIBLE_INVENTORY = "inventory.ini"
        DEPLOY_PLAYBOOK = "deploy.yml"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/mohitiiitb/calculator-spe.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${env.DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-creds', url: '']) {
                    script {
                        sh "docker push ${env.DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Deploy via Ansible') {
            steps {
                script {
                    sh "ansible-playbook -i ${env.ANSIBLE_INVENTORY} ${env.DEPLOY_PLAYBOOK}"
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
