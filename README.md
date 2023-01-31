
## Weather Report Service Fore Cast Prediction
### Introduction to Weather Fore Cast
The project consists of a Spring Boot  microservice as a backend and React-Js as frontend. It provides an API exposed by taking cityname as an input and provides the weather cast and respective recommendation for the next three days.

### Note :
To run the backend service , java8+ should be installed and to run the frontend, node v16+ version should be installed in your system.

### Installations and Run :

For the Backend Service :-

* After having the above java8+ installed , open the backend service with name weather-report in either eclipse/intelliJ as per your preferable editor.
* Open the WeatherReportApplication class and run the project as `Spring Boot APP`. This should run the backend service in the port 8080.
* Or, you can use the Jenkinsfile that will build the sevice automatically once jenkins is configured.
* If you have a DOCKER installed in your system, just navigate to root source for backend and run below command.
        $sh docker-run.sh
* The above, docker comamnd should create the image and run the conatiner in the port 8080.
* Now, Open the Frontend Project with the name weather-report-react using either Visual Studio or any other preferred IDE.
* In the react root folder please do run below command using terminal and it will install all the necessary packages required to start the application.
        $ npm install
*  Once the npm installation is done, run the below command and the React will start the application in port 3000.
        $ npm start

Now, both the frontend and backend servers are UP and ready for usage.

### Design Approach & Explanation for the microservice:

We do have a controller layer and business layer pre-dominantly to get our required output.
* A contract(approximation ) is made by the Frontend vs Backend, to get the forecast and future temperatures for a particular city in a single API Call.
* Controller layer(Weather Report Controller) takes the input , city as parameter and routes the request to the Service Layer .
* Service layer (Weather Service Impl) is capable of calling the 3rd party API to get the complete data for the city .
* The Weather Service Impl class calls the Recommendation Service to make the necessary recommendations for the particular time band as well as future temperatures list.
* WeatherLinkBuilder interface is implemented for Weather Service Impl to make the String request to call 3rd party Data API.
* ForeCastProperties class from configuration package imports all the forecast properties from the service and stores in its variables.
* DataTimeManagement class in utils package primarily deals with the checking of day(i.e tomorrow, dayaftertomorrow etc).

### Request and Response Expected :

A Request with the following parameter should be made to get the required output from backend.

    $http://localhost:8080/api/get/city/forecast?cityname={CITYNAME}

* In the above request CITYNAME should be passed as a value i.e (${CITYNAME} as bangalore,london etc)
* If correct city is given the we get the following output :- <br />

Example :- 
 
   ``` {
    "message": "success",
    "predictionWindow": {
        "11/01/2023 02:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 05:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 08:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 11:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 14:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 17:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 20:30:00": "You Can Roam freely without umbrella",
        "11/01/2023 23:30:00": "You Can Roam freely without umbrella",
        "12/01/2023 02:30:00": "You Can Roam freely without umbrella",
        "12/01/2023 05:30:00": "Snow is Falling",
        "12/01/2023 08:30:00": "Snow is Falling",
        "12/01/2023 11:30:00": "Snow is Falling",
        "12/01/2023 14:30:00": "Snow is Falling",
        "12/01/2023 17:30:00": "Snow is Falling",
        "12/01/2023 20:30:00": "Snow is Falling",
        "12/01/2023 23:30:00": "Snow is Falling",
        "13/01/2023 02:30:00": "Snow is Falling",
        "13/01/2023 05:30:00": "Snow is Falling",
        "13/01/2023 08:30:00": "Snow is Falling",
        "13/01/2023 11:30:00": "Snow is Falling",
        "13/01/2023 14:30:00": "Snow is Falling",
        "13/01/2023 17:30:00": "Snow is Falling",
        "13/01/2023 20:30:00": "Snow is Falling",
        "13/01/2023 23:30:00": "Carry Umbrella"
    },
    "futureDayTemperatures": {
        "11/01/2023": [
            1.44,
            1.29,
            0.97,
            -2.99,
            -5.07,
            -5.71,
            -4.21,
            -0.21
        ],
        "12/01/2023": [
            1.14,
            0.85,
            0.41,
            -0.02,
            0.52,
            0.7,
            0.86,
            1.41
        ],
        "13/01/2023": [
            1.59,
            1.47,
            1.22,
            1.36,
            1.48,
            1.44,
            1.65,
            1.84
        ]
    }
}

```

* If wrong cityname or no city name is given then we get the following output with message as either 404 not found or 400 request bad.

Example :- 

```  {
    "message": "404 Not Found",
    "predictionWindow": null,
    "futureDayTemperatures": null
}
```

### Design patterns Implemented :

* SingleTon pattern is implemented while creating components. This should save memory without recreating new objects everytime.
* Builder Pattern in creating link for data API in the weatherReportService i.e weatherLinkBuilder
* Apart from above, SOLID principles with OPEN CLOSED SUBSTIUTION , Interface Segregation can be achieved by implementing the interfaces in Service Layer.

