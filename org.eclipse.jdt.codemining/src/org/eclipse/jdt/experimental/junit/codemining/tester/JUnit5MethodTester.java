package org.eclipse.jdt.experimental.junit.codemining.tester;

import org.eclipse.jdt.core.IMethod;

public class JUnit5MethodTester implements IJUnitMethodTester {

	public static final IJUnitMethodTester INSTANCE = new JUnit5MethodTester();

	private final String[] JUNIT_TEST_ANNOTATIONS = new String[] { "Test", "org.junit.jupiter.api.Test", "ParameterizedTest", "org.junit.jupiter.params.ParameterizedTest" };

	@Override
	public boolean isTestMethod(IMethod method) {
		return IJUnitMethodTester.isTestMethod(method, false, JUNIT_TEST_ANNOTATIONS);
	}

}
