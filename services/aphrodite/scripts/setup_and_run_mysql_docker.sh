docker run --name mysql-dev-container -p 6033:3306 -e MYSQL_ROOT_PASSWORD=<password> -d mysql:latest

docker exec -it mysql-dev-container bash;