/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sapient.storm.demo.constant.CandidateNames;
import sapient.storm.demo.constant.Country;
import sapient.storm.demo.constant.NamedHashTags;
import sapient.storm.demo.vo.Location;
import twitter4j.Status;

public class NameTagUtil {

	private static Pattern HASHTAG_TRUMP2016 = Pattern.compile(NamedHashTags.TRUMP2016.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_TRUMP = Pattern.compile(NamedHashTags.TRUMP.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_NEVERTRUMP = Pattern.compile(NamedHashTags.NEVERTRUMP.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_DUMPTRUMP = Pattern.compile(NamedHashTags.DUMPTRUMP.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_DONALNDTRUMP = Pattern.compile(NamedHashTags.DONALNDTRUMP.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_DONALNDTRUMPPREZ = Pattern.compile(NamedHashTags.DONALNDTRUMPPREZ.name(), Pattern.CASE_INSENSITIVE);

	private static Pattern HASHTAG_FEELTHEBERN = Pattern.compile(NamedHashTags.FEELTHEBERN.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_SANDERS = Pattern.compile(NamedHashTags.SANDERS.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_BERNIESANDERS = Pattern.compile(NamedHashTags.BERNIESANDERS.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_BERNIESANDERS2016 = Pattern.compile(NamedHashTags.BERNIESANDERS2016.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_SANDERS2016 = Pattern.compile(NamedHashTags.SANDERS2016.name(), Pattern.CASE_INSENSITIVE);

	private static Pattern HASHTAG_HILLARY2016 = Pattern.compile(NamedHashTags.HILLARY2016.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_HILLARYCLINTON = Pattern.compile(NamedHashTags.HILLARYCLINTON.name(), Pattern.CASE_INSENSITIVE);

	private static Pattern HASHTAG_ATIMEFORTRUTH = Pattern.compile(NamedHashTags.ATIMEFORTRUTH.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_CRUZCREW = Pattern.compile(NamedHashTags.CRUZCREW.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_TEDCRUZ = Pattern.compile(NamedHashTags.TEDCRUZ.name(), Pattern.CASE_INSENSITIVE);
	private static Pattern HASHTAG_CRUZ2016 = Pattern.compile(NamedHashTags.CRUZ2016.name(), Pattern.CASE_INSENSITIVE);

	// ----------------------- Candidate Names Start -----------------//

	private static Pattern TRUMP2016 = Pattern.compile(NamedHashTags.TRUMP2016.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern TRUMP = Pattern.compile(NamedHashTags.TRUMP.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern NEVERTRUMP = Pattern.compile(NamedHashTags.NEVERTRUMP.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern DUMPTRUMP = Pattern.compile(NamedHashTags.DUMPTRUMP.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern DONALNDTRUMP = Pattern.compile(NamedHashTags.DONALNDTRUMP.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern DONALNDTRUMPPREZ = Pattern.compile(NamedHashTags.DONALNDTRUMPPREZ.getCandidateName(), Pattern.CASE_INSENSITIVE);

	private static Pattern FEELTHEBERN = Pattern.compile(NamedHashTags.FEELTHEBERN.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern SANDERS = Pattern.compile(NamedHashTags.SANDERS.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern BERNIESANDERS = Pattern.compile(NamedHashTags.BERNIESANDERS.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern BERNIESANDERS2016 = Pattern.compile(NamedHashTags.BERNIESANDERS2016.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern SANDERS2016 = Pattern.compile(NamedHashTags.SANDERS2016.getCandidateName(), Pattern.CASE_INSENSITIVE);

	private static Pattern HILLARY2016 = Pattern.compile(NamedHashTags.HILLARY2016.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern HILLARYCLINTON = Pattern.compile(NamedHashTags.HILLARYCLINTON.getCandidateName(), Pattern.CASE_INSENSITIVE);

	private static Pattern ATIMEFORTRUTH = Pattern.compile(NamedHashTags.ATIMEFORTRUTH.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern CRUZCREW = Pattern.compile(NamedHashTags.CRUZCREW.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern TEDCRUZ = Pattern.compile(NamedHashTags.TEDCRUZ.getCandidateName(), Pattern.CASE_INSENSITIVE);
	private static Pattern CRUZ2016 = Pattern.compile(NamedHashTags.CRUZ2016.getCandidateName(), Pattern.CASE_INSENSITIVE);

	// ----------------------- Candidate Names End ----------------------//

	public static NamedHashTags getCandidateName(Status t) {

		Matcher matcher = HILLARY2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.HILLARY2016;
		}
		
		matcher = HILLARYCLINTON.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.HILLARYCLINTON;
		}
		
		matcher = ATIMEFORTRUTH.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.ATIMEFORTRUTH;
		}
		
		matcher = CRUZCREW.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.CRUZCREW;
		}
		matcher = TEDCRUZ.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TEDCRUZ;
		}
		matcher = CRUZ2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.CRUZ2016;
		}
		
		matcher = TRUMP2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TRUMP2016;
		}
		matcher = TRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TRUMP;
		}
		matcher = NEVERTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.NEVERTRUMP;
		}
		matcher = DUMPTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DUMPTRUMP;
		}
		matcher = DONALNDTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DONALNDTRUMP;
		}
		matcher = DONALNDTRUMPPREZ.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DONALNDTRUMPPREZ;
		}
		matcher = FEELTHEBERN.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.FEELTHEBERN;
		}
		matcher = SANDERS.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.SANDERS;
		}
		matcher = BERNIESANDERS.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.BERNIESANDERS;
		}
		matcher = BERNIESANDERS2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.BERNIESANDERS2016;
		}
		matcher = SANDERS2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.SANDERS2016;
		}		



		return NamedHashTags.UNKNOWN;
	}

	public static NamedHashTags getHashTagId(Status t) {

		Matcher matcher = HASHTAG_HILLARY2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.HILLARY2016;
		}
		
		matcher = HASHTAG_HILLARYCLINTON.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.HILLARYCLINTON;
		}
		
		matcher = HASHTAG_ATIMEFORTRUTH.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.ATIMEFORTRUTH;
		}
		
		matcher = HASHTAG_CRUZCREW.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.CRUZCREW;
		}
		matcher = HASHTAG_TEDCRUZ.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TEDCRUZ;
		}
		matcher = HASHTAG_CRUZ2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.CRUZ2016;
		}
		 matcher = HASHTAG_TRUMP2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TRUMP2016;
		}
		matcher = HASHTAG_TRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.TRUMP;
		}
		matcher = HASHTAG_NEVERTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.NEVERTRUMP;
		}
		matcher = HASHTAG_DUMPTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DUMPTRUMP;
		}
		matcher = HASHTAG_DONALNDTRUMP.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DONALNDTRUMP;
		}
		matcher = HASHTAG_DONALNDTRUMPPREZ.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.DONALNDTRUMPPREZ;
		}
		matcher = HASHTAG_FEELTHEBERN.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.FEELTHEBERN;
		}
		matcher = HASHTAG_SANDERS.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.SANDERS;
		}
		matcher = HASHTAG_BERNIESANDERS.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.BERNIESANDERS;
		}
		matcher = HASHTAG_BERNIESANDERS2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.BERNIESANDERS2016;
		}
		matcher = HASHTAG_SANDERS2016.matcher(t.getText());
		if (matcher.find()) {
			return NamedHashTags.SANDERS2016;
		}		



		return NamedHashTags.UNKNOWN;
	}

}
