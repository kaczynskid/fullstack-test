Instantors Fullstack exercise
=========================
Welcome to Instantors fullstack exercise! 
This exercise is done to give you an opportunity to 
show case your coding style and how you think about problem solving. 
This exercise is usually paired with an interview were you will have the opportunity
to go through your solution to show and discuss your design choices.

Task
----
The task is to make this repository into an webapp/website that
presents information about a specific (or random) Star Wars character. 

Rules
-----
1. You should not need to change any front-end code to change the character information (if it contains errors).
2. The front-end may not communicate with any third-party resources. 
3. Have fun, and do not feel pressured to spend more then a couple of hours on this task.
4. Work on a personal fork and when you are done create a PR to [github.com/instantor/fullstack-test](https://github.com/instantor/fullstack-test).

Character Information
--------
The character information can either be static or
for extra credits it can be retrieved from [swapi.co](https://swapi.co) in the back-end.


Best of luck!

//Developers @ Instantor

## Solution description

### Building

1) Building front-end bundle:
    ```shell script
    cd react-frontend
    npm install
    npm run install
    ```
   Above will build a `zip` bundle and install it into local Maven repository.

2) Building front-end bundle:
    ```shell script
    cd spring-boot-backend
    ./gradlew build jar publishToMavenLocal
    ```
   Above will use previously installed frontend `zip` bundle and package singe `jar` application artifact.

3) Building Docker image
    ```shell script
    ./gradlew jibDockerBuild   
    ```
   Above will wrap previously packaged `jar` application artifact into application Docker image.

### Running

After building Docker image run:
```shell script
docker-compose -p instantor up -d
```
Above Docker Compose setup will run both required Redis persistence container and application container.

The application is now available under `http://localhost:8080`

### Running for frontend development on stubs

To run the stub server using stubs artifact in your local Maven repository run:
```shell script
npm run stubs
```
This will make the backend subs available under `http://localhost:8080` - exactly where development proxy would expect the real app.

No you can develop frontend as usual by running:
```shell script
npm run start
``` 
This will run you local frontend development server and proxy all backed API calls to the stub server run above. 

### Considerations

Frontend part is very simplistic and raw - I know. It does reflect my minimal proficiency level at Web UI development. It's entirely based on simple online tutorials and I hope it's not too compromising. It is jus the way I was able to patch it up. No real design there was intended, maybe except for components division. I chose to display less data that comes in from the backend to not bother laying it out on the page.

Most important in frontend for me is the fact that it produces a `zip` archive as artifact I can deploy to local repository and use as a dependency for the backend app.

Another notable thing in frontend is the `stubs` task in `package.json`. This allows to run the backend stubs generated based on backend API contracts. This is a part of CDC process between backend and frontend that allows establishing API integration layer first and in a formal manner of a contract. This contract is later used by both sides to implement and verify against each implementation. This approach also allows to implement both the backend and frontend in parallel after the contract has been established. It also allows to develop frontend without the need for running the backend. Useful when the cross functional team has experts in both frontend and backend to allow seamless cooperation without stepping on each other's toes.

Backend part contains more code that is needed. In this simple use case it was hard to showcase the approach to developing an application with some actual business logic. I am aware that `character` package is ove engineered for this particular use case, but it it rarely the case that the actual requirements are that simplistic. If I were to create a simple read-only interface I'd not re-write the model, but rather made sure that what I'm getting from persistence service is what I' going to send over to the frontend.

To make things a little bit interesting I added a simple persistence layer with Redis. The data is retrieved from [swapi.co](https://swapi.co), but since this service has and API call limitation it is retrieved only once, upon application startup, saved, and then read from Redis persistence.
