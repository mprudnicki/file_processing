

import static org.junit.Assert.*;

import org.junit.Test;

public class FileProcesserTest {

	@Test
	public void testProcess() {
		
		//input data
		String filepath1 = "resources/test/case1.txt";
		String filepath2 = "resources/test/case2.txt";
		
		//expected output data
		String output1 = "Users: 5 Oldest: Karolina, Puchacka, 36, +48 123123123";
		String output2 = "Users: 7 Oldest: Adam, Abacki, 32, +34123123123";
		
		
		//test operations
		assertEquals(output1, FileProcesser.process(filepath1));
		assertEquals(output2, FileProcesser.process(filepath2));
	}

}
