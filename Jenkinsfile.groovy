
pipeline {
    environment  {
        registry = "nourhadrich/tpachat"

        registryCredential = "dockerHub"
        dockerImage =''
    }

    agent any
    stages {
            stage('Checkout GIT'){
                steps{
                    echo 'pulling ...';
                        git branch : 'nour',
                        url : 'https://github.com/oumaymajr/SpringAOP.git'

                }
            }



            stage('MVN CLEAN') {
                steps{
                    sh 'mvn clean'
                }
            }

           

            stage('MVN SONARQUBE') {
                steps{
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ApaMkvk8urqBe9q'
                }
            }
            stage('Building our image') {

                steps {

                    script {

                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }

            }

        }

            stage('Deploy our image') {

                steps {

                    script {

                        docker.withRegistry( '', registryCredential ) {

                        dockerImage.push()

                    }

                }

            }

        }

            stage('Cleaning up') {

                steps {

                    sh "docker rmi $registry:$BUILD_NUMBER"

            }

        }

    }


}
