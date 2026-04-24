# Interaction Flow

## IntersectionController

- Add intersection
  - intersectionController -> IntersectionService.addIntersection -> IntersectionRepository.addIntersection : intersectionId

- Start Cycle (intersectionId)
  - intersectionController -> IntersectionService.startCycle(intersectionId)
    - get intersection and start logic

- 