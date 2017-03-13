package Valhalla.MidgardWebserver.database;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Valhalla.MidgardWebserver.model.TemperatureRegister;

public class DatabaseConnector {
	private MongoClient m_client;
	private MongoDatabase m_database;
	private MongoCollection<Document> m_collection;

	private String m_databaseName;
	private String m_collectionName;

	public DatabaseConnector(String p_databaseName, String p_collectionName) {
		m_databaseName = p_databaseName;
		m_collectionName = p_collectionName;

	}

	public void Connect() {
		// MongoCredential credential =
		// MongoCredential.createCredential(userName, database, password);
		// MongoClient mongoClient = new MongoClient(new ServerAddress(),
		// Arrays.asList(credential));
		
		MongoClientURI uri = new MongoClientURI("mongodb://odin:newsand01@ds129050.mlab.com:29050/heroku_0lpfwdhp");
		m_client = new MongoClient(uri);
		
	/*	m_client = new MongoClient("localhost", 27017);
		m_database = m_client.getDatabase(m_databaseName);
		m_collection = m_database.getCollection(m_collectionName);*/
		
		
		m_database = m_client.getDatabase("heroku_0lpfwdhp");
		m_collection = m_database.getCollection("TemperatureRegistry");
	}

	public void Disconnect() {
		m_client.close();
	}

	public void InsertFirst() {
		Connect();
		TemperatureRegister v_toRegister = new TemperatureRegister(OffsetDateTime.now(ZoneOffset.UTC), (float) 23.6, 1,
				1);
		m_collection.insertOne(new Document(v_toRegister.toDocument()));
		Disconnect();
	}

	public TemperatureRegister InsertOne(TemperatureRegister p_toInsert) {
		Connect();
		System.out.println("conected");
		if (p_toInsert.getRegisterTime() == null)
			p_toInsert.setRegisterTime(OffsetDateTime.now(ZoneOffset.UTC));
		m_collection.insertOne(new Document(p_toInsert.toDocument()));
		Disconnect();
		return p_toInsert;
	}

	public void InsertMany() {
	}

	public void DeleteOne() {
	}

	public void UpdateOne() {
	}

	public long Count() {
		Connect();
		long v_count = m_collection.count();
		Disconnect();
		return v_count;
	}

	public TemperatureRegister GetFirst() {

		Connect();
		Document myDoc = m_collection.find().first();
		TemperatureRegister v_return = new TemperatureRegister(OffsetDateTime.parse(myDoc.getString("RegisterTime")),
				myDoc.getDouble("Temperature"), myDoc.getInteger("Hardware"), myDoc.getInteger("Sensor"));
		Disconnect();
		return v_return;
	}

	public TemperatureRegister GetLast() {
		Connect();
		Document v_document = (Document) m_collection.find().sort(new BasicDBObject("RegisterTime", -1)).first();
		TemperatureRegister v_return = new TemperatureRegister(v_document);
		Disconnect();
		return v_return;
	}

	public List<TemperatureRegister> GetAll() {
		Connect();
		MongoCursor<Document> cursor = m_collection.find().iterator();
		
		Disconnect();
		List<TemperatureRegister> target = new ArrayList<>();
		try {
		    while (cursor.hasNext()) {
		        target.add(new TemperatureRegister(cursor.next()));
		    }
		} finally {
		    cursor.close();
		}
		
		
		
		return target;
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
