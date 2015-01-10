package tests;

import obstacles.gameElement.GameElementNames;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import container.ServiceNames;
import table.Table;
import enums.Tribool;

/**
 * Tests unitaires pour Table
 * @author pf
 *
 */

public class JUnit_Table extends JUnit_Test {

	private Table table;
	
    @Before
    public void setUp() throws Exception {
        super.setUp();
        table = (Table) container.getService(ServiceNames.TABLE);
    }

    @Test
    public void test_clone() throws Exception
    {
    	Table cloned_table = table.clone();
    	Assert.assertTrue(table.equals(cloned_table));
        cloned_table.setDone(GameElementNames.CLAP_1, Tribool.TRUE);
    	Assert.assertTrue(!table.equals(cloned_table));
    	cloned_table.copy(table);
		Assert.assertTrue(table.isDone(GameElementNames.CLAP_1) == Tribool.TRUE);
		Assert.assertTrue(cloned_table.isDone(GameElementNames.CLAP_1) == Tribool.TRUE);
    	Assert.assertTrue(table.equals(cloned_table));
    	table.setDone(GameElementNames.CLAP_2, Tribool.TRUE);
    	Assert.assertTrue(!table.equals(cloned_table));
    }

}