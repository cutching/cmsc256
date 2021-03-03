/**********************************************************************************
 * CustomDateTest.java
 * *********************************************************************************
 * Project 3 - Test Class: Implementation of test methods to test CustomDate.java functionality
 * CMSC 256-901
 * 3/1/2021
 * Gavin Cutchin
 **********************************************************************************/
package Project3.cmsc256;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class CustomDateTest {
   CustomDate testDate;


    @Before
    public void setUp(){
        testDate = new CustomDate();
    }

    @Test
    public void testParameterlessConstructor(){
        assertTrue(1 == testDate.getMonth() && 1 == testDate.getDay() && 1900 == testDate.getYear() );
    }

    //Test the ToString method
    @Test
    public void testToString1(){
        assertTrue(testDate.toString().equals("01/01/1900"));
    }

    //Test the ToString method for leading zeros
    @Test
    public void testToString2(){
        testDate.setYear(1);
        assertTrue(testDate.toString().equals("01/01/0001"));
    }

    //Test the setMonth method
    @Test
    public void testSetMonth(){
        testDate.setMonth(5);
        assertTrue(testDate.getMonth() == 5);
    }
    //Test the setDay method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetMonthBadInput1(){
        testDate.setMonth(32);
    }
    //Test the setDay method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetMonthBadInput2(){
        testDate.setMonth(0);
    }

    //Test the setDay method
    @Test
    public void testSetDay(){
        testDate.setDay(25);
        assertTrue(testDate.getDay() == 25);
    }
    //Test the setDay method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetDayBadInput1(){
        testDate.setDay(32);
    }
    //Test the setDay method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetDayBadInput2(){
        testDate.setDay(0);
    }

    //Test the setYear method
    @Test
    public void testSetYear(){
        testDate.setYear(25);
        assertTrue(testDate.getYear() == 25);
    }
    //Test the setYear method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetYearBadInput1(){
        testDate.setYear(10000);
    }
    //Test the setYear method bad input
    @Test (expected = IllegalArgumentException.class)
    public void testSetYearBadInput2(){
        testDate.setYear(0);
    }

    //Test getMonth
    @Test
    public void testGetMonth(){
        CustomDate test = new CustomDate(5,6,2005);
        assertTrue(test.getMonth() == 5);
    }
    //Test getDay
    @Test
    public void testGetDay(){
        CustomDate test = new CustomDate(5,6,2005);
        assertTrue(test.getDay() == 6);
    }
    //Test getYear
    @Test
    public void testGetYear(){
        CustomDate test = new CustomDate(5,6,2005);
        assertTrue(test.getYear() == 2005);
    }

    //Tests standard constructor usage
    @Test
    public void testParameterConstructorWithGoodInts1(){
        CustomDate test = new CustomDate(5,20,2990);
        assertTrue(test.toString().equals("05/20/2990"));
    }

    //Tests leap year input
    @Test
    public void testParameterConstructorWithLeapYear1(){
        CustomDate test = new CustomDate(2,29,2000);
        assertTrue(test.toString().equals("02/29/2000"));
    }
    //Tests a bad leap year input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithLeapYear2(){
        CustomDate test = new CustomDate(2,29,2001);
    }

    //Tests a bad month input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithBadInts1(){
        CustomDate test = new CustomDate(50,1,1900);
    }
    //Tests a bad day input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithBadInts2(){
        CustomDate test = new CustomDate(1,50,1900);
    }
    //Tests a bad year input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithBadInts3(){
        CustomDate test = new CustomDate(1,1,50000);
    }
    //Tests a bad year input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithBadInts4(){
        CustomDate test = new CustomDate(1,1,0);
    }

    //Tests a bad month String input
    @Test (expected = IllegalArgumentException.class)
    public void testParameterConstructorWithABadString(){
        CustomDate test = new CustomDate("Juuly",1,1900);
    }

    //Tests a good month String input
    @Test
    public void testParameterConstructorWithAGoodString1(){
        CustomDate test = new CustomDate("July",1,1900);
        assertTrue(test.toString().equals("07/01/1900"));
    }
    //Tests a good month String input ignoring case
    @Test
    public void testParameterConstructorWithAGoodString2(){
        CustomDate test = new CustomDate("JULY",1,1900);
        assertTrue(test.toString().equals("07/01/1900"));
    }

    //Tests the days in month method
    @Test
    public void testDaysInMonth1(){
        assertTrue(testDate.daysInTheMonth() == 31);
    }
    //Tests the days in month method for february
    @Test
    public void testDaysInMonth2(){
        testDate.setMonth(2);
        testDate.setYear(2001);
        assertTrue(testDate.daysInTheMonth() == 28);
    }
    //Tests the days in month method for february for leap year
    @Test
    public void testDaysInMonth3(){
        testDate.setMonth(2);
        testDate.setYear(2000);
        assertTrue(testDate.daysInTheMonth() == 29);
    }

    //Could throw an exception
    //Tests the advance one week method
    @Test
    public void testAdvanceOneWeek1(){
        testDate.advanceOneWeek();
        assertTrue(testDate.toString().equals("01/08/1900"));
    }
    //Tests the advance one week method at the end of the calendar
    @Test
    public void testAdvanceOneWeek2(){
        testDate = new CustomDate(12,31,9999);
        testDate.advanceOneWeek();
        assertTrue(testDate.toString().equals("01/07/0000"));
    }
    //Tests the advance one week method at the end of the month
    @Test
    public void testAdvanceOneWeek3(){
        testDate.setDay(31);
        testDate.advanceOneWeek();
        assertTrue(testDate.toString().equals("02/07/1900"));
    }

    //Could throw an exception
    //Tests the advance one Day method
    @Test
    public void testAdvanceOneDay1() {
        testDate.advanceOneDay();
        assertTrue(testDate.toString().equals("01/02/1900"));
    }
    //Tests the advance one Day method at the end of the calendar
    @Test
    public void testAdvanceOneDay2(){
        testDate = new CustomDate(12,31,9999);
        testDate.advanceOneDay();
        assertTrue(testDate.toString().equals("01/01/0000"));
    }
    //Tests the advance one Day method at the end of the month
    @Test
    public void testAdvanceOneDay3() throws Exception {
        testDate.setDay(31);
        testDate.advanceOneDay();
        assertTrue(testDate.toString().equals("02/01/1900"));
    }

    //Tests isLeapYear for a false input
    @Test
    public void testIsLeapYear1(){
        assertFalse(testDate.isLeapYear());
    }
    //Tests isLeapYear for a true input
    @Test
    public void testIsLeapYear2(){
        testDate.setYear(2000);
        assertTrue(testDate.isLeapYear());
    }

    //Tests compareTo method with a object with the same information
    @Test
    public void testCompareTo1(){
        CustomDate newerDate = new CustomDate(1,1,1900);
        assertTrue(testDate.compareTo(newerDate) == 0);
    }
    //Tests compareTo method with a object with the future dates
    @Test
    public void testCompareTo2(){
        CustomDate newerDate = new CustomDate(1,1,2000);
        assertTrue(testDate.compareTo(newerDate) == -1);
    }
    //Tests compareTo method with a object with the past dates
    @Test
    public void testCompareTo3(){
        CustomDate newerDate = new CustomDate(1,1,50);
        assertTrue(testDate.compareTo(newerDate) == 1);
    }
    //Tests compareTo method with a customDate in the same year
    @Test
    public void testCompareTo4(){
        CustomDate newerDate = new CustomDate(2,5,1900);
        assertTrue(testDate.compareTo(newerDate) == -1);
    }
    //Tests compareTo method with a customDate in the same year and the same month
    @Test
    public void testCompareTo5(){
        testDate.setDay(30);
        CustomDate newerDate = new CustomDate(1,29,1900);
        assertTrue(testDate.compareTo(newerDate) == 1);
    }

    //Test the equals() method with true input
    @Test
    public void testEquals1(){
        CustomDate test2 = new CustomDate();
        assertTrue(test2.equals(testDate));
    }
    //Test the equals() method with false input
    @Test
    public void testEquals2(){
        CustomDate test2 = new CustomDate(1,2,1900);
        assertFalse(test2.equals(testDate));
    }
    //Test the equals() method with another object type
    @Test
    public void testEquals3(){
        Object test2 = new CustomDate();
        assertTrue(test2.equals(testDate));
    }
}
