echo
====

Echo Server - dictionary based content storage

Runs on default port 9090, could be changed in the configuration file (e.g. application.conf -> echo.port = 8080). <br>
To start the server execute from project root directory:

mvn package && java -jar target/echo-0.1.0.one-jar.jar

Usage examples:

- to store content on path /pulse <br>
    PUT / POST http://localhost:9090/pulse, content sent as request body
    
    
- to retrieve content from path /pulse <br>
    GET http://localhost:9090/pulse

- to remove content from path /pulse <br>
    DELETE http://localhost:9090/pulse