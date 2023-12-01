# QualairWebui

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.0.2.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.




## Usage

L'application ne nécessite pas de config particulière hormis le démarrage du back `QUALAIR-api` sur le port 8080 de la même machine.

L'application est utilisable dans son ensemble sans utilisateur connecté, sauf pour l'ajout de message dans un forum et la page de profil.

Pour créer un utilisateur, aller sur la page de connexion avec le bouton `Se connecter` en haut à droite puis en cliquant sur le lien `inscription`. (⚠️ Il n'y pas encore de retour visuel lorsque l'utilisateur est correctement créé.)


Fonctionnalités :

- La recherche par saisie de nom de commune est fonctionnelle. La recherche avec la carte n'est pas encore fonctionnelle.  
  - Lors d'une recherche, on est redirigé vers une page de résultat. Elle affiche les dernières données de conditions météo récupérées par le back.
  - ⚠️Pour l'instant pas de retour visuel si la ville n'est pas encore géré par le back ou si il n'y pas de données pour cette ville.
  - Pour que des infos apparaissent, il faut que la ville existe dans la table City (de la base) et qu'il y ai au moins un enregistrement dans la table `WeatherForecast` lié à la ville recherchée.
  - ⚠️Pour l'instant pas de données de qualité de l'air parce que leurs règles métiers n'étaient pas suffisamment géré dans le back.

- La page de recherche d'historique existe, mais nous avons manqué de temps pour trouver une librairie pour afficher un graphique donc la page de résultats de l'historique n'a pa été faite.

- La partie Forum n'a pas connecté au back.
  - Un forum déjà créé en dur.
  - Possibilité de créer des nouveaux forums, d'afficher leurs messages. Il est aussi possible d'ajouter des messages à condition d'être connecté.
  - ⚠️Pas de persistence de données entre rechargement, car non connecté au back.
  - ⚠️Pas de gestion des réactions.

- Page de profil utilisateur connecté au back et accessible quand un utilisateur est connecté par le menu déroulant en haut à droite.

- Le panel d'administration avec la page de gestion des utilisateurs, la page gestions des alertes et la page de gestion des forums n'a pas encore été fait.
