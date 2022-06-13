package com.example.pointini.services;

import com.example.pointini.entities.Pack;
import com.example.pointini.repository.PackRepository;
import com.example.pointini.services.Interface.PackServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.pointini.entities.Enum.DaysMonths.Months;

@Service
public class PackService implements PackServiceI {
    @Autowired
    public PackRepository packRepository;
    @Autowired
    public EntrepriseService entrepriseService ;

    @Override
    public List<Pack> getAllPack() {
        return packRepository.findAll();
    }

    @Override
    public Pack findPackById(Long id) {
        return packRepository.findById(id).get();
    }

    @Override
    public void delete(Long id){
        packRepository.deleteById(id);
    }
    @Override
    public Date addDateDexpirationMonths(int duree) {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, duree);
            cal.add(Calendar.HOUR, 2);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        java.util.Date expirationDate = cal.getTime();

            return expirationDate;
        }
    @Override
    public Date addDateDexpirationDays(int duree) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, duree);
        cal.add(Calendar.HOUR, 2);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        java.util.Date expirationDate = cal.getTime();

        return expirationDate;
    }

    @Override
    public Pack createPack(Pack pack) {
        if (pack.getType()==Months){
            pack.setExpiration(addDateDexpirationMonths(pack.getDuree()));
            return packRepository.save(pack);
        }
        else{
            pack.setExpiration(addDateDexpirationDays(pack.getDuree()));
            return packRepository.save(pack);
        }
    }
    @Override
    public Pack updatePack(Pack pack, Long IdP) {

        Optional<Pack> utOptional=packRepository.findById(IdP);
        if(utOptional==null) {
            return null;
        }else {
            return packRepository.save(pack);
        }
    }

    @Override
    public Pack findByLibelle(String s){
        return packRepository.findByLibelle(s) ;
    }




}
