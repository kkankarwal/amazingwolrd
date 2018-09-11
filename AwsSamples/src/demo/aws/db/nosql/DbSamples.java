package demo.aws.db.nosql;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DbSamples {

	public static void main(String[] args) {

		System.out.println("Program Stats ... ");

		AmazonDynamoDB dbClient = getAwsDynamoDbClient();

		//DynamoDbUtilities.createTableRequest(dbClient, "TEMP");
		//DynamoDbUtilities.describeTables(dbClient, "PORTFOLIO");
		//DynamoDbUtilities.listTables(dbClient);
		
		//DynamoDbUtilities.queryTable(dbClient, "STOCKS", "#a", "id", "8");
		DynamoDbUtilities.deletetable(dbClient, "STOCKS");
		System.out.println("Program Exits ... ");
	}

	private static AmazonDynamoDB getAwsDynamoDbClient() {
		// AmazonDynamoDB client =
		// AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();

		String region = "us-east-1";
		String dynamodbEndPoint = "dynamodb.us-east-1.amazonaws.com";
		String accessKey = {USE YOUR OWN KEY};
		String secretAccessKey = {USE YOUR OWN SECRET KEY};

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
				secretAccessKey);
		AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(
								dynamodbEndPoint, region)).build();
		return dbClient;
	}
}
