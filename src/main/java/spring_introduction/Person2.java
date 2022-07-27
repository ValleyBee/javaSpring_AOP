package spring_introduction;

public class Person2 {
    private Pet pet;
    private String surname;
    private int age;
    Person2(Pet pet){
        System.out.println("Person2 bean is created");
        this.pet = pet;
    }
//    Person2(){
//        System.out.println("Person bean is created: Default constructor");
//        }

    // SETTERS
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // GETTERS
    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void callYourPet() {
        System.out.println("Person 2: Hello my lovely pet");
        pet.say();
    }
}
