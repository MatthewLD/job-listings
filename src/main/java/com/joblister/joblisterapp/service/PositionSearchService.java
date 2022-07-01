package com.joblister.joblisterapp.service;

import com.joblister.joblisterapp.entity.Position;
import com.joblister.joblisterapp.error.ResultMessage;
import com.joblister.joblisterapp.error.ResultType;
import com.joblister.joblisterapp.repository.ApiRepository;
import com.joblister.joblisterapp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.joblister.joblisterapp.JobListerApplication.getBaseURL;

@Service
public class PositionSearchService {
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ApiRepository apiRepository;


    public Object searchPosition(String keyWord, String location, String apiKey) {
        ResultMessage result = checkInputValidity(keyWord, location, apiKey);
        if (result.getType()== ResultType.ERROR) {
            return result;
        }
        else{
            List<Position> positions = positionRepository.searchPositionByName(keyWord, location);
            List<String> retVal = new ArrayList<>();
            for(Position position : positions){
                retVal.add(getBaseURL()+"/position/"+position.getId());
            }
            return retVal;
        }
    }

    private ResultMessage checkInputValidity(String name, String location, String apiKey){
        ResultMessage apiValid = apiKeyValid(UUID.fromString(apiKey));

        if (apiValid.getType() == ResultType.OK) {
            ResultMessage nameValid = keyWordValid(name);

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

    private ResultMessage keyWordValid(String keyWord){
        if (keyWord.length() > 50)
            return new ResultMessage(ResultType.ERROR, "Entered key word is longer than 50 characters!");
        return new ResultMessage(ResultType.OK);
    }

    private ResultMessage locationValid(String location) {
        if (location.length() > 50)
            return new ResultMessage(ResultType.ERROR, "Entered location name is longer than 50 characters!");
        return new ResultMessage(ResultType.OK);
    }

    public Position findById(Long id) {
        return positionRepository.findPositionById(id);
    }
}
