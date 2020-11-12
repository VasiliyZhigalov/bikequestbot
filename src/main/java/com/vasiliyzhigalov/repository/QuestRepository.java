package com.vasiliyzhigalov.repository;

import com.vasiliyzhigalov.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> findByLocationLongitudeBetweenAndLocationLatitudeBetween(Float latitudeLeft,
                                                                  Float latitudeRight,
                                                                  Float longitudeLeft,
                                                                  Float longitudeRight);
    List<Quest> findByLocationLongitude(Double longitude);

}
