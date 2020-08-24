bazel build //services/aphrodite:aphrodite_service_container_image
bazel run //services/aphrodite:aphrodite_service_container_image

docker run --rm -it -p8080:8080 --link mysql-balenciaga-container:mysql --env-file=./dev.env bazel/services/aphrodite:aphrodite_service_container_image