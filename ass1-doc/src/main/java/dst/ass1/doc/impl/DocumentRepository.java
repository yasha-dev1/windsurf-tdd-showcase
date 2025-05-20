package dst.ass1.doc.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dst.ass1.doc.IDocumentRepository;
import dst.ass1.jpa.model.ILocation;
import dst.ass1.jpa.util.Constants;
import org.bson.Document;

import java.util.Map;

/**
 * Implementation of the IDocumentRepository interface for MongoDB.
 * Provides methods for storing location data in MongoDB.
 */
public class DocumentRepository implements IDocumentRepository {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> locationCollection;

    public DocumentRepository() {
        // Connect to MongoDB
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase(Constants.MONGO_DB_NAME);
        this.locationCollection = database.getCollection(Constants.COLL_LOCATION_DATA);
    }

    @Override
    public void insert(ILocation location, Map<String, Object> locationProperties) {
        // Create a new document with location data
        Document document = new Document();
        
        // Add the location ID as a NumberLong
        document.append(Constants.I_LOCATION, location.getLocationId());
        
        // Add the location name
        document.append(Constants.M_LOCATION_NAME, location.getName());
        
        // Add all additional properties
        for (Map.Entry<String, Object> entry : locationProperties.entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }
        
        // Insert the document into the collection
        locationCollection.insertOne(document);
    }
}
