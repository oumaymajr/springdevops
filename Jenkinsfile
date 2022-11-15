pipeline {
    environment  {
        registry = "medalibouchhioua/tpachat"
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.10.0.140:8081"
        NEXUS_REPOSITORY = "deployRepo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
        registryCredential = "87406a5a-32fa-4ef3-9133-848e1eb8cafa"
        dockerImage =''
    }

    agent any
    stages {

        stage('MVN clean'){
            steps{
                echo 'Cleaning Project '
                echo "Maven Version "
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('MVN COMPILE'){
            steps{
                script{
                    sh 'mvn clean install -DskipTests'
                }
            }
        }
        stage("Runing Tests with Mockito") {
            steps{
                sh 'mvn test'
            }
        }
        stage("MVN SonarQube") {
            steps{
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Admin -DskipTests -X'
            }
        }
        stage('Mvn Nexus'){
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                                nexusVersion: NEXUS_VERSION,
                                protocol: NEXUS_PROTOCOL,
                                nexusUrl: NEXUS_URL,
                                groupId: pom.groupId,
                                version: pom.version,
                                repository: NEXUS_REPOSITORY,
                                credentialsId: NEXUS_CREDENTIAL_ID,
                                artifacts: [
                                        [artifactId: pom.artifactId,
                                         classifier: '',
                                         file: artifactPath,
                                         type: pom.packaging],
                                        [artifactId: pom.artifactId,
                                         classifier: '',
                                         file: "pom.xml",
                                         type: "pom"]
                                ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }

        }
               stage('Building Docker Image') {
                            steps {

                                    sh 'docker build -t medalibouchhioua/tpachat .'

                                    }
                                }
                        stage('Login to DockerHub') {
                            steps{

                                    sh 'docker login -u medalibouchhioua -p dockerpassword'

                                }
                            }
                        stage('Push to DockerHub') {
                            steps{

                                    sh 'docker push medalibouchhioua/tpachat'
                                     }

                            }
                        stage('Docker Compose'){
                            steps{

                                    sh 'docker-compose up -d'

                               }
                            }

    }


}