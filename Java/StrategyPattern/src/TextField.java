public class TextField {

    //Validator validator
    private String value;
    private String name;

    public TextField(String initName) {
        this.name = initName;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public boolean validate() {
        return true;
    }

    @Override
    public String toString() {
        return "TextField{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
