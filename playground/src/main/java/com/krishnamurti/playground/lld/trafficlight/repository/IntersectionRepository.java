package com.krishnamurti.playground.lld.trafficlight.repository;

import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;

import java.util.Optional;

public interface IntersectionRepository {
    Integer addIntersection(Intersection intersection);

    Integer updateIntersection(Intersection intersection);

    Optional<Intersection> findById(Integer intersectionId);
}
