package com.dev.immutable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public final class ImmutableClassExample {

	private final int id;
	private final String name;
	private final HashMap<String, String> testMap;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Accessor function for mutable objects
	 */
	public HashMap<String, String> getTestMap() {
		// return testMap;
		return (HashMap<String, String>) testMap.clone();
	}

	/**
	 * Constructor performing Deep
	 * 
	 * @param i
	 * @param n
	 * @param hm
	 */

	public ImmutableClassExample(int i, String n, HashMap<String, String> passedMap) {

		System.out.println("Performing Deep  for Object initialization");

		this.id = i;
		this.name = n;

		// Deep copy
		HashMap<String, String> tempMap = new HashMap<String, String>();
		for (Entry<String, String> passedMapEntry : passedMap.entrySet()) {
			tempMap.put(passedMapEntry.getKey(), passedMapEntry.getValue());
		}
		this.testMap = tempMap;
	}

	/**
	 * Constructor performing Shallow
	 * 
	 * @param i
	 * @param n
	 * @param hm
	 */

	// public ImmutableClassExample(int i, String n, HashMap<String, String> hm)
	// {
	// System.out.println("Performing Shallow for Object initialization");
	// this.id = i;
	// this.name = n;
	// this.testMap = hm;
	// }

	/**
	 * To test the consequences of Shallow and how to avoid it with Deep for
	 * creating immutable classes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		HashMap<String, String> h1 = new HashMap<String, String>();
		h1.put("1", "first");
		h1.put("2", "second");

		String s = "original";

		int i = 10;

		ImmutableClassExample immutableObject = new ImmutableClassExample(i, s, h1);

		// Lets see whether its copy by field or reference
		System.out.println(s == immutableObject.getName());
		System.out.println(h1 == immutableObject.getTestMap());

		// print the ce values
		System.out.println("ce id:" + immutableObject.getId());
		System.out.println("ce name:" + immutableObject.getName());
		System.out.println("ce testMap:" + immutableObject.getTestMap());

		// change the local variable values
		i = 20;
		s = "modified";
		h1.put("3", "third");

		// print the values again
		System.out.println("ce id after local variable change:" + immutableObject.getId());
		System.out.println("ce name after local variable change:" + immutableObject.getName());
		System.out.println("ce testMap after local variable change:" + immutableObject.getTestMap());

		HashMap<String, String> hmTest = immutableObject.getTestMap();
		hmTest.put("4", "new");

		System.out.println("ce testMap after changing variable from accessor methods:" + immutableObject.getTestMap());

	}

}
