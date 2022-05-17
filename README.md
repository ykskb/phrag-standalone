# Standalone Phrag

**Stand-alone Version of [Phrag](https://github.com/ykskb/phrag)**

**Instantly runnable**: artifacts in this repository can be used if GraphQL is desired for a RDBMS without any authentication or customization. They are also suitable for just playing around with Phrag.

For customization use cases, please refer to [Phrag](https://github.com/ykskb/phrag) library.

### Run

#### As a docker container

Run with [self-contained](db/meetup_project.sql) database:

```sh
docker run -it -p 3000:3000 ykskb/phrag-standalone:latest
```

Run with JDBC URL to a custom SQLite DB:

```sh
docker run -it -p 3000:3000 \
-e JDBC_URL=jdbc:sqlite:path/to/db.sqlite \
ykskb/phrag-standalone:latest
```

Run with a custom PostgreSQL DB:

```sh
docker run -it -p 3000:3000 \
-e DB_CURRENT_SCHEMA=public \
-e DB_HOST=localhost \
-e DB_NAME=mydb \
-e DB_PASSWORD=mysecret \
-e DB_PORT=5432 \
-e DB_TYPE=postgresql \
-e DB_USER=mydbuser \
ykskb/phrag-standalone:latest
```

> Notes:
>
> To stop a running container of Phrag, simply press `CTRL + C`.

#### As a Java process

Build:

```sh
lein uberjar
```

Run:

```sh
java -jar target/phrag-standalone-0.1.0-standalone.jar
```

> Notes:
>
> To stop a running Java process of Phrag, simply press `CTRL + C`.

### Environment Variables

Env vars for a database connection can be provided to docker container or Java process.

If no variable is provided, Phrag process connects to a [self-contained SQLite DB](db/meetup_project.sql) by default.

- `JDBC_URL`: if a JDBC connection URL is provided, it'll supersede all other env vars below.
- `DB_TYPE`: a database type such as `postgresql`.

- `DB_HOST`: a host of a database.

- `DB_PORT`: a port of a database.

- `DB_USER`: a database user.

- `DB_PASSWORD`: a password for the database user.

- `DB_CURRENT_SCHEMA`: a current schema for PostgreSQL database. Typically `public`.
