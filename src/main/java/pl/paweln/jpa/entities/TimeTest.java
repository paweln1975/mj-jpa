package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TIME_TEST")
@Access(value= AccessType.FIELD)
public class TimeTest {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="TIME_TEST_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP) //java.util or java.time properties need to explicitly mark the SQL type
    @Column(name="DATETIME_COLUMN")
    private Date dateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP_COLUMN")
    private Date timeStamp;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_COLUMN")
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name="TIME_COLUMN")
    private Date time;

    @Column(name="SQL_DATETIME_COLUMN")
    private java.sql.Timestamp sqlDateTime;

    @Column(name="SQL_TIMESTAMP_COLUMN")
    private java.sql.Timestamp sqlTimeStamp;

    @Column(name="SQL_DATE_COLUMN")
    private java.sql.Date sqlDate;

    @Column(name="SQL_TIME_COLUMN")
    private java.sql.Time sqlTime;

    public TimeTest(Date date) {
        this.dateTime = date;
        this.timeStamp = date;
        this.date = date;
        this.time = date;

        this.sqlDateTime = new java.sql.Timestamp(date.getTime());
        this.sqlTimeStamp = new java.sql.Timestamp(date.getTime());
        this.sqlDate = new java.sql.Date(date.getTime());
        this.sqlTime = new java.sql.Time(date.getTime());
    }

    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        return lineSeparator + "DATE_TIME=" + this.dateTime
                + lineSeparator + "TIME_STAMP=" + this.timeStamp
                + lineSeparator +"DATE=" + this.date
                + lineSeparator +"TIME=" + this.time
                + lineSeparator +"SQL_DATE_TIME=" + this.sqlDateTime
                + lineSeparator +"SQL_TIME_STAMP=" + this.sqlTimeStamp
                + lineSeparator +"SQL_DATE=" + this.sqlDate
                + lineSeparator +"SQL_TIME=" + this.sqlTime;
    }
}
