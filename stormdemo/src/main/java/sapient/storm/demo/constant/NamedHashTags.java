/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.constant;

public enum NamedHashTags {

	TRUMP2016("#Trump2016", "TRUMP"),
	TRUMP("#Trump", "TRUMP"),
	NEVERTRUMP("#NeverTrump", "TRUMP"),
	DUMPTRUMP("#DumpTrump", "TRUMP"),
	DONALNDTRUMP("#DonaldTrump", "TRUMP"),
	DONALNDTRUMPPREZ("#DonaldTrumpforPresient", "TRUMP"),
	
	FEELTHEBERN("#FeelTheBern", "SANDERS"),
	SANDERS("#Sanders", "SANDERS"),
	BERNIESANDERS("#BernieSanders", "SANDERS"),
	BERNIESANDERS2016("#BernieSanders2016", "SANDERS"),
	SANDERS2016("#Sanders2016", "SANDERS"),
	
	HILLARY2016("#Hillary2016", "HILLARY"),
	HILLARYCLINTON("#HillaryClinton", "HILLARY"), 
	
	ATIMEFORTRUTH("#ATimeforTruth", "CRUZ"),
	CRUZCREW("#CruzCrew", "CRUZ"), 
	TEDCRUZ("#TedCruz", "CRUZ"),
	CRUZ2016("#Cruz2016", "CRUZ"),
	
	UNKNOWN("UNKNOWN", "UNKNOWN");

	NamedHashTags(String name, String candidateName) {
		this.hashTag= name;
		this.candidateName = candidateName;
	}

	public String getHashTag() {
		return hashTag;
	}

	public String getCandidateName() {
		return candidateName;
	}

	private String hashTag;
	private String candidateName;
}