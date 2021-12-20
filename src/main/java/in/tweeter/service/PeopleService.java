package in.tweeter.service;

import in.tweeter.repository.PeopleRepository;
import in.tweeter.repository.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {
    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public void savePeople(PeopleEntity entity) {
        peopleRepository.save(entity);
    }

    public PeopleEntity findPeopleById(long id) {
        return peopleRepository.getById(id);
    }

    public PeopleEntity findPeopleByUsername(String username) {
        return peopleRepository.findPeopleByUsername(username);
    }
}