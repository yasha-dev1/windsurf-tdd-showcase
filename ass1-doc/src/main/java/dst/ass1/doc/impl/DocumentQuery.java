package dst.ass1.doc.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import dst.ass1.doc.IDocumentQuery;
import dst.ass1.jpa.util.Constants;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IDocumentQuery interface for MongoDB.
 * Provides methods for querying geospatial data in MongoDB.
 */
public class DocumentQuery implements IDocumentQuery {

    private final MongoDatabase db;
    private final MongoCollection<Document> locationCollection;

    public DocumentQuery(MongoDatabase db) {
        this.db = db;
        this.locationCollection = db.getCollection(Constants.COLL_LOCATION_DATA);
    }

    @Override
    public Document calculateLocationDensity(List<List<Double>> coordinates) {
        // Create a polygon from the coordinates
        List<List<Double>> polygonCoordinates = new ArrayList<>(coordinates);
        
        // Ensure the polygon is closed (first and last points are the same)
        if (!coordinates.get(0).equals(coordinates.get(coordinates.size() - 1))) {
            polygonCoordinates.add(coordinates.get(0));
        }
        
        // Create a GeoJSON polygon
        Document polygon = new Document("type", "Polygon")
                .append("coordinates", List.of(polygonCoordinates));
        
        // Find all locations within the polygon
        long count = locationCollection.countDocuments(
                Filters.geoWithin("geo", polygon));
        
        // Calculate area in square kilometers
        double area = calculatePolygonArea(coordinates);
        
        // Calculate density (locations per square kilometer)
        double density = count / area;
        
        return new Document("count", count)
                .append("area", area)
                .append("density", density);
    }

    @Override
    public List<Document> findOpenRestaurantsInRadius(double centerLng, double centerLat, 
                                                     double radiusKm, int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23");
        }
        
        // Create a GeoJSON point for the center
        Point center = new Point(new Position(centerLng, centerLat));
        
        // Convert radius to meters for MongoDB
        double radiusMeters = radiusKm * 1000;
        
        // Find restaurants within radius that are open at the specified hour
        List<Document> results = new ArrayList<>();
        locationCollection.find(
                Filters.and(
                    Filters.eq("category", Constants.CATEGORY_RESTAURANT),
                    Filters.lte("openHour", hour),
                    Filters.gte("closingHour", hour),
                    Filters.nearSphere("geo", center, radiusMeters, null)
                )
        ).into(results);
        
        return results;
    }

    @Override
    public List<Document> findDocumentsByType(String type) {
        List<Document> results = new ArrayList<>();
        locationCollection.find(Filters.eq("type", type)).into(results);
        return results;
    }
    
    /**
     * Calculates the approximate area of a polygon in square kilometers.
     * Uses the shoelace formula (Gauss's area formula) for calculating the area.
     * 
     * @param coordinates List of coordinate pairs defining the polygon
     * @return Area in square kilometers
     */
    private double calculatePolygonArea(List<List<Double>> coordinates) {
        double area = 0.0;
        int j = coordinates.size() - 1;
        
        for (int i = 0; i < coordinates.size(); i++) {
            area += (coordinates.get(j).get(0) + coordinates.get(i).get(0)) * 
                   (coordinates.get(j).get(1) - coordinates.get(i).get(1));
            j = i;
        }
        
        // Convert to square kilometers (approximate conversion from degrees to km)
        // This is a simplified calculation and assumes a flat Earth
        double areaInSquareDegrees = Math.abs(area / 2.0);
        
        // Approximate conversion factor (varies by latitude)
        // Using average conversion factor for mid-latitudes
        double degToKmFactor = 111.0 * 111.0; // 1 degree ≈ 111 km at equator
        
        return areaInSquareDegrees * degToKmFactor;
    }
}
