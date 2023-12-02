# shinpo-api

## Steps to create the local database
1) Install PostgreSQL 14 in your machine and make sure it is running.
2) Run the psql tool:
```
psql postgres
```
3) Create the db user:
```
create user shinpo_admin with encrypted password 'admin';
```
4) Create the database:
```
create database shinpo_db;
```
5) Grant the user access to the db:
```
grant all privileges on database shinpo_db to shinpo_admin;
```