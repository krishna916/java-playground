package com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl;

import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;
import com.krishnamurti.playground.lld.trafficlight.repository.IntersectionRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class IntersectionInMemoryRepository implements IntersectionRepository {

    private static final Map<Integer, Intersection> INTERSECTION_MAP = new ConcurrentHashMap<>();

    @Override
    public synchronized Integer addIntersection(Intersection intersection) {
        INTERSECTION_MAP.put(intersection.getIntersectionId(), intersection);
        System.out.println("Repository: add intersection: " + intersection.getIntersectionId());
        return intersection.getIntersectionId();
    }

    @Override
    public Integer updateIntersection(Intersection intersection) {
        System.out.println("Repository: update intersection: " + intersection.getIntersectionId());

        if (!INTERSECTION_MAP.containsKey(intersection.getIntersectionId())) {
            throw new IllegalArgumentException("Provided intersection does not exist!");
        }
        INTERSECTION_MAP.put(intersection.getIntersectionId(), intersection);
        return intersection.getIntersectionId();
    }

    @Override
    public Optional<Intersection> findById(Integer intersectionId) {
        return Optional.ofNullable(INTERSECTION_MAP.get(intersectionId));
    }
}
