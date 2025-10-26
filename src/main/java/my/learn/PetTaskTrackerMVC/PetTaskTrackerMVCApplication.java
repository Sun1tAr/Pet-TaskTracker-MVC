package my.learn.PetTaskTrackerMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class PetTaskTrackerMVCApplication {


    public static void main(String[] args) {
        SpringApplication.run(PetTaskTrackerMVCApplication.class, args);
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(10));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(10, 20));
        }
    }


}
