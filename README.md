🛍️ E-commerce API

Ce projet est le **backend** d’une application e-commerce.  
Il est développé avec **Spring Boot** et expose une API REST permettant la gestion des produits, utilisateurs et commandes.

 🚀 Technologies utilisées
- Java 17+
- Spring Boot 3
- Spring Data JPA
- MySQL
- Maven
- Spring Security (optionnel)

 📂 Structure du projet
- `src/main/java` → Code source
- `src/main/resources/application.properties` → Configuration (base de données, etc.)
- `pom.xml` → Dépendances Maven

 ⚙️ Installation & exécution
1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/jihenrabouch/ecommerce-api.git
   cd ecommerce-api
Configurer la base de données MySQL
Crée une base de données ecommerce :

sql
Copier le code
CREATE DATABASE ecommerce;
Mets à jour src/main/resources/application.properties :

properties
Copier le code
spring.datasource.url=jdbc:mysql://localhost:4200/ecommerce
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe
Lancer l’application

bash
Copier le code
mvn spring-boot:run
📡 Endpoints principaux
GET /api/products → Liste des produits

POST /api/products → Ajouter un produit

PUT /api/products/{id} → Modifier un produit

DELETE /api/products/{id} → Supprimer un produit
Configurer la base de données MySQL
Crée une base de données ecommerce :

CREATE DATABASE ecommerce;


Mets à jour src/main/resources/application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe


Lancer l’application

mvn spring-boot:run

📡 Endpoints principaux

GET /api/products → Liste des produits

POST /api/products → Ajouter un produit

PUT /api/products/{id} → Modifier un produit

DELETE /api/products/{id} → Supprimer un produit

🔗 Lien associé

Frontend : https://github.com/jihenrabouch/ecommerce-frontend




