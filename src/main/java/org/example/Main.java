package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb+srv://sawsenelbahri:hellokarmin1@cluster0.uwoyvpj.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"; // Remplacez avec votre propre URI de connexion MongoDB
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("bibliotheque");
            MongoCollection<Document> auteurs = database.getCollection("auteurs");
            MongoCollection<Document> livres = database.getCollection("livres");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1: Ajouter un auteur");
            System.out.println("2: Ajouter un livre");
            System.out.println("3: Mettre à jour un auteur");
            System.out.println("4: Mettre à jour un livre");
            System.out.println("5: Supprimer un auteur");
            System.out.println("6: Supprimer un livre");
            System.out.print("Choix: ");
            int choix = Integer.parseInt(scanner.nextLine());

            if (choix == 1) {
                System.out.println("Entrez le nom de l'auteur :");
                String nom = scanner.nextLine();
                System.out.println("Entrez la date de naissance de l'auteur (format YYYY-MM-DD) :");
                String dateNaissance = scanner.nextLine();
                System.out.println("Entrez la nationalité de l'auteur :");
                String nationalite = scanner.nextLine();

                Document auteur = new Document("nom", nom)
                        .append("dateNaissance", dateNaissance)
                        .append("nationalite", nationalite);
                auteurs.insertOne(auteur);
                System.out.println("Auteur ajouté avec succès !");
            } else if (choix == 2) {
                System.out.println("Entrez le titre du livre :");
                String titre = scanner.nextLine();
                System.out.println("Entrez l'ISBN du livre :");
                String isbn = scanner.nextLine();
                System.out.println("Entrez l'ID de l'auteur :");
                String auteurId = scanner.nextLine();
                System.out.println("Entrez le genre du livre :");
                String genre = scanner.nextLine();

                Document livre = new Document("titre", titre)
                        .append("isbn", isbn)
                        .append("auteurId", new ObjectId(auteurId))
                        .append("genre", genre);
                livres.insertOne(livre);
                System.out.println("Livre ajouté avec succès !");
            } else if (choix == 3) {
                System.out.println("Entrez l'ID de l'auteur à mettre à jour :");
                String id = scanner.nextLine();
                System.out.println("Entrez le nouveau nom de l'auteur :");
                String nouveauNom = scanner.nextLine();

                UpdateResult result = auteurs.updateOne(new Document("_id", new ObjectId(id)),
                        new Document("$set", new Document("nom", nouveauNom)));
                if (result.getModifiedCount() > 0) {
                    System.out.println("Auteur mis à jour avec succès !");
                } else {
                    System.out.println("Aucun auteur mis à jour.");
                }
            } else if (choix == 4) {
                System.out.println("Entrez l'ID du livre à mettre à jour :");
                String id = scanner.nextLine();
                System.out.println("Entrez le nouveau titre du livre :");
                String nouveauTitre = scanner.nextLine();

                UpdateResult result = livres.updateOne(new Document("_id", new ObjectId(id)),
                        new Document("$set", new Document("titre", nouveauTitre)));
                if (result.getModifiedCount() > 0) {
                    System.out.println("Livre mis à jour avec succès !");
                } else {
                    System.out.println("Aucun livre mis à jour.");
                }
            } else if (choix == 5) {
                System.out.println("Entrez l'ID de l'auteur à supprimer :");
                String id = scanner.nextLine();

                DeleteResult result = auteurs.deleteOne(new Document("_id", new ObjectId(id)));
                if (result.getDeletedCount() > 0) {
                    System.out.println("Auteur supprimé avec succès !");
                } else {
                    System.out.println("Aucun auteur supprimé.");
                }
            } else if (choix == 6) {
                System.out.println("Entrez l'ID du livre à supprimer :");
                String id = scanner.nextLine();

                DeleteResult result = livres.deleteOne(new Document("_id", new ObjectId(id)));
                if (result.getDeletedCount() > 0) {
                    System.out.println("Livre supprimé avec succès !");
                } else {
                    System.out.println("Aucun livre supprimé.");
                }
            } else {
                System.out.println("Choix non valide.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            e.printStackTrace();
        }
        // je veux mettre mon projet sur github
            //  git init
            // git add .
            // git commit -m "first commit"
            // git remote add origin
            // git push -u origin master
        
    }
}
