package Model;


public class User extends Employee {
    
    
    public int id;

    public User(int id,String nameSurname, String tcNumber, String phoneNumber, String userName, String password, String role, String Country, String birthDate) {
        super(nameSurname, tcNumber, phoneNumber, userName, password, role, Country, birthDate);
        this.id=id;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
