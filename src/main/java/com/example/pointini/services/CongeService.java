package com.example.pointini.services;

import com.example.pointini.dto.EventsDto;
import com.example.pointini.entities.Conge;

import com.example.pointini.entities.Enum.ConfinoConfir;
import com.example.pointini.entities.User;
import com.example.pointini.repository.CongeRepository;
import com.example.pointini.services.Interface.CongeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CongeService implements CongeServiceI {
    @Autowired
    public CongeRepository congeRepository;
    @Autowired
    public UserService userService;




    @Override
    public Conge createConge(Conge conge){
        return congeRepository.save(conge);
    }
    @Override
    public void delete(Long id){
        congeRepository.deleteById(id);
    }

    @Override
    public List<Conge> getAllConge() {
        return congeRepository.findAll();
    }

    @Override
    public Date getDateWithoutTimeUsingCalendar()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Override
    public Date testconge(int nbJour)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getDateWithoutTimeUsingCalendar());
        cal.add(Calendar.DAY_OF_YEAR, nbJour);
        java.util.Date datefin =cal.getTime();
            return datefin;

    }

        @Override
        public Boolean checkDate(Long idUser, Date datedeb, Date datefin)
        {
            User user=userService.findUserById(idUser);
            Conge conge=congeRepository.getCongeByUserAndDatedebAndDatefin(user,datedeb,datefin);
            if(conge==null)
            {
                return true;
            }
            else
            return false;

        }



        @Override
        public Long getDuree( Date deb,Date fin ){
            var diff = deb.getTime() - fin.getTime();
            var daydiff = diff / (1000 * 60 * 60 * 24);
        return daydiff ;
        }




    @Override
    public Conge DemmandeCongeUser(Long idUser, int nbJour/*, Conge conge*/)  {
//        int nb=this.getDuree(conge.getDatedeb(),conge.getDatefin())
        Conge conge = new Conge();
        Calendar calendar=Calendar.getInstance();
        User user = userService.findUserById(idUser);
        int soldeConge = user.getSoldeconge();
        int dayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
        int i=0;
        if (this.checkDate(idUser,this.getDateWithoutTimeUsingCalendar(),testconge(nbJour))) {
            conge.setDatedeb(this.getDateWithoutTimeUsingCalendar());
            conge.setNbJour(nbJour);
            if( dayOfWeek == Calendar.SATURDAY) {
                i=nbJour+2;
            }else
            {
                i=nbJour+1;

            }
            conge.setDatefin(testconge(i));
            int soldeCongefinal = soldeConge - nbJour;
            user.setSoldeconge(soldeCongefinal);
            conge.setUser(user);
            conge.setEtat(ConfinoConfir.en_cours);
            return congeRepository.save(conge);
        }
        else
        {
            return null;
        }

    }

    @Override
    public Conge confirmConge(Long id){
        Conge conge=findCongeById(id);
        conge.setEtat(ConfinoConfir.confirmer);
        return congeRepository.save(conge);
    }




    @Override
    public Conge updateConge(Conge conge, Long id) {
        Conge cong=congeRepository.findById(id).orElse(null);
        cong.setId(id);
        cong.setDatedeb(conge.getDatedeb()==null ? cong.getDatedeb():conge.getDatedeb());
        cong.setDatefin(conge.getDatefin()==null ? cong.getDatefin():conge.getDatefin());
        cong.setNbJour(conge.getNbJour()==0? cong.getNbJour():conge.getNbJour());
        cong.setUser(conge.getUser()==null ? cong.getUser():conge.getUser());
        return congeRepository.saveAndFlush(cong);

    }
    @Override
    public Conge findCongeById (Long id){
        return congeRepository.findById(id).get();
    }



    public EventsDto convertEntitytoDto(Conge conge){
        EventsDto eventsDto=new EventsDto();
        eventsDto.setId(conge.getId());
        eventsDto.setStart(conge.getDatedeb());
        eventsDto.setEnd(conge.getDatefin());


        return eventsDto;
    }
    public List<EventsDto> getAllEvnts(){
        return congeRepository.findAll()
                .stream()
                .map(this::convertEntitytoDto)
                .collect(Collectors.toList());
    }





    }







