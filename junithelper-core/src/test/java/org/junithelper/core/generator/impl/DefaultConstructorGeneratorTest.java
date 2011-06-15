package org.junithelper.core.generator.impl;

import org.junit.Before;
import org.junit.Test;
import org.junithelper.core.config.Configuration;
import org.junithelper.core.meta.ClassMeta;
import org.junithelper.core.meta.ConstructorMeta;
import org.junithelper.core.meta.extractor.ClassMetaExtractor;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultConstructorGeneratorTest {

	Configuration config = new Configuration();
	ClassMetaExtractor classMetaExtractor = new ClassMetaExtractor(config);
	ClassMeta targetClassMeta;
	DefaultConstructorGenerator target;

	@Before
	public void setUp() {
		String sourceCodeString = "package hoge.foo; import java.util.List; public class Sample { public Sample() {}\r\n public int doSomething(String str, long longValue) throws Throwable { System.out.println(\"aaaa\") } }";
		targetClassMeta = classMetaExtractor.extract(sourceCodeString);
		target = new DefaultConstructorGenerator();
	}

	@Test
	public void type() throws Exception {
		assertNotNull(DefaultConstructorGenerator.class);
	}

	@Test
	public void instantiation() throws Exception {
		DefaultConstructorGenerator target = new DefaultConstructorGenerator();
		assertNotNull(target);
	}

	@Test
	public void getAllInstantiationSourceCodeList_A$ClassMeta()
			throws Exception {
		// given
		ClassMeta classMeta = targetClassMeta;
		// e.g. : given(mocked.called()).willReturn(1);
		// when
		List<String> actual = target
				.getAllInstantiationSourceCodeList(classMeta);
		// then
		// e.g. : verify(mocked).called();
		assertEquals("		Sample target = new Sample();\r\n", actual.get(0));
	}

	@Test
	public void getFirstInstantiationSourceCode_A$ClassMeta() throws Exception {
		// given
		ClassMeta classMeta = targetClassMeta;
		// e.g. : given(mocked.called()).willReturn(1);
		// when
		String actual = target.getFirstInstantiationSourceCode(classMeta);
		// then
		// e.g. : verify(mocked).called();
		assertEquals("		Sample target = new Sample();\r\n", actual);
	}

	@Test
	public void getInstantiationSourceCode_A$ClassMeta$ConstructorMeta()
			throws Exception {
		// given
		ClassMeta classMeta = targetClassMeta;
		ConstructorMeta constructorMeta = target.getFirstConstructor(classMeta);
		// e.g. : given(mocked.called()).willReturn(1);
		// when
		String actual = target.getInstantiationSourceCode(classMeta,
				constructorMeta);
		// then
		// e.g. : verify(mocked).called();
		assertEquals("		Sample target = new Sample();\r\n", actual);
	}

	@Test
	public void getFirstConstructor_A$ClassMeta() throws Exception {
		// given
		ClassMeta classMeta = targetClassMeta;
		// e.g. : given(mocked.called()).willReturn(1);
		// when
		ConstructorMeta actual = target.getFirstConstructor(classMeta);
		// then
		// e.g. : verify(mocked).called();
		assertNotNull(actual);
	}

}
