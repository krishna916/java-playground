package com.krishnamurti.playground.lld.trafficlight.service;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;

import java.util.List;

public interface IntersectionService {

    Integer addIntersection(List<Direction> directions);

    Intersection getById(Integer intersectionId);

    boolean startCycle(Integer intersectionId);

    void displayCurrentState(Integer intersectionId);
}
