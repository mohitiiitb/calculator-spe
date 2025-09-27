pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "mohitiiitb/calculator-spe:latest"
        ANSIBLE_INVENTORY = "inventory.ini"
        DEPLOY_PLAYBOOK = "deploy.yml"
    }

    stages {
        stage('Build and Test JAR') {
            steps {
                sh 'mvn -B clean verify package'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'dockerhub-creds', url: '']) {
                        sh "docker build -t ${env.DOCKER_IMAGE} ."
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
