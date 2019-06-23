package com.northcity.blog.util;
import java.util.concurrent.atomic.*;


public class IdGenerate {
	private static AtomicInteger syslogId = new AtomicInteger(0);

	private static AtomicInteger tagId = new AtomicInteger(0);

	private static AtomicInteger categoryId = new AtomicInteger(0);

	public static AtomicInteger getSyslogId() {
		syslogId = new AtomicInteger(syslogId.incrementAndGet());
		return syslogId;
	}

	public static AtomicInteger getTagId() {
		tagId = new AtomicInteger(tagId.incrementAndGet());
		return tagId;
	}

	public static AtomicInteger getCategoryId() {
		categoryId = new AtomicInteger(categoryId.incrementAndGet());
		return categoryId;
	}
}
