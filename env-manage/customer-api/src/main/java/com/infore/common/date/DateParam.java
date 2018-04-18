package com.infore.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.WebApplicationException;

/**
 * Created by xuyao on 2017/7/5.
 */
public class DateParam {
    private SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
    private Date date;

    public DateParam( String timestampStr ) throws WebApplicationException {
        try {
            date =  df.parse( timestampStr );
        } catch ( final ParseException ex ) {
            throw new WebApplicationException( ex );
        }
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        if ( date != null ) {
            return date.toString();
        } else {
            return "";
        }
    }
}
