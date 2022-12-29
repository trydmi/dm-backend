to run:

    cd dm-backend
    ./mvnw install
    docker-compose up
    
  endpoints:
  

 - /api/v1/riserva-netta/{date} - returns .xlsx file with report
 - /api/v1/riserva-netta/data/{date} - returns data for report in json body
