package com.graduation.order.service;

import com.graduation.order.models.Accept;
import com.graduation.order.models.Order;
import com.graduation.order.repository.AcceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcceptService {

    @Autowired
    AcceptRepository acceptRepository;

    public Accept add(Accept accept)
    {
        return acceptRepository.save(accept);
    }

    @PreAuthorize("hasAuthority('ROLE_Aemployee')")
    public Accept setAEmployeeAccept(long id){
        Accept a=get(id);
        if (a.getRoleName().equals("ROLE_Aemployee"))
        {
            a.setAccept(true);
            Accept accept=add(a);
            return accept;
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_Eemployee')")
    public Accept setEEmployeeAccept(long id){
        Accept a=get(id);
        if (a.getRoleName().equals("ROLE_Eemployee"))
        {
            a.setAccept(true);
            Accept accept=add(a);
            return accept;
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_dean')")
    public Accept setDeanAccept(long id){
        Accept a=get(id);
        if (a.getRoleName().equals("ROLE_dean"))
        {
            a.setAccept(true);
            Accept accept=add(a);
            return accept;
        }
        return null;
    }

    public boolean IsAccepted(int id){
        List<Accept> accepts=acceptRepository.findByOrderId(id);
        for (Accept a:accepts){
                if(!a.isAccept()){
                    return false;
                }
        }
        return true;
    }

    public Accept get(long id){
        return acceptRepository.findById(id).get();
    }

    List<Accept> getNeededAccept(String roleName){
        return acceptRepository.findByRoleName(roleName);
    }




}
