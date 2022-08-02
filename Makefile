#!make

#Application Commands
run:
	docker-compose up --build
clean:
	docker-compose down --rmi all