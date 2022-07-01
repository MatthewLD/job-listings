package com.joblister.joblisterapp.repository;

import com.joblister.joblisterapp.entity.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface PositionRepository  extends CrudRepository<Position,Long> {
    Position findPositionById(Long id);

    Position findPositionByClientID(Long clientID);

    Position findPositionByName(String name);

    List<Position> findAll();

    default List<Position> searchPositionByName(String name, String location) {
        List<Position> positionsValid = new ArrayList<>();
        for (Position position : findAll())
            if (StringUtils.containsIgnoreCase(position.getName(), name))
                if (location.equals("") || StringUtils.containsIgnoreCase(position.getLocation(), location))
                    positionsValid.add(position);
        return positionsValid;
    }
}
