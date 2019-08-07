package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public String deleteTrack(int id);

    public List<Track> getAllTracks();

    public Track getTrackById(int id);

    public Track UpdateTrack(Track track);
    public List<Track> getTrackByName(String name);


}
