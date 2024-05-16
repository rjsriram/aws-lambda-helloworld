pipeline {
    agent any 

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code..'
                // Here you can add code checkout steps
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/rjsriram/aws-lambda-helloworld.git']]])
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                // Here you can add Maven build steps
                withMaven(maven: 'Maven Default') {
                    if (isUnix()) {
                        sh 'mvn clean package'
                    } else {
                        bat 'mvn clean package'
                    }
                }
            }
        }
        /*stage('Test') {
            steps {
                echo 'Testing..'
                // Here you can add test steps
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                // Here you can add deploy steps
            }
        }*/
    }
}