package net.liftweb.seventhings.lib;

import scala.PartialFunction;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

interface MatchClause<T> {
	public boolean test (T subject);
	public Object  apply(T subject);
}

interface F0V { //extends PartialFunction<Void, Object> {
	public Object apply();
}


class Matcher<T> {

	private final List<MatchClause<T>> matchers;

    public static <T> Matcher<T> matcher(MatchClause<T>... matchers) {
        return new Matcher<T>(matchers);
    }

	public Matcher(MatchClause<T>... matchers) {
		this.matchers = Collections.unmodifiableList(Arrays.asList(matchers));
	}

	public F0V match(final T subject) {
		return new F0V() {
			public Object apply() {
				for(MatchClause<T> matcher: matchers) {
					if(matcher.test(subject)) {
						return matcher.apply(subject);
					}
				}
                return null; // TODO should return Empty
			}
		};
	}

}

/*
class liftjmatch {
	public static void main(String[] args) {

		final MatchClause<Object> mString = new MatchClause<Object>() {
			public boolean test(Object x) { return x instanceof String; }

			public void apply(Object x) { System.out.println("String: " + x); }
		};

		final MatchClause<Object> mInteger = new MatchClause<Object>() {
			public boolean test(Object x) { return x instanceof Integer; }

			public void apply(Object x) { System.out.println("Integer: " + x); }
		};

		final MatchClause<Object> mOther = new MatchClause<Object>() {
			public boolean test(Object x) { return true; }

			public void apply(Object x) { System.out.println("Other: " + x); }
		};

		Matcher<Object> m = Matcher.matcher(
                mString,
                mInteger,
                mOther
        );

		m.match("Hello World").apply();
		m.match(1).apply();
	}
}
*/

