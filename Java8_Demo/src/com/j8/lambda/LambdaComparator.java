package com.j8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaComparator {

	public static void main(String[] args) {

		List<String> words = new ArrayList<>();
		words.add("ccc");
		words.add("BBB");
		words.add("aaa");

		words.add("CCC");
		words.add("bbb");
		words.add("AAA");

		System.out.println("Original >>>>>>>>> >>>>>>>>>  >>>>>> :: " + words);

		// Case - 01
		Collections.sort(words);
		System.out.println("Sorted >>>>>>>>>>>> >>>>>>>>>  >>>>>> :: " + words);

		// Case - 02
		Collections.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
				// return s1.compareToIgnoreCase(s2);
			}
		});

		System.out.println("Sort with Comparator >>>>>>>>> >>>>>> :: " + words);

		// Case - 03
		Collections.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});

		System.out.println("Sort with Comparator [Case inSensitive]:: " + words);

		// Case-04 - Lambda
		Comparator<String> cmp = (String s1, String s2) -> s1.compareToIgnoreCase(s2);
		Collections.sort(words, cmp);
		System.out.println("Sort with Lambda Comparator [Case inSensitive]:: " + words);

		// Iterator in Java8 (lambda style)
		words.forEach(x -> System.out.println(x));

		Predicate<String> pred = new Predicate<String>() {

			@Override
			public boolean test(String t) {
				return t.startsWith("a");
			}
		};

		for (String str : words) {
			if (pred.test(str))
				System.out.println(str);

		}

		// Lambda Style
		Predicate<String> predLambda = (str) -> str.startsWith("a");
		words.forEach(str -> {
			if (predLambda.test(str))
				System.out.println(str);
		});
	}

}
