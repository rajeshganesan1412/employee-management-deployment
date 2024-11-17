pipeline{
    agent any
    tools {
        maven 'maven_3_9_9'
    }
    stages {
        stage('Maven Build') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rajeshganesan1412/employee-management-deployment']])
                bat 'mvn clean install'
            }
        }
        stage ('Build Docker Image'){
            steps {
                script {
                    bat 'docker build -t rajeshson1412/employee-management .'
                }
            }
        }
        stage ('Push image to dockerhub') {
            steps {
                script {

                    bat 'docker login -u rajeshson1412 -p Aswin@0510'

                    bat 'docker push rajeshson1412/employee-management'
                }
            }
        }
        stage ('Deploy into kubernetes') {
            steps {
                script {
                }
            }
        }
    }
}