package com.joblister.joblisterapp.service;

import com.joblister.joblisterapp.entity.Position;
import com.joblister.joblisterapp.error.ResultMessage;
import com.joblister.joblisterapp.error.ResultType;
import com.joblister.joblisterapp.repository.ApiRepository;
import com.joblister.joblisterapp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.UUID;

@Service
public class PositionRegistrationService {
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ApiRepository apiRepository;

    public Object registerPosition(String name, String location, String apiKey) throws MalformedURLException {
        ResultMessage result = checkInputValidity(name, location, apiKey);
        if (result.getType()==ResultType.ERROR) {
            return result;
        } else {
            Position position = new Position();
            position.setName(name);
            position.setLocation(location);

            UUID apiUUID = UUID.fromString(apiKey);
            Long clientId = apiRepository.findAPIByKeyvalue(apiUUID).getId();

            position.setClientID(clientId);

            Position savedPosition = positionRepository.save(position);

            return new String("/position/" + savedPosition.getId());
        }
    }

    private ResultMessage checkInputValidity(String name, String location, String apiKey) {
        ResultMessage apiValid = apiKeyValid(UUID.fromString(apiKey));

        if (apiValid.getType() == ResultType.OK) {
            ResultMessage nameValid = nameValid(name);

            if (nameValid.getType() == ResultType.OK) {
                ResultMessage locationValid = locationValid(location);

                if (locationValid.getType() == ResultType.OK) {
                    return new ResultMessage(ResultType.OK);
                } else return locationValid;
            } else return nameValid;
        } else return apiValid;
    }

    private ResultMessage apiKeyValid(UUID apiKey) {
        if (apiRepository.findAPIByKeyvalue(apiKey) == null)
            return new ResultMessage(ResultType.ERROR, "Invalid API-key! Please, use a valid key for this operation!");
        return new ResultMessage(ResultType.OK);
    }

    private ResultMessage nameValid(String name) {
        if (name.length() > 100)
            return new ResultMessage(ResultType.ERROR, "Entered position name is longer than 100 characters!");
        return new ResultMessage(ResultType.OK);
    }

    private ResultMessage locationValid(String location) {
        if (location.length() > 50)
            return new ResultMessage(ResultType.ERROR, "Entered location name is longer than 50 characters!");
        return new ResultMessage(ResultType.OK);
    }
}