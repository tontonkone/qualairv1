# QUALAIR-api

Qualité de l’air : plateforme d’information et d’échange

## Technos

- Spring Boot


## Compilation / Usage

L'application vient avec 2 profils maven :

- `mariadb`
- `mysql`

Raison : Certains développeurs utilisaient une base mariadb en local, d'autre utilisaient une base mysql. Bien que nous utilisions le même connecteur `org.mariadb.jdbc:mariadb-java-client`, en fonction du serveur le dialect que JPA doit utiliser diffère (MariaDBDialect pour mariadb et MySQLDialect pour mysql), sinon certaines requêtes générées par JPA provoque des erreurs (notamment lors du `drop-and-create` d'hibernate).

Du coup, le profil maven permet de configurer le Dialect dans `application.properties`.  
On a choisi cette solution pour éviter d'avoir à reconfigurer le projet à chaque pull, on sélectionne une fois le profil avec l'ide et on ne s'en soucie plus.

Il faut donc bien penser à activer le bon profil en fonction du serveur de base de données utiliser à chaque compilation ou démarrage avec `mvn spring-boot:start`.

---

Config par défault :

Datasource:

url datasource: jdbc:mariadb://localhost:3306/airdb

username: root

password: [empty]

---

Exemple pour démarrer avec un serveur mariadb :

```shell
mvn clean compile -P mariadb
mvn spring-boot:start -P mariadb
```

