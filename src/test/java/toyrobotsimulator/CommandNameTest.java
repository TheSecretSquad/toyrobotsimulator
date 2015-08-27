package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandNameTest {
	
	private CommandName upperCaseName = new CommandName("TEST");
	private CommandName lowerCaseName = new CommandName("test");
	private CommandName commandName = new CommandName("Test");
	private CommandName commandNameOtherInstance = new CommandName("Test");
	private CommandName differentCommandName = new CommandName("DifferentCommandName");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenComparingEqual_IsValueEqual() {
		assertEquals(upperCaseName, upperCaseName);
	}
	
	@Test
	public void WhenComparingEqual_IsCaseInsensitive() {
		assertEquals(upperCaseName, lowerCaseName);
	}
	
	@Test
	public void WhenComparingEqual_AndIsDifferentValue_IsNotEqual() {
		assertNotEquals(commandName, differentCommandName);
	}
		
	@Test
	public void WhenComparingEqual_AndSameObject_IsEqual() {
		assertEquals(commandName, commandName);
	}
	
	@Test
	public void WhenComparingEqual_AndNull_IsNotEqual() {
		assertNotEquals(commandName, null);
	}
	
	@Test
	public void WhenComparingEqual_AndIsOtherType_IsNotEqual() {
		assertNotEquals(commandName, new Object());
	}
	
	@Test
	public void WhenComparingEqual_IsSymmetric() {
		assertTrue(commandName.equals(commandNameOtherInstance));
		assertTrue(commandNameOtherInstance.equals(commandName));
	}
	
	@Test
	public void WhenComparingEqual_AndGivenNullValue_IsEqualToCommandNameWithEmptyStringValue() {
		assertEquals(new CommandName(null), new CommandName(""));
	}
}