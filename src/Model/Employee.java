package Model;

import Model.PersonModel.PersonModel;

public class Employee extends PersonModel {
    private int Id;
    
    public Employee(String nameSurname, String userName,String phoneNumber, String role) {
        this.userName = userName;
        this.role = role;
        this.setNameSurname(nameSurname);
        this.setPhoneNumber(phoneNumber);
    }
    public Employee(String name){
        this.nameSurname=name;
    }
    private String tcNumber="-";
    private String userName="-";
    private String password="-";
    private String role="-";
    private String Country="-";
    private String birthDate="-";
    private String profilePhoto="-";

    public Employee(String nameSurname,String tcNumber, String phoneNumber,String userName, String password, String role, String Country, String birthDate) {
        this.tcNumber = tcNumber;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.Country = Country;
        this.birthDate = birthDate;
        this.nameSurname=nameSurname;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public String getNameSurname() {
        return nameSurname;
    }

    @Override
    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getAdress() {
        return adress;
    }

    @Override
    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    
    public int getId() {
        return Id;
    }

    public String getTcNumber() {
        return tcNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getCountry() {
        return Country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }  

    
}
