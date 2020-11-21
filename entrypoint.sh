until nc -z -v -w30 $DB_HOST 3306; do
  echo "Waiting for database connection..."
  # wait for 5 seconds before check again
  sleep 5
done

java $JAVA_OPTS -Dserver.port=${SERVER_PORT} -Dspring.profiles.active=${ENVIRONMENT_NAME} -jar /book-store*.jar
