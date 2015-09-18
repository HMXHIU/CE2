import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextBuddyTest {


	@Test
	public void testTextBuddy() {
		TextBuddy.processFile("mytextfile.txt") ;
		
		assertEquals("Clear file before test", "all content deleted from mytextfile.txt", TextBuddy.commandClear());
		assertEquals("Wrong input test", "Wrong input of command. Please try agian.", TextBuddy.executeCommand("qwe"));
		assertEquals("add", ("Added to mytextfile.txt : \"qwe\""), TextBuddy.commandAdd("qwe"));
		assertEquals("add", ("No input detected! Please try agian."), TextBuddy.commandAdd(""));
		assertEquals("display", ("1. qwe\n"), TextBuddy.commandDisplay());
		assertEquals("delete 1", ("deleted from mytextfile.txt: \"qwe\""), TextBuddy.commandDelete(1));
		assertEquals("delete 1", ("Error! Could not found the number on list!"), TextBuddy.commandDelete(1));
		assertEquals("add", ("Added to mytextfile.txt : \"apple\""), TextBuddy.commandAdd("apple"));
		assertEquals("add", ("Added to mytextfile.txt : \"orange\""), TextBuddy.commandAdd("orange"));
		assertEquals("add", ("Added to mytextfile.txt : \"guava\""), TextBuddy.commandAdd("guava"));
		assertEquals("add", ("Added to mytextfile.txt : \"appleCare\""), TextBuddy.commandAdd("appleCare"));
		assertEquals("search", ("1. apple\n2. appleCare\nTotal of 2 results are found!\n"), TextBuddy.commandSearch("pp"));
		assertEquals("add", ("Added to mytextfile.txt : \"papaya\""), TextBuddy.commandAdd("papaya"));
		assertEquals("add", ("Added to mytextfile.txt : \"watermelon\""), TextBuddy.commandAdd("watermelon"));
		assertEquals("add", ("Added to mytextfile.txt : \"grape\""), TextBuddy.commandAdd("grape"));
		assertEquals("add", ("Added to mytextfile.txt : \"mango\""), TextBuddy.commandAdd("mango"));
		assertEquals("add", ("Added to mytextfile.txt : \"cherry\""), TextBuddy.commandAdd("cherry"));
		assertEquals("sort", ("The list is sorted in order!!"), TextBuddy.commandSort());
		assertEquals("display", ("1. apple\n2. appleCare\n3. cherry\n4. grape\n5. guava\n6. mango\n7. orange\n8. papaya\n9. watermelon\n"), TextBuddy.commandDisplay());
		assertEquals("search", ("The word is not found in the list!"), TextBuddy.commandSearch("bose"));
		
	}
}
