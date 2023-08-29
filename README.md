# quad-trivia

Play trivia with questions from the open trivia database!


The backend is built on Spring boot and requires gradle to run:
- ```cd Trivia```
- ```./gradlew build```
- ```./gradlew bootRun```
  
This will start a server on ```localhost:8080``` with REST endpoints ```/questions``` and ```/checkanswers```

The frontend is a simple react app, so requires node.js:
- ```cd Frontend/my-react-app```
- ```npm install```
- ```npm start```

This starts a webserver on ```localhost:3000``` and opens a browser.
