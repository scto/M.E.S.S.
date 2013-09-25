package jeu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import vaisseaux.PositionnementTest;

@RunWith(Suite.class)
@SuiteClasses({ PhysiqueTest.class, PositionnementTest.class })
public class AllTests {

}
