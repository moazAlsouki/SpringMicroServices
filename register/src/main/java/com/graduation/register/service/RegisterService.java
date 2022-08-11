package com.graduation.register.service;

import com.graduation.register.models.Register;
import com.graduation.register.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    public Register addRegister(Register register){
        return registerRepository.save(register);
    }
    public Register findRegisterById(Long id){
        Register register=registerRepository.findById(id).get();
        return register;
    }
    public List<Register> findAllRegister(){
        return registerRepository.findAll();
    }

    public boolean deleteRegister(Long id){
        Register register=findRegisterById(id);
        if (register ==null)
        {
            return false;
        }
        registerRepository.delete(register);
        return true;
    }


    public Register findByExam(int examNumber){
        Register register=registerRepository.findByExamNumber(examNumber).get();
        return register;
    }
    public Register getLastRegister(int id){
        List <Register> registers=registerRepository.findByStdId(id);
        if(registers.size()>0) {
            Register register=registers.get(0);
            for (Register r : registers) {
                if(register.getYear()<r.getYear())
                {
                    register =findRegisterById(r.getId());
                }
            }
            return register;
        }
        return null;
    }
}
