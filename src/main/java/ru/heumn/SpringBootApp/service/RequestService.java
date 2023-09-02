package ru.heumn.SpringBootApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.heumn.SpringBootApp.domain.Request;
import ru.heumn.SpringBootApp.repos.RequestRepo;

@Service
public class RequestService {

    @Autowired
    RequestRepo requestRepo;
    public Boolean requestIsExists(Request request){
        Request requestTest = requestRepo.findByNumber(request.getNumber());
        if(requestTest != null)
        {
            return true;
        }
        return false;
    }

    public void addRequest(Request request){
        requestRepo.save(request);
    }

    public void delete(String number) {
        System.out.println(number + "ААААААААААААААААААААААААААААААА");
        Request requestTest = requestRepo.findByNumber(number);
        System.out.println(requestTest.getName());
        System.out.println(requestTest.getNumber());
        requestRepo.delete(requestTest);
    }
}
