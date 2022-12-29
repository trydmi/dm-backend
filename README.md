to run:

    cd dm-backend
    ./mvnw install
    docker-compose up
    
  endpoints:
  

 - /api/v1/riserva-netta/{date} - returns .xlsx file with report
 - /api/v1/riserva-netta/data/{date} - returns data for report in json body

report example:

<img width="587" alt="Screenshot at Dec 29 1-18-14 PM" src="https://user-images.githubusercontent.com/74013112/209943836-a52c1386-e675-413c-a2c1-16bb9dfe8cd1.png">
