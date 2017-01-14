
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import program2.ObjectList;

/**
 * This test class is for JUnit test for the public methods inside of ObjectList
 * class. By creating a helper method inside of the testObjectList class, JUnit
 * test class call this helper method to make sure the modification or creation
 * of a list is right.
 *
 * @author Wallace He
 */
public class testObjectList {

    ObjectList testListA;
    ObjectList EmptyList;

    /**
     * this method is for setting up the testing lists going to be used for
     * testing method in the ObjectList class before the testing.
     */
    @Before
    public void setUp() {
        testListA = new ObjectList();
        testListA.insert(3);
        testListA.insert(2);
        testListA.insert(1);
        EmptyList = new ObjectList();
    }

    /**
     * This helper method is for testing the order and elements of an given
     * ObjectList is the same as the created testing Array. Returns ture if all
     * elements in the ObjectList match Array.
     *
     * @return return true if the method check that the number of entries in the
     * list matched the number of elements in array and the order of the
     * elements in the list match the order of element in array from beginning
     * to the end and from end to beginning.
     */
    public boolean helper(ObjectList List, Object[] testArray) {
        List.getFirst();
        if (List.getLength() == testArray.length) {
            for (int i = 0; i < testArray.length; i++) {
                if (testArray[i] != List.getCurrent()) {
                    //System.out.println(i + "case1");(debug)
                    return false;
                } else {
                    List.getNext();
                }
            }

            for (int i = testArray.length - 1; i >= 0; i--) {
                if (testArray[i] != List.getCurrent()) {
                    //System.out.println("case2");(debug)
                    return false;
                } else {
                    List.getPrevious();
                }
            }
            return true;
        }
        //System.out.println("case3");(debug)
        return false;

    }

    /**
     * This method is for testing default constructor in the ObjectList class
     */
    @Test
    public void testDefaultConstructor() {
        Object[] testArray = {};
        ObjectList A = new ObjectList();
        assertTrue(helper(A, testArray));
    }

    /**
     * This method is for testing getLength() in the ObjectList class
     */
    @Test
    public void testgetLength() {
        testListA.getLength();
        assertEquals(3, testListA.getLength());//test regular
        testListA.remove();
        assertEquals(2, testListA.getLength());
        testListA.remove();
        assertEquals(1, testListA.getLength());
        assertEquals(0, EmptyList.getLength());//test empty
    }

    /**
     * This method is for testing getCurrentPosition() in the ObjectList class
     */
    @Test
    public void testgetCurrentPosition() {
        assertEquals(1, testListA.getCurrentPosition());
        assertEquals(0, EmptyList.getCurrentPosition());//test empty
        testListA.getNext();
        assertEquals(2, testListA.getCurrentPosition());//test middle
        testListA.getNext();
        assertEquals(3, testListA.getCurrentPosition());//test last

    }

    /**
     * This method is for testing getCurrent() in the ObjectList class
     */
    @Test
    public void testgetCurrent() {
        assertEquals(1, testListA.getCurrent());//test first        
        testListA.getNext();
        assertEquals(2, testListA.getCurrent());//test middle
        testListA.getLast();
        assertEquals(3, testListA.getCurrent());//test last
        assertEquals(null, EmptyList.getCurrent());

    }

    /**
     * This method is for testing getCurrentPosition() in the ObjectList class
     */
    @Test
    public void testgetFirst() {
        assertEquals(1, testListA.getFirst());//test first 
        testListA.remove();
        assertEquals(2, testListA.getFirst());//test middle 
        testListA.remove();
        assertEquals(3, testListA.getFirst());//test last 
        assertEquals(null, EmptyList.getFirst());//test empty 

    }

    /**
     * This method is for testing getNext() in the ObjectList class
     */
    @Test
    public void testgetNext() {
        assertEquals(2, testListA.getNext()); //test first 
        assertEquals(3, testListA.getNext());//test next 
        assertEquals(3, testListA.getNext());//test last 
        assertEquals(null, EmptyList.getNext());//test empty

    }

    /**
     * This method is for testing getPrevious() in the ObjectList class
     */
    @Test
    public void testgetPrevious() {
        assertEquals(1, testListA.getPrevious());//test first 
        testListA.getNext();
        testListA.getNext();
        assertEquals(2, testListA.getPrevious());//test last  
        assertEquals(1, testListA.getPrevious());//test middle 
        assertEquals(1, testListA.getPrevious());//test first 
        assertEquals(null, EmptyList.getPrevious());//test empty 

    }

    /**
     * This method is for testing getLast() in the ObjectList class
     */
    @Test
    public void testgetLast() {

        assertEquals(3, testListA.getLast());//test last
        testListA.remove();
        assertEquals(2, testListA.getLast());//test middle 
        testListA.remove();
        assertEquals(1, testListA.getLast());//test first 
        assertEquals(null, EmptyList.getLast());//test empty 

    }

    /**
     * This method is for testing getLast() in the ObjectList class
     */
    @Test
    public void testappend() {

        EmptyList.append(2);
        Object[] testArray2 = {2};
        assertTrue(helper(EmptyList, testArray2));//test empty 
        testListA.append(4);
        Object[] testArray = {1, 2, 3, 4};
        assertTrue(helper(testListA, testArray));//test regular 

    }

    /**
     * This method is for testing insert() in the ObjectList class
     */
    @Test
    public void testinsert() {
        testListA.insert(3);
        EmptyList.insert(2);
        Object[] testArray = {3, 1, 2, 3};
        Object[] testArray2 = {2};
        assertTrue(helper(testListA, testArray));//test the first 
        testListA.getNext();
        testListA.insert(7);
        Object[] testArray3 = {3, 7, 1, 2, 3};
        assertTrue(helper(testListA, testArray3));//test next 
        testListA.getLast();
        testListA.insert(8);
        Object[] testArray4 = {3, 7, 1, 2, 8 , 3};
        assertTrue(helper(testListA, testArray4));//test last
        assertTrue(helper(EmptyList, testArray2));//test the empty 

    }

    /**
     * This method is for testing remove() in the ObjectList class
     */
    @Test
    public void testremove() {
        testListA.getNext();
        testListA.remove();
        Object[] testArray = {1, 3};
        assertTrue(helper(testListA, testArray));// remove middle
        testListA.getFirst();
        testListA.remove();
        Object[] testArray2 = {3};
        assertTrue(helper(testListA, testArray2));//remove first
        testListA.remove();
        EmptyList.remove();
        Object[] testArray3 = {};
        assertTrue(helper(EmptyList, testArray3));//remove last
        assertTrue(helper(testListA, testArray3));//remove empty

    }

    /**
     * This method is for testing replace() in the ObjectList class
     */
    @Test
    public void testreplace() {
        testListA.replace(4);
        Object[] testArray = {4, 2, 3};
        assertTrue(helper(testListA, testArray));//replace first
        testListA.getNext();
        testListA.replace(5);
        Object[] testArray2 = {4, 5, 3};
        assertTrue(helper(testListA, testArray2));//replace middle
        testListA.getLast();
        testListA.replace(5);
        Object[] testArray3 = {4, 5, 5};
        assertTrue(helper(testListA, testArray3));//replace last
        EmptyList.replace(5);
        Object[] testArray4 = {};
        assertTrue(helper(EmptyList, testArray4));

    }

    /**
     * This method is for testing clear() in the ObjectList class
     */
    @Test
    public void testclear() {
        testListA.clear();
        Object[] testArray = {};
        assertTrue(helper(testListA, testArray));
        assertTrue(helper(EmptyList, testArray));
    }

}
