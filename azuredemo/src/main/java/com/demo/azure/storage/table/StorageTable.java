package com.demo.azure.storage.table;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.UUID;

import com.demo.azure.storage.table.keys.Utility;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;
//import com.microsoft.azure.storage.util.Utility;

/**
 * This sample illustrates basic usage of the various Table Primitives provided
 * in the Storage Client Library including TableOperation, TableBatchOperation,
 * and TableQuery.
 */
public class StorageTable {

	protected static CloudTableClient tableClient;
	protected static CloudTable table;
	protected final static String tableName = "tablebasics1";
	
	public static void main(String[] args) throws InvalidKeyException, URISyntaxException {

		 // Setup the cloud storage account.
        CloudStorageAccount account = CloudStorageAccount.parse(Utility.storageConnectionString);
        
        // Create a table service client.
        tableClient = account.createCloudTableClient();

        
        try {
            // Retrieve a reference to a table.
            // Append a random UUID to the end of the table name so that this
            // sample can be run more than once in quick succession.
            //table = tableClient.getTableReference(tableName + UUID.randomUUID().toString().replace("-", ""));
        	
        	table = tableClient.getTableReference(tableName);

            // Create the table if it doesn't already exist.
            table.createIfNotExists();

            // List the tables with a given prefix.
            Iterable<String> listTables = tableClient.listTables(tableName, null, null);
            for (String s : listTables) {
                System.out.println(s);
            }
                    
            
           /* createNewRecordInTableStorage("Kamal", "Kankarwal", "v@gmail.com", "1234567890");
            createNewRecordInTableStorage("Vimla", "Kankarwal", "v@gmail.com", "1234567890");
            createNewRecordInTableStorage("Nidhi", "Kankarwal", "v@gmail.com", "1234567890");
            createNewRecordInTableStorage("GAURANSH", "Kankarwal", "v@gmail.com", "1234567890");*/
            
            TableQuery<CustomerEntity> partitionQuery = TableQuery.from(CustomerEntity.class);
            for (CustomerEntity entity : table.execute(partitionQuery)) {
                System.out.println(entity);
            }
           // basicQuery();
            
            // Delete the table.
            table.deleteIfExists();

        }
        catch (Throwable t) {
            Utility.printException(t);
        }

	}

	private static void createNewRecordInTableStorage(String firstName, String lastName, String email, String phoneNumber) throws StorageException {
		// Create a new customer entity.
		CustomerEntity customer1 = new CustomerEntity(firstName, lastName);
		customer1.setEmail(email);
		customer1.setPhoneNumber(phoneNumber);

		// Create an operation to add the new customer to the tablebasics table.
		TableOperation insertCustomer1 = TableOperation.insert(customer1);

		// Submit the operation to the table service.
		table.execute(insertCustomer1);
	}
	
	/**
     * Illustrates how to form and execute a query operation.
     * 
     * @throws StorageException
     */
    public static void basicQuery() throws StorageException {
        // Retrieve a single entity.
        // Retrieve the entity with partition key of "Smith" and row key of "Jeff".
        TableOperation retrieveSmithJeff = TableOperation.retrieve("Smith", "Jeff", CustomerEntity.class);

        // Submit the operation to the table service and get the specific entity.
        @SuppressWarnings("unused")
        CustomerEntity specificEntity = table.execute(retrieveSmithJeff).getResultAsType();

        // Retrieve all entities in a partition.
        // Create a filter condition where the partition key is "Smith".
        String partitionFilter = TableQuery.generateFilterCondition("PartitionKey", QueryComparisons.EQUAL, "Smith");

        // Specify a partition query, using "Smith" as the partition key filter.
        //TableQuery<CustomerEntity> partitionQuery = TableQuery.from(CustomerEntity.class).where(partitionFilter);
        
        TableQuery<CustomerEntity> partitionQuery = TableQuery.from(CustomerEntity.class);

        // Loop through the results, displaying information about the entity.
        for (CustomerEntity entity : table.execute(partitionQuery)) {
            System.out.println(entity.getPartitionKey() + " " + entity.getRowKey() + "\t" + entity.getEmail() + "\t"
                    + entity.getPhoneNumber());
        }
    }


}
