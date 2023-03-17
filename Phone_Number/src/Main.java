import java.util.*;

class PhoneNumber implements Comparable<PhoneNumber> {
    final int areaCode;
    final int phoneNumber;

    PhoneNumber(int newCode, int newNumber) {
        areaCode = newCode;
        phoneNumber = newNumber;
    }

    public int compareTo(PhoneNumber toCompare) { return Integer.compare(phoneNumber, toCompare.phoneNumber); }
}

abstract class Entry {
    abstract void entryDescription();

    abstract String getCity();
}

class Person extends Entry {
    private final String name;
    private final String surname;
    private final String city;
    final PhoneNumber phoneNumber;

    Person(String newName, String newSurname, String newCity, PhoneNumber newNumber) {
        name = newName;
        surname = newSurname;
        city = newCity;
        phoneNumber = newNumber;
    }

    void entryDescription() {
        System.out.println("Name: " + name + " | Surname: " + surname + " | City: " + city + " | Phone Number: +" + phoneNumber.areaCode + " " + phoneNumber.phoneNumber);
    }

    String getCity() { return city; }
}

class Company extends Entry {
    private final String companyName;
    private final String city;
    final PhoneNumber phoneNumber;

    Company(String newName, String newCity, PhoneNumber newNumber) {
        companyName = newName;
        city = newCity;
        phoneNumber = newNumber;
    }

    void entryDescription() {
        System.out.println("Company Name: " + companyName + " | City: " + city + " | Phone Number: +" + phoneNumber.areaCode + " " + phoneNumber.phoneNumber);
    }

    String getCity() { return city; }
}

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Adrian", "Kowalski", "Konin", new PhoneNumber(48, 609784567));
        Person person2 = new Person("Kamil", "Pietrzak", "Warsaw", new PhoneNumber(48, 665123987));
        Person person3 = new Person("Jason", "Newborn", "Paris", new PhoneNumber(72, 225345789));

        Company company1 = new Company("TeleMango", "Taiwan", new PhoneNumber(34, 223445689));
        Company company2 = new Company("CD Projekt Blue", "Warsaw", new PhoneNumber(48, 456123789));

        TreeMap<PhoneNumber, Entry> map = new TreeMap<>();

        map.put(person1.phoneNumber, person1);
        map.put(person2.phoneNumber, person2);
        map.put(person3.phoneNumber, person3);
        map.put(company1.phoneNumber, company1);
        map.put(company2.phoneNumber, company2);

        Iterator<Map.Entry<PhoneNumber, Entry>> iterator = map.entrySet().iterator();

        System.out.println("Original TreeMap:");
        while(iterator.hasNext()) {
            Map.Entry<PhoneNumber, Entry> entry = iterator.next();
            entry.getValue().entryDescription();
        }

        //Deleting entries with the same city name
        Iterator<Map.Entry<PhoneNumber, Entry>> iterator_1 = map.entrySet().iterator();
        while(iterator_1.hasNext()) {
            Map.Entry<PhoneNumber, Entry> entry_1 = iterator_1.next();

            Iterator<Map.Entry<PhoneNumber, Entry>> iterator_2 = map.entrySet().iterator();
            while(iterator_2.hasNext()) {
                Map.Entry<PhoneNumber, Entry> entry_2 = iterator_2.next();

                if(entry_1.getKey() != entry_2.getKey() && Objects.equals(entry_1.getValue().getCity(), entry_2.getValue().getCity())) {
                    map.remove(entry_1.getKey());
                    map.remove(entry_2.getKey());

                    iterator_1 = map.entrySet().iterator();
                    iterator_2 = map.entrySet().iterator();
                }
            }
        }

        iterator = map.entrySet().iterator();

        System.out.println("\nModified TreeMap:");
        while(iterator.hasNext()) {
            Map.Entry<PhoneNumber, Entry> entry = iterator.next();
            entry.getValue().entryDescription();
        }
    }
}