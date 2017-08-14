package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateTests.class, MoveTests.class, ReactionTests.class, RotationTests.class, UndoTests.class })
public class AllTests {

}
