package com.self.blog.controllers;

//Java code to show implementation of
//Guava's Lists.partition() method

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class dd {

	// Driver's code
	public static void main(String[] args)
	{
		// Creating a List of Integers
		List<Integer> myList
			= new ArrayList<>();

		// Using Lists.partition() method to divide
		// the original list into sublists of the same
		// size, which are just views of the original list.
		// The final list may be smaller.
		List<List<Integer> > lists
			= Lists.partition(myList, 2);

		// Displaying the sublists
		for (List<Integer> sublist: lists)
			System.out.println(sublist);
	}
}
