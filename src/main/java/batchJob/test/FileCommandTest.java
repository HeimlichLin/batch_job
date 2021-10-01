package batchJob.test;

import java.io.File;
import java.util.Arrays;

import batchJob.common.file.FileCommand;
import batchJob.common.file.impl.FileCommandFacade;

public class FileCommandTest {
	
	public static void main(final String[] args) {
		final FileCommandFacade fileCommandFactory = new FileCommandFacade();
		final FileCommand fileCommand = fileCommandFactory.getLazyFileCommand();
		try {
			fileCommand.createFile(new File("/TestFile/xxx.txt.flg"), Arrays.asList("flg"));
			fileCommand.createFile(new File("/TestFile/test1.txt"), Arrays.asList("content"));
			fileCommand.delete(new File("/TestFile/xxx.txt.flg"));
			fileCommand.renameTo(new File("/TestFile/test1.txt"), new File("/TestFile/test2.txt"));
			fileCommand.commit();
		} catch (final Exception e) {
			fileCommand.rollback();
		}

	}

	public static void TestAuto() {
		final FileCommandFacade fileCommandFactory = new FileCommandFacade();
		final FileCommand fileCommand = fileCommandFactory.getLazyFileCommand();
		try {
			fileCommand.createFile(new File("/TestFile/xxx.txt.flg"), Arrays.asList("flg"));
			fileCommand.createFile(new File("/TestFile/test1.txt"), Arrays.asList("content"));
			fileCommand.delete(new File("/TestFile/xxx.txt.flg"));
			fileCommand.renameTo(new File("/TestFile/test1.txt"), new File("/TestFile/test2.txt"));
		} catch (final Exception e) {
			fileCommand.rollback();
		}

	}

	public static void TestLazy() {
		final FileCommandFacade fileCommandFactory = new FileCommandFacade();
		final FileCommand fileCommand = fileCommandFactory.getLazyFileCommand();
		try {
			fileCommand.createFile(new File("/TestFile/xxx.txt.flg"), Arrays.asList("flg"));
			fileCommand.createFile(new File("/TestFile/test1.txt"), Arrays.asList("content"));
			fileCommand.delete(new File("/TestFile/xxx.txt.flg"));
			fileCommand.renameTo(new File("/TestFile/test1.txt"), new File("/TestFile/test2.txt"));
			fileCommand.commit();
		} catch (final Exception e) {
			fileCommand.rollback();
		}

	}

}
