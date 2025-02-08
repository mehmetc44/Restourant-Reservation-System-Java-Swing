package Model;

public class Reservation {
    private int id;
    private int tableNumber;
    private String Date;
    private String beginHour;
    private String endHour;
    private int numberOfPerson;
    private String  nameSurname;
    private String phoneNumber;
    private String statement="Active";





    public Reservation(int id,String NameSurname,String PhoneNumber, int tableNumber, String Date, String beginHour, String endHour, int numberOfPerson, String statement) {
        this.id=id;
        this.tableNumber = tableNumber;
        this.Date = Date;
        this.beginHour = beginHour.substring(0,5);
        this.endHour = endHour.substring(0,5);
        this.numberOfPerson = numberOfPerson;
        this.statement = statement;
        this.nameSurname=NameSurname;
        this.phoneNumber=PhoneNumber;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Reservation() {
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }
    public String getStatement() {
        return this.statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
