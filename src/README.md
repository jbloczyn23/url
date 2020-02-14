#Read Me
##Description
This is a service for creating shortened urls similar to bit.ly or goo.gl. The service
returns the shortened url back to the client and then once a client requests the
shortened url, the service sends a redirect back to the client to the full length url.
The service also supports querying for metrics on how often a given shortened url
has been requested.

##Prerequisites
Java version 1.8 or above (streams and lambda are used)  
Maven  
PostgreSQL  
PostrgeSQL database created named "cloudflare"  
    a. open up psql terminal (command = "psql")  
    b. create new database (command = "create database cloudflare")  
    
##Running Locally  
1. cd into /url
2. mvn clean install  
3. mvn spring-boot:run 

##API Endpoints With Examples
1. generate shortened url from full length url
    a. POST http://localhost:8080/url/create
    b. body { "longurl" : "https://google.com" }
    c. response { "longurl" : "https://google.com", "shorturl" : "http://localhost:8080/url/eDvWZdOM" }
2. get redirected based on the shorturl back to the full url
    a. GET http://localhost:8080/url/eDvWZdOM
    b. HTTP Status 302 with HTTP Header Location = https://google.com
3. get metrics about how often a given shortened url has been accessed in a given time period
    a. GET http://localhost:8080/url/eDvWZdOM/accessed/day
    b. valid time periods = day, week, all
    c. response { "url" : "http://localhost:8080/url/eDvWZdOM", "timePeriod":"all", "timesAccessed":1, "timestamps" : ["2020-02-13T22:15:06.437"] }
    d. HTTP Status 400 with "Invalid time period.  Please use one of the following: day, week or all" for invalid time period

##Assumptions
1. The service assumes that the inputted full length urls are valid urls
2. The service assumes that the requested shortened urls are valid shortened urls

##Design Decisions
1. The shortened url is made up of 8 randomly selected alphanumeric (A-Z, a-z, 0-9)
2. The service can support 62 ^ 8 (2.1834011e+14) shortened urls at the current 8 character length
3. The service does not handle bad redirect requests gracefully and currently throws a 500 due to a NPE. This could be changed to a meaningful error code at a later point (such as a 404)
