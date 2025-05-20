package dst.ass1.doc.impl;

import com.mongodb.client.MongoDatabase;
import dst.ass1.doc.IDocumentQuery;
import dst.ass1.doc.IDocumentRepository;
import dst.ass1.doc.IDocumentServiceFactory;

/**
 * Factory for creating document service components.
 * Provides methods to create instances of DocumentQuery and DocumentRepository.
 */
public class DocumentServiceFactory implements IDocumentServiceFactory {

    @Override
    public IDocumentQuery createDocumentQuery(MongoDatabase db) {
        return new DocumentQuery(db);
    }

    @Override
    public IDocumentRepository createDocumentRepository() {
        return new DocumentRepository();
    }
}
