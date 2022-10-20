
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
    }


}