package com.gabrielfeo.backintheday.data.util;

public final class IntegerRange {

	private IntegerRange() { }

	public static Integer[] of(int startInclusive, int endExclusive) {
		int rangeSize = endExclusive - startInclusive;
		Integer[] range = new Integer[rangeSize];
		for (int i = 0; i < range.length; i++)
			range[i] = startInclusive + i;
		return range;
	}

}
