import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class TextBuddyTest {

	@Before
	public void init(){
		TextBuddy.processFile("mytextfile.txt") ;
	}
	
	@Test
	public void testClear(){
		assertEquals("Clear file before test", "all content deleted from mytextfile.txt\n", TextBuddy.commandClear());
	}

	@Test
	public void testAdd(){
		assertEquals("add", ("Added to mytextfile.txt : \"qwe\"\n"), TextBuddy.commandAdd("qwe"));
		assertEquals("add", ("No input detected! Please try agian.\n"), TextBuddy.commandAdd(""));
	}
	
	@Test
	public void testDelete(){
		assertEquals("Clear file before test", "all content deleted from mytextfile.txt\n", TextBuddy.commandClear());
		assertEquals("add", ("Added to mytextfile.txt : \"qwe\"\n"), TextBuddy.commandAdd("qwe"));
		assertEquals("delete 1", ("deleted from mytextfile.txt: \"qwe\"\n"), TextBuddy.commandDelete(1));
		assertEquals("delete 1", ("Error! Could not found the number on list!\n"), TextBuddy.commandDelete(1));
	}
	
	@Test
	public void testSearch(){
		assertEquals("Clear file before test", "all content deleted from mytextfile.txt\n", TextBuddy.commandClear());
		assertEquals("add", ("Added to mytextfile.txt : \"apple\"\n"), TextBuddy.commandAdd("apple"));
		assertEquals("add", ("Added to mytextfile.txt : \"orange\"\n"), TextBuddy.commandAdd("orange"));
		assertEquals("add", ("Added to mytextfile.txt : \"guava\"\n"), TextBuddy.commandAdd("guava"));
		assertEquals("add", ("Added to mytextfile.txt : \"appleCare\"\n"), TextBuddy.commandAdd("appleCare"));
		assertEquals("search", ("1. apple\n2. appleCare\nTotal of 2 results are found!\n"), TextBuddy.commandSearch("pp"));
		assertEquals("search", ("The word is not found in the list!\n"), TextBuddy.commandSearch("bose"));
	}
	
	@Test
	public void testSort(){
		assertEquals("Clear file before test", "all content deleted from mytextfile.txt\n", TextBuddy.commandClear());
		assertEquals("add", ("Added to mytextfile.txt : \"apple\"\n"), TextBuddy.commandAdd("apple"));
		assertEquals("add", ("Added to mytextfile.txt : \"orange\"\n"), TextBuddy.commandAdd("orange"));
		assertEquals("add", ("Added to mytextfile.txt : \"guava\"\n"), TextBuddy.commandAdd("guava"));
		assertEquals("add", ("Added to mytextfile.txt : \"appleCare\"\n"), TextBuddy.commandAdd("appleCare"));
		assertEquals("add", ("Added to mytextfile.txt : \"papaya\"\n"), TextBuddy.commandAdd("papaya"));
		assertEquals("add", ("Added to mytextfile.txt : \"watermelon\"\n"), TextBuddy.commandAdd("watermelon"));
		assertEquals("add", ("Added to mytextfile.txt : \"grape\"\n"), TextBuddy.commandAdd("grape"));
		assertEquals("add", ("Added to mytextfile.txt : \"mango\"\n"), TextBuddy.commandAdd("mango"));
		assertEquals("add", ("Added to mytextfile.txt : \"cherry\"\n"), TextBuddy.commandAdd("cherry"));
		assertEquals("sort", ("The list is sorted in order!!\n"), TextBuddy.commandSort());
		assertEquals("display", ("1. apple\n2. appleCare\n3. cherry\n4. grape\n5. guava\n6. mango\n7. orange\n8. papaya\n9. watermelon\n"), TextBuddy.commandDisplay());
	}
}
