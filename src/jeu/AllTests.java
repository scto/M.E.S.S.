package jeu;

import objets.PositionnementTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PhysiqueTest.class, PositionnementTest.class })
public class AllTests {

}
