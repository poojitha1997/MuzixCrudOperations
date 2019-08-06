/*
package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import com.stackroute.muzix.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)

public class MuzixMockitoTest {
    @Autowired
    Track track;
    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list=null;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        track=new Track();
        list=new ArrayList<Track>();
        track.setId(101);
        track.setName("pallavi");
        track.setComment("good");
        track.setId(102);
        track.setName("pd");
        track.setComment("good");
        track.setId(103);
        track.setName("pdfjkvi");
        track.setComment("good");
        list.add(track);
    }
    @Test
    public void saveMuxix() throws TrackAlreadyExistsException
    {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
        verify(trackRepository,times(1)).save(track);

    }
    @Test
    public void getTrack() {
        when(trackRepository.findAll()).thenReturn(list);
       List<Track> list2= trackService.getAllTracks();
       Assert.assertEquals(list,list2);
       verify(trackRepository,times(1)).findAll();

    }
    @Test
    public void getTrackById() throws TrackNotFoundException {
        when(trackRepository.findById(any())).thenReturn(Optional.of(track));
        Track getTrack=trackService.getTrackById(1);
        Assert.assertEquals(track,getTrack);
        verify(trackRepository,times(2)).findById(any());
    }

    @Test
    public void deleteTrack()
    {
        doNothing().when(trackRepository).deleteById(any());
        trackService.deleteTrack(1);
        verify(trackRepository,times(1)).deleteById(any());

    }
    @Test
    public void updateTrack()
    {
        when(trackRepository.save(any())).thenReturn(track);
        Track updateTrck=trackService.UpdateTrack(track);
        Assert.assertEquals(track,updateTrck);
        verify(trackRepository,times(1)).save(track);
    }

}
*/
