package newhope.server.dao;

import newhope.server.entity.TripFactEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TripFactDaoMockImpl {

    List<TripFactEntity> tripfacts = new LinkedList<>();

    public <S extends TripFactEntity> S save(S s) {
        tripfacts.add(s);
        return null;
    }

    public <S extends TripFactEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        for (S s : iterable){
            this.save(s);
        }
        return null;
    }

    public Optional<TripFactEntity> findById(Long aLong) {
        return Optional.empty();
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public Iterable<TripFactEntity> findAll() {
        return new LinkedList<>(tripfacts);
    }

    
    public Iterable<TripFactEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    
    public long count() {
        return 0;
    }

    
    public void deleteById(Long aLong) {

    }

    
    public void delete(TripFactEntity tripFactEntity) {

    }

    
    public void deleteAll(Iterable<? extends TripFactEntity> iterable) {

    }

    
    public void deleteAll() {

    }
}
