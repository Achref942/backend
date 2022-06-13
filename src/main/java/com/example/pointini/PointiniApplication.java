package com.example.pointini;


import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;
import com.example.pointini.services.PointageService;
import com.example.pointini.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableScheduling
public class PointiniApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointiniApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer()  {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }













    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start (UserService userService, PointageService pointageService){
     //   return args -> { User user=userService.createUser(new User(1L,null,null,"achref",null,22,null,null,null,null,null,"achref",20,1,0,null,0,null,null,null,null,null,null,null,null,null),null);
      //      Pointage pointage=pointageService.createPointage(new Pointage(),user.getId());
           // TimeUnit.SECONDS.sleep(5);
        //    pointageService.updatePointage(pointage,user.getId());
//            pointage.getArrive().getHour();
//            pointage.getDateArrive().getYear();
//            pointage.getSortir();
//            pointage.getDateSortir();
//            LocalDateTime fromDateTime = LocalDateTime.of( pointage.getDateArrive().getYear(), pointage.getDateArrive().getMonth(), pointage.getDateArrive().getDay(), pointage.getArrive().getHour(), pointage.getArrive().getMinute(), pointage.getArrive().getSecond());
//            LocalDateTime toDateTime = LocalDateTime.of( pointage.getDateSortir().getYear(),  pointage.getDateSortir().getMonth(),  pointage.getDateSortir().getDay(), pointage.getSortir().getHour(), pointage.getSortir().getMinute(), pointage.getSortir().getSecond());
//
//            LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );
//
//            long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
//            tempDateTime = tempDateTime.plusYears( years );
//
//            long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
//            tempDateTime = tempDateTime.plusMonths( months );
//
//            long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
//            tempDateTime = tempDateTime.plusDays( days );
//
//
//            long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
//            tempDateTime = tempDateTime.plusHours( hours );
//
//            long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
//            tempDateTime = tempDateTime.plusMinutes( minutes );
//
//            long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS );
//
//            System.out.println( years + " years " +
//                    months + " months " +
//                    days + " days " +
//                    hours + " hours " +
//                    minutes + " minutes " +
//                    seconds + " seconds.");

//prints: 29 years 8 months 24 days 22 hours 54 minutes 50 seconds.

return args -> {;
        };







    }



}
