package com.stackroute.MuzixCrudOperations.controller;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.service.TrackService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController
{
     private TrackService trackService;
    private ResponseEntity responseEntity;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
        //saving the track
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED); //http status is created and showed as successfully created
            

        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT); //http conflict is raised if track already existed. 
            ex.printStackTrace();
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
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id)
    {
        return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    }
        //deleting the track by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable(value="id") Integer id)
    {
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Deleted",HttpStatus.FORBIDDEN);//httpstatus is shown as "deleted"
        return responseEntity;
    }
        //updating the track
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)  {
        try {

            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        }
        catch (TrackAlreadyExistsException ex) {// exception is thrown if track is already existed

            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
         catch(Exception e)
        {
            e.printStackTrace();
        }

        return responseEntity;
    }

    @GetMapping("trackForName/{name}")
    @Query("from Track where name=?1")
    public ResponseEntity<?> getAllTracksByName(@PathVariable(value="name") String name) //retriving the track by name
    {
         
        return new ResponseEntity<List<Track>>(trackService.getTrackByName(name),HttpStatus.OK);
    }

}
