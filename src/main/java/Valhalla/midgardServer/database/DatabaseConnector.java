package Valhalla.midgardServer.database;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnector {
	private MongoClient m_client;
	private MongoDatabase m_database;
	private MongoCollection<Document> m_collection;
	public int x;
	private String m_databaseName;
	private String m_collectionName;

	public DatabaseConnector(String p_databaseName, String p_collectionName) {
		m_databaseName = p_databaseName;
		m_collectionName = p_collectionName;

	}

	public void Connect() {
//		 MongoClient mongoClient = new MongoClient(new
//		 MongoClientURI(mongodblocalhost27017));
//		 MongoClient mongoClient = new MongoClient();
//		 MongoDatabase database = mongoClient.getDatabase(bunitaoDB);
//		 MongoCollectionDocument collection =
//		 database.getCollection(tester);
//		 collection.insertOne(new Document(hi, 100));

		m_client = new MongoClient();
		m_database = m_client.getDatabase(m_databaseName);
		m_collection = m_database.getCollection(m_collectionName);
		x=1;
	}

	public void Disconnect() {
		m_client.close();
	}

	public void InsertOne(Document p_toInsert) {
		Connect();
		m_collection.insertOne(new Document("hi", x));
		this.x=this.x+1;
		Disconnect();
	}

	public void InsertMany() {
	}

	public void DeleteOne() {
	}

	public void UpdateOne() {
	}

	public void GetOne() {
	}

	public void GetAll() {
	}

	public String getDatabaseName() {
		return m_databaseName;
	}

	public void setDatabaseName(String m_databaseName) {
		this.m_databaseName = m_databaseName;
	}

	public String getCollectionName() {
		return m_collectionName;
	}

	public void setCollectionName(String m_collectionName) {
		this.m_collectionName = m_collectionName;
	}
}