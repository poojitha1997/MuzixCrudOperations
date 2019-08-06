package com.stackroute.muzix.service;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TrackServiceImpl implements TrackService
{

    private TrackRepository trackRepository;
    @Autowired
    private  MongoTemplate mongoTemplate;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }



    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId()))
            throw new TrackAlreadyExistsException("Track Already Exists");
        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("Track cannot be empty");
        }

        return savedTrack;
    }

    @Override
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if(!trackRepository.findById(id).isPresent()) {
            throw new TrackNotFoundException("No track found with this Id");
        }

      else {
            return trackRepository.findById(id).get();
      }
    }

    @Override
    public Track UpdateTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

  /*  @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {
        Query query=new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query,Track.class);
    }*/

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException{
        List<Track> tracks=trackRepository.getTrackByName(name);
        if(tracks.isEmpty())
        {
            throw new TrackNotFoundException("No Track found with this track name");
        }
        return tracks;
    }

}
