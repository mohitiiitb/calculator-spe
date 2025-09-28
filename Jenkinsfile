pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "mohitiiitb/calculator-spe:latest"
        ANSIBLE_INVENTORY = "inventory.ini"
        DEPLOY_PLAYBOOK = "playbook.yml"
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
                        sh "DOCKER_BUILDKIT=1 docker build -t ${env.DOCKER_IMAGE} ."
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
        success {
            echo 'Pipeline completed successfully!'
            script {
                withCredentials([usernamePassword(
                    credentialsId: 'pipeline-notify-email',
                    usernameVariable: 'EMAIL_USR',
                    passwordVariable: 'EMAIL_PSW'
                )]) {
                    emailext(
                        subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """
                            <html>
                            <body>
                                <h2 style="color:green;">Pipeline Succeeded.</h2>
                                <p><b>Job:</b> ${env.JOB_NAME}</p>
                                <p><b>Build:</b> #${env.BUILD_NUMBER}</p>
                                <p><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                            </body>
                            </html>
                        """,
                        mimeType: 'text/html',
                        to: "${EMAIL_USR}"
                    )
                }
            }
        }

        failure {
            echo 'Pipeline failed.'
            script {
                withCredentials([usernamePassword(
                    credentialsId: 'pipeline-notify-email',
                    usernameVariable: 'EMAIL_USR',
                    passwordVariable: 'EMAIL_PSW'
                )]) {
                    emailext(
                        subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """
                            <html>
                            <body>
                                <h2 style="color:red;">Pipeline Failed.</h2>
                                <p><b>Job:</b> ${env.JOB_NAME}</p>
                                <p><b>Build:</b> #${env.BUILD_NUMBER}</p>
                                <p><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                            </body>
                            </html>
                        """,
                        mimeType: 'text/html',
                        to: "${EMAIL_USR}"
                    )
                }
            }
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
