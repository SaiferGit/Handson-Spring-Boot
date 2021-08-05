Install Postgres:

```
sudo apt update
sudo apt install postgresql postgresql-contrib

// creating new user
createuser --interactive

```

Accessing Postgres CLI:

```
sudo -i -u postgres
psql
```

Start or restart postgres server:

```
sudo systemctl start postgresql
// or
sudo systemctl restart postgresql
```

- List of all TCP ports: `sudo lsof -nP -iTCP -sTCP:LISTEN`
- To view all the database: `\l`
- To exit rom psql: `\q`
- To create a DB: `CREATE DATABASE <dbname>;`
- To view all Users: `\du`
- Granting permission a db to a specific user: `GRANT ALL PRIVILEGES ON DATABASE "student" TO saif;`
- To connect with a db: `\c <db name>`
- To see relations in db: `\d`

For now we just have created our new db, we don't have any tables in our db.
