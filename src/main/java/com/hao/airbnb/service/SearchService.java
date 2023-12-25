package com.hao.airbnb.service;

import com.hao.airbnb.repository.StayReservationDateRepository;
import com.hao.airbnb.repository.LocationRepository;
import com.hao.airbnb.repository.StayRepository;
import org.springframework.stereotype.Service;
import com.hao.airbnb.model.Stay;

import java.time.LocalDate;
import java.util.*;

@Service
public class SearchService {
    private final StayRepository stayRepository;
    private final StayReservationDateRepository stayReservationDateRepository;
    private final LocationRepository locationRepository;

    public SearchService(StayRepository stayRepository, StayReservationDateRepository stayReservationDateRepository, LocationRepository locationRepository) {
        this.stayRepository = stayRepository;
        this.stayReservationDateRepository = stayReservationDateRepository;
        this.locationRepository = locationRepository;
    }

    public List<Stay> search(int guestNumber, LocalDate checkinDate, LocalDate checkoutDate, double lat, double lon, String distance) {

        List<Long> stayIds = locationRepository.searchByDistance(lat, lon, distance);
        if (stayIds == null || stayIds.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> reservedStayIds = stayReservationDateRepository.findByIdInAndDateBetween(stayIds, checkinDate, checkoutDate.minusDays(1));

        List<Long> filteredStayIds = new ArrayList<>();
        for (Long stayId : stayIds) {
            if (!reservedStayIds.contains(stayId)) {
                filteredStayIds.add(stayId);
            }
        }
        return stayRepository.findByIdInAndGuestNumberGreaterThanEqual(filteredStayIds, guestNumber);
    }
}

