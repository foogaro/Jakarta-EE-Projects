package org.jakartaeeprojects.recommendation.suggestion.boundary;

import org.jakartaeeprojects.recommendation.suggestion.entity.UserRating;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static org.jakartaeeprojects.recommendation.suggestion.entity.UserRating.rate;

@ApplicationScoped
public class RatingManager {

    @Inject
    private Logger logger;

    private Map<Integer, Set<UserRating>> ratingsMap;

    @PostConstruct
    public void init() {
        this.ratingsMap = Map.of(
                10, Set.of(
                        rate(10, 1, 4),
                        rate(10, 2, 2),
                        rate(10, 3, 2)
                ),
                11, Set.of(
                        rate(11, 1, 4),
                        rate(11, 2, 2),
                        rate(11, 3, 2)
                ),
                12, Set.of(
                        rate(12, 4, 5),
                        rate(12, 5, 2)
                )
        );
    }

    public Map<Integer, Set<UserRating>> getRatingsMap() {
        return this.ratingsMap;
    }

    public void addOrUpdateUserRating(UserRating rating) {
        Set<UserRating> givenRatings = this.ratingsMap.get(rating.getUserId());
        boolean added = givenRatings.add(rating);
        if(!added) {
            givenRatings.remove(rating);
            givenRatings.add(rating);
        }
    }

}
