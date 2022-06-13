package com.example.pointini.services;

import com.example.pointini.entities.JourFerie;
import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;
import com.example.pointini.repository.JourFerieRepository;
import com.example.pointini.repository.PointgeRepository;
import com.example.pointini.repository.UserRepository;
import com.example.pointini.services.Interface.JourFerieServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
public class JourFerieService implements JourFerieServiceI {

    @Autowired
    public JourFerieRepository jourFerieRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public  UserRepository userRepository;
    @Autowired
    public PointgeRepository pointgeRepository;
    @Autowired
    public PointageService pointageService;
  @Value("${jobs.enabled:true}")
    private boolean isEnabled;

    //Logger logger= LoggerFactory.getLogger(JourFerieService.class);
    @Override
    public void delete (Long id){
        jourFerieRepository.deleteById(id);
    }
    @Override
    public JourFerie createJourFerie(JourFerie jourFerie) {

        return jourFerieRepository.save(jourFerie);
    }

    @Override
    public JourFerie updateJourFerie(JourFerie jourFerie,Long idJF) {
        JourFerie jFerie=jourFerieRepository.findById(idJF).orElse(null);
        jFerie.setId(idJF);
        jFerie.setLibelle(jourFerie.getLibelle()==null ? jFerie.getLibelle():jourFerie.getLibelle());
        jFerie.setDatedeb(jourFerie.getDatedeb()==null ? jFerie.getDatedeb():jourFerie.getDatedeb());
        jFerie.setDatefin(jourFerie.getDatefin()==null ? jFerie.getDatefin():jourFerie.getDatefin());
        return  jourFerieRepository.saveAndFlush(jFerie);
    }

    @Override
    public String getDateWithoutTimeUsingCalendar()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date=calendar.getTime();
        String chaymadate=dateFormat.format(date);
        return chaymadate;
    }

    @Override
    public JourFerie findJFById(Long id) {
        return jourFerieRepository.findById(id).get();
    }


    @Override
    public Boolean checkDate(Date date,Long idUser)
    {
        Pointage pointage=pointgeRepository.findPointageByDateAndUserId(date,idUser);
        if(pointage==null)
        {
            return true;
        }
        else
            return false;
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public List<JourFerie> testJourFerie() {
        System.out.println(this.getDateWithoutTimeUsingCalendar());
        List<JourFerie> jourFeries=jourFerieRepository.findAll();
        List<User> users=userRepository.findAll();
        jourFeries.stream().forEach(jourFerie -> {
            jourFerie.setUsers(users);
            System.out.println(jourFerie.getDatedeb());
            System.out.println("new date "+ new Date());
          if(this.getDateWithoutTimeUsingCalendar().equals(jourFerie.getDatedeb().toString()) && isEnabled){
              users.stream().forEach(user -> {
                  if(this.checkDate(jourFerie.getDatedeb(),user.getId())) {
                      Pointage pointage = new Pointage();
                      pointageService.createPointageEntreSotrie(pointage, user.getId());
                  }
              });
          }
        });
        return jourFeries;
    }

    @Override
    public JourFerie findJourFerieById(Long id) {
        return jourFerieRepository.findById(id).get();

    }
    @Override
    public List<JourFerie> getAllJourFerie() {

        return jourFerieRepository.findAll();
    }
}
