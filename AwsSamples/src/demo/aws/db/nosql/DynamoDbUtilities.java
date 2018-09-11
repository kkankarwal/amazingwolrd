package demo.aws.db.nosql;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputDescription;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

/**
 * https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/java/example_code
 * /dynamodb/src/main/java/aws/example/dynamodb
 * 
 * @author kkanka
 *
 */
public class DynamoDbUtilities {

	public static void createTableRequest(final AmazonDynamoDB ddb,
			final String tableName) {

		System.out.println("Create Tables Stats ... ");
		CreateTableRequest request = new CreateTableRequest()
				.withAttributeDefinitions(
						new AttributeDefinition("Name", ScalarAttributeType.S))
				.withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
				.withProvisionedThroughput(
						new ProvisionedThroughput(new Long(1), new Long(1)))
				.withTableName(tableName);

		// final AmazonDynamoDB ddb =
		// AmazonDynamoDBClientBuilder.defaultClient();

		try {
			System.out
					.format("Creating table \"%s\" with a simple primary key: \"Name\".\n",
							tableName);
			CreateTableResult result = ddb.createTable(request);
			System.out.println(result.getTableDescription().getTableName());
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}

		System.out.println("Create Tables Completes ... ");
	}

	public static void listTables(final AmazonDynamoDB ddb) {
		System.out.println("List Tables Stats ... ");
		System.out.println("Your DynamoDB tables:");

		ListTablesRequest request;

		boolean more_tables = true;
		String last_name = null;

		while (more_tables) {
			try {
				if (last_name == null) {
					request = new ListTablesRequest().withLimit(10);
				} else {
					request = new ListTablesRequest().withLimit(10)
							.withExclusiveStartTableName(last_name);
				}

				ListTablesResult table_list = ddb.listTables(request);
				List<String> table_names = table_list.getTableNames();

				if (table_names.size() > 0) {
					for (String cur_name : table_names) {
						System.out.format("* %s\n", cur_name);
					}
				} else {
					System.out.println("No tables found!");
					System.exit(0);
				}

				last_name = table_list.getLastEvaluatedTableName();
				if (last_name == null) {
					more_tables = false;
				}

			} catch (AmazonServiceException e) {
				System.err.println(e.getErrorMessage());
				System.exit(1);
			}
		}
		System.out.println("List Tables Stats ... ");

	}

	public static void describeTables(final AmazonDynamoDB ddb, String tableName) {

		System.out.format("Getting description for %s\n\n", tableName);

		TableDescription table_info = ddb.describeTable(tableName).getTable();

		ProvisionedThroughputDescription throughput_info = table_info
				.getProvisionedThroughput();

		System.out.println("Throughput");
		System.out.format("  Read Capacity : %d\n", throughput_info
				.getReadCapacityUnits().longValue());
		System.out.format("  Write Capacity: %d\n", throughput_info
				.getWriteCapacityUnits().longValue());

		List<AttributeDefinition> attributes = table_info
				.getAttributeDefinitions();
		System.out.println("Attributes");
		for (AttributeDefinition a : attributes) {
			System.out.format("  %s (%s)\n", a.getAttributeName(),
					a.getAttributeType());
		}
	}

	public static void queryTable(AmazonDynamoDB ddb, String table_name,
			String partition_alias, String partition_key_name,
			String partition_key_val) {

		System.out.format("Querying %s", table_name);
		System.out.println("");

		// set up an alias for the partition key name in case it's a reserved
		// word
		HashMap<String, String> attrNameAlias = new HashMap<String, String>();
		attrNameAlias.put(partition_alias, partition_key_name);

		// set up mapping of the partition name with the value
		HashMap<String, AttributeValue> attrValues = new HashMap<String, AttributeValue>();
		attrValues.put(":" + partition_key_name,
				new AttributeValue().withS(partition_key_val));

		QueryRequest queryReq = new QueryRequest()
				.withTableName(table_name)
				.withKeyConditionExpression(
						partition_alias + " = :" + partition_key_name)
				.withExpressionAttributeNames(attrNameAlias)
				.withExpressionAttributeValues(attrValues);

		try {
			QueryResult response = ddb.query(queryReq);
			System.out
					.println("Returned results count: " + response.getCount());
			System.out.println("Results :: " + response.getItems());
		} catch (AmazonDynamoDBException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
		System.out.println("Done!");
	}

	public static void deletetable(AmazonDynamoDB ddb,
			String table_name) {
		System.out.println("Deleting Table : " + table_name);
		try {
			ddb.deleteTable(table_name);
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
	}
}
