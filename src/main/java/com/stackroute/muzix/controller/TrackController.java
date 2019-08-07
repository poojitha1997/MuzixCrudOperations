package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

private TrackService trackService;
    ResponseEntity responseEntity;
    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService = trackService;
    }

    //saving the tracks
    @PostMapping("Tracks")
    public ResponseEntity<?> saveTracks(@RequestBody List<Track> tracks) throws TrackAlreadyExistsException{
        for(Track t1:tracks)
        {
            trackService.saveTrack(t1);

        }
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }

    
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
         catch(Exception e)
        {
            e.printStackTrace();
        }

        return responseEntity;
    }
    //retrieving all the tracks
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    //retrieving the track by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id)
    {
        try {
            responseEntity=  new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
        }
        catch(TrackNotFoundException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return responseEntity;
    }
    //deleting the track by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable(value="id") Integer id)
    {
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Deleted",HttpStatus.NO_CONTENT);
        return responseEntity;
    }
    //updating the user 
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)  {
        try {

            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        }
        catch (TrackAlreadyExistsException ex) {

            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

            return responseEntity;
        }

   @GetMapping("tracks/{name}")
   //@Query("from Track where name=?1")
    public ResponseEntity<?> getAllTracksByName(@PathVariable(value="name") String name)
    {
        try {
            responseEntity=new ResponseEntity<List<Track>>(trackService.getTrackByName(name),HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

       return responseEntity;
    }
}
