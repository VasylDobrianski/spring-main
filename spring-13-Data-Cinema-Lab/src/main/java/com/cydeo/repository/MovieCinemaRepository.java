package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read movie cinema with id
    Optional<MovieCinema> findById(Long Id);


    //Write a derived query to count all movie cinemas with a specific cinema id
    Integer countAllByCinemaId(Long Id);

    //Write a derived query to count all movie cinemas with a specific movie id
    Integer countAllByMovieId(Long Id);

    //Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> findAllByDateTimeAfter(LocalDateTime dateTime);

    //Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findTop3ByOrderByMoviePriceDesc();

    //Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> findAllByMovieNameContaining(String Name);

    //Write a derived query to list all movie cinemas in a specific location name
    List<MovieCinema> findAllByCinemaLocationName(String name);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("select mc from MovieCinema mc where mc.dateTime > ?1")
    List<MovieCinema> fetchAllWithHigherThanSpecificDate(@Param("dateTime") LocalDateTime dateTime);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count all movie cinemas by cinema id
    @Query(value = "select count(*) from movie_cinema where cinema_id = ?1",nativeQuery = true)
    List<MovieCinema> countByCinemaId(@Param("id") Long cinemaId);

    //Write a native query that returns all movie cinemas by location name
    @Query(value = "select * from movie_cinema mc join cinema c on c.id = mc.cinema_id" +
            "join location l on l.id = c.location_id where l.name = ?1",nativeQuery = true)
    List<MovieCinema> retrieveAllByLocationName(@Param("name") String name);

}
