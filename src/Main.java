import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countLessThan18 = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Колличество несовершеннолетних граждан = " + countLessThan18);

        List ListManOfMilitaryAge = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() >= 18 && age.getAge() <= 27)
                .map(family -> family.getFamily())
                .collect(Collectors.toList());
        System.out.println("Граждане призывного возраста: " + ListManOfMilitaryAge);

        List ListOfPeople = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() >= 18)
                .filter(sex -> {
                    if (sex.getSex() == Sex.MAN) {
                        return sex.getAge() < 65;
                    } else {
                        return sex.getAge() < 60;
                    }
                })
                    //if (sex.getSex() == Sex.MAN & sex.getAge() >= 18 & sex.getAge() <= 65
                    //        || sex.getSex() == Sex.WOMAN & sex.getAge() >= 18 & sex.getAge() <= 60) {
                    //    return true;
                    //}
                    //return false;
                    //})
                .collect(Collectors.toList());
        System.out.println("Список работоспособных граждан с высшим образованием: " + ListOfPeople);
    }
}
