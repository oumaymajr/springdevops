
pipeline {
    agent any
    stages {
            stage('Checkout GIT'){
                steps{
                    echo 'pulling ...';
                        git branch : 'nour',
                        url : 'https://github.com/oumaymajr/SpringAOP.git'

                }
            }

            stage('date') {
                steps{
                    echo date
                }
            }

            stage('MVN CLEAN') {
                steps{
                    sh 'mvn clean'
                }
            }

            stage('MVN COMPILE') {
                steps{
                    sh 'mvn compile'
                }
            }

            stage('MVN SONARQUBE') {
                steps{
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ApaMkvk8urqBe9q'
                }
            }

    }


}