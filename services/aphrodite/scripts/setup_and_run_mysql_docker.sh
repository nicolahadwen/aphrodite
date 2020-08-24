docker run --name mysql-balenciaga-container -p 6033:3306 -e MYSQL_ROOT_PASSWORD=<password> -d mysql:latest

docker exec -it mysql-balenciaga-container bash;