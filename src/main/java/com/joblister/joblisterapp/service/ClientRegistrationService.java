package com.joblister.joblisterapp.service;

import com.joblister.joblisterapp.entity.Api;
import com.joblister.joblisterapp.entity.Client;
import com.joblister.joblisterapp.error.ResultMessage;
import com.joblister.joblisterapp.error.ResultType;
import com.joblister.joblisterapp.repository.ApiRepository;
import com.joblister.joblisterapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ClientRegistrationService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ApiRepository apiRepository;

    public Object registerClientInDB(String name, String email) {
        ResultMessage result = checkInputValidity(name, email);
        if (result.getType()==ResultType.ERROR) {
            return result;
        } else {
            UUID api;
            Api newApi;
            boolean valid;

            do {
                api = UUID.randomUUID();
                newApi = new Api();
                newApi.setKeyvalue(api);
                valid = apiRepository.findAPIByKeyvalue(api) != null;
            } while (valid);

            Api savedApi = apiRepository.save(newApi);

            Client client = new Client();
            client.setName(name);
            client.setEmail(email);
            client.setApiID(savedApi.getId());

            clientRepository.save(client);

            return api;
        }
    }

    private ResultMessage checkInputValidity(String name, String email) {
        ResultMessage nameValid = nameValid(name);

        if(nameValid.getType()==ResultType.OK){
            ResultMessage emailValid = eMailValid(email);

            if(emailValid.getType()==ResultType.OK)  return new ResultMessage(ResultType.OK);
            else return emailValid;
        }
        else return nameValid;
    }

    private ResultMessage nameValid(String name) {
        if (name.length() > 100)
            return new ResultMessage(ResultType.ERROR,"Entered name is longer than 100 characters!");
        return new ResultMessage(ResultType.OK);
    }

    private ResultMessage eMailValid(String email) {
        final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Pattern.compile(regexPattern)
                .matcher(email)
                .matches())
            return new ResultMessage(ResultType.ERROR,"This is not a valid e-mail address!");

        if(clientRepository.findClientByEmail(email)!=null)
            return new ResultMessage(ResultType.ERROR,"This e-mail address is used by another client. Please, change your e-mail address!");

        return new ResultMessage(ResultType.OK);
    }
}
