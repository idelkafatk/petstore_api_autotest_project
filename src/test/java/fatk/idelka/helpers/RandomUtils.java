package fatk.idelka.helpers;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RandomUtils {
    static Faker faker = new Faker();
    public static String petName = faker.animal().name();
    public static Integer petId = faker.number().randomDigitNotZero();


    public static List<String> getPhotoUrls () {
        List<String> petPhotoUrls = new ArrayList<>();

        for (int i=0; i<2; i++) {
            petPhotoUrls.add(faker.internet().url());
        }

        return petPhotoUrls;
    }
}
