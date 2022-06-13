package com.example.pointini.services;

import com.example.pointini.entities.Entreprise;
import com.example.pointini.entities.Pack;
import com.example.pointini.entities.User;
import com.example.pointini.repository.EntrepriseRepository;
import com.example.pointini.repository.PackRepository;
import com.example.pointini.repository.UserRepository;
import com.example.pointini.services.Interface.EntrepriseServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Component
public class EntrepriseService implements EntrepriseServiceI {
    @Autowired
    public EntrepriseRepository entrepriseRepository;

    @Autowired
    public PackRepository packRepository;

    @Autowired
    public UserRepository userRepository;

   //@Value("${jobs.enabled:true}")
    //private boolean isEnabled;

    @Override
    public List<Entreprise> getAllEntreprise() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Entreprise findEntrepriseById(Long id) {
        return entrepriseRepository.findById(id).get();

    }
    @Override
    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise, Long idE) {
        Entreprise en=entrepriseRepository.findById(idE).orElse(null);
        en.setId(idE);
        en.setLibelle(entreprise.getLibelle()==null ? en.getLibelle():entreprise.getLibelle());
        en.setLogo(entreprise.getLogo()==null ? en.getLogo():entreprise.getLogo());
        en.setMatriculeFiscale(entreprise.getMatriculeFiscale() ==null ? en.getMatriculeFiscale():entreprise.getMatriculeFiscale());
        en.setPrixHeurSup(entreprise.getPrixHeurSup() == 0 ? en.getPrixHeurSup():entreprise.getPrixHeurSup());
        en.setNbCongePMois(entreprise.getNbCongePMois()==0 ? en.getNbCongePMois():entreprise.getNbCongePMois());
        en.setEtat(entreprise.getEtat()==0 ? en.getEtat():entreprise.getEtat());
        en.setPack(entreprise.getPack()==null ? en.getPack():entreprise.getPack());
return  entrepriseRepository.saveAndFlush(en);


    }


    @Override
    public void delete(Long id){
        entrepriseRepository.deleteById(id);
    }


    @Override
    public Pack AddPackToEntreprise(Long idPack, Long idEntreprise) {
        Entreprise entreprise = this.findEntrepriseById(idEntreprise);
        Pack pack = packRepository.findPackById(idPack);
        entreprise.setExpirationPack(pack.getExpiration());
        entreprise.getPack().add(pack);
        return packRepository.save(pack);
    }
    @Override
    public String getDateWithoutTimeUsingCalendar()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DAY_OF_WEEK, 3);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date=calendar.getTime();
        String chaymadate=dateFormat.format(date);
        return chaymadate;
    }
    //@Scheduled(cron = "0 0 0 * * *")
    @Override
    public Entreprise DesactiverEntreprise(Long idEntreprise)
    {
        Entreprise entreprise=this.findEntrepriseById(idEntreprise);

        System.out.println(entreprise.getExpirationPack());
        System.out.println(this.getDateWithoutTimeUsingCalendar());

        if(entreprise.getExpirationPack().toString().equals(this.getDateWithoutTimeUsingCalendar()))
        {
            entreprise.setEtat(0);
            List<User> users=entreprise.getUser();
            users.stream().forEach(user ->{
                user.setEtat(0);
                userRepository.save(user);
            });


        }
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise Desactive(Long idE){
        Entreprise entreprise=this.findEntrepriseById(idE);
        entreprise.setEtat(0);
        return entrepriseRepository.save(entreprise);

    }
    @Override
    public Entreprise Active(Long idE){
        Entreprise entreprise=this.findEntrepriseById(idE);
        entreprise.setEtat(1);
        return entrepriseRepository.save(entreprise);

    }

}
