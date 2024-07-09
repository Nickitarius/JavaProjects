package oop4;

public class Abiturient {

    //Properties
    private int id;
    private String surname;
    private String name;
    private String secondName;
    private String phoneNumber;
    private int firstSubject;
    private int secondSubject;
    private int thirdSubject;

    //Constructors
    public Abiturient() {
        this.id = 0;
        this.surname = "";
        this.name = "";
        this.secondName = "";
        this.phoneNumber = "+78005553535";
        this.firstSubject = 0;
        this.secondSubject = 0;
        this.thirdSubject = 0;
    }

    public Abiturient(int id, String name, String secondName, String surname, String phoneNumber, int firstSubject, int secondSubject, int thirdSubject) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.firstSubject = firstSubject;
        this.secondSubject = secondSubject;
        this.thirdSubject = thirdSubject;
    }

    public Abiturient(Abiturient JangoFett) {
        this.id = JangoFett.getId();
        this.surname = JangoFett.getSurname();
        this.name = JangoFett.getName();
        this.secondName = JangoFett.getSecondName();
        this.phoneNumber = JangoFett.getPhoneNumber();
        this.firstSubject = JangoFett.getFirstSubject();
        this.secondSubject = JangoFett.getSecondSubject();
        this.thirdSubject = JangoFett.getThirdSubject();
    }

    //Setters & getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setFirstSubject(int firstSubject) {
        this.firstSubject = firstSubject;
    }

    public int getFirstSubject() {
        return this.firstSubject;
    }

    public void setSecondSubject(int secondSubject) {
        this.secondSubject = secondSubject;
    }

    public int getSecondSubject() {
        return this.secondSubject;
    }

    public void setThirdSubject(int thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public int getThirdSubject() {
        return this.thirdSubject;
    }

    //End of setters&getters
    @Override
    public String toString() {
        return String.format("|%4d|%16s|%16s|%16s|%s|%8d|%8d|%8d|",
                this.id, this.surname, this.name, this.secondName, this.phoneNumber, this.firstSubject, this.secondSubject, this.thirdSubject);
    }

    public boolean equals(Abiturient other) {
        return (other.getId() == this.id) && (other.getSurname().equals(this.surname)) && (other.getName().equals(this.name))
                && (other.getSecondName().equals(this.secondName)) && (other.getPhoneNumber().equals(this.phoneNumber))
                && (other.getFirstSubject() == this.firstSubject) && (other.getSecondSubject() == this.secondSubject)
                && (other.getThirdSubject() == this.thirdSubject);
    }

    public static String getTableCaption() {
        return String.format(getLine()
                        + "\n|%4s|%16s|%16s|%16s|%12s|%8s|%8s|%8s|%s",
                "id", "Фамилия", "Имя", "Отчество", "Номер тел.", "Оценка 1", "Оценка 2", "Оценка 3", "\n" + getLine());
    }

    public static String getLine() {
        return String.format("+----+----------------+----------------+----------------+------------+--------+--------+--------+");
    }
}
