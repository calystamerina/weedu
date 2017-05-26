package com.telkom.weedu.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sidiqpermana on 4/12/17.
 */
public class DateUtilsTest {
    @Test
    public void formatEdumailDate() throws Exception {
        String sampleDate = "2017-03-10T14:46:43.892+07:00";
        String formattedDate = DateUtils.formatEdumailDate(sampleDate);
        assertEquals("Test Date", "10 Maret", formattedDate);
    }

}