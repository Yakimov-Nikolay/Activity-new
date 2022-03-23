package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.GenderEntity;
import activity_new.activity_new.model.entity.enumerated.GenderEnumName;
import activity_new.activity_new.repository.GenderRepository;
import activity_new.activity_new.repository.RoleRepository;
import activity_new.activity_new.service.GenderService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;

    }


    @Override
    public void initGender() {

        if (genderRepository.count() != 0) {
            return;
        }

        GenderEntity male = new GenderEntity();
        male.setSex(GenderEnumName.MALE);

        GenderEntity female = new GenderEntity();
        female.setSex(GenderEnumName.FEMALE);

        genderRepository.saveAll(List.of(male, female));
    }
}
