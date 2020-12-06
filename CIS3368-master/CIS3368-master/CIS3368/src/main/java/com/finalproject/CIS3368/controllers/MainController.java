package com.finalproject.CIS3368.controllers;

import com.finalproject.CIS3368.models.Corona;
import com.finalproject.CIS3368.models.CoronaRepo;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class MainController {

    @Autowired
    CoronaRepo coronaRepo;

// for login we were using built in login that comes with spring booth these are the dependencies
// that needs to be added in order to see a working login page the only reason it was taken out
// was that spring boot started giving error when the post method was used through the click of
// a button to push the api call in the database.

            /*<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>*/


    // we are displaying the database table content from here
    @RequestMapping ("/")
    public ModelAndView doHome() {
        //calling index.jsp for html page
        ModelAndView mv = new ModelAndView("index");
        //calling information from Events table
        mv.addObject("covidlist", coronaRepo.findAll());



        return mv;
    }


    //we are calling the information from API through the get method and converting it in to a String.
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get (@RequestParam("id1") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        String covid = getCovidinfo(id);
        try{
            JSONObject json = new JSONObject(covid);


            mv.addObject("Country_text", json.getString("Country_text"));
            mv.addObject("Total Cases_text", json.getString("Total Cases_text"));
            mv.addObject("Active Cases_text", json.getString("Active Cases_text"));
            mv.addObject("Total Recovered_text", json.getString("Total Recovered_text"));
            mv.addObject("Total Deaths_text", json.getString("Total Deaths_text"));
            mv.addObject("Last Update", json.getString("Last Update"));
            //mv.addObject("today_confirmed", json.getJSONObject("total").get("today_confirmed").toString());


        } catch (Exception e) {
            System.out.println(e.toString());


        }
        return mv;
    }

    private String getCovidinfo(String id){
        try {

            URL urlForGetRequest = new URL("https://covid-19.dataflowkit.com/v1/" + id );

            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            } else {
                return "Unexpected HTTP response";
            }
        } catch (Exception e){
            return "Exception: " + e.getMessage();
        }
    }


    // we are pushing the displayed API information in the table we created in workbench which is connected to the AWS database.
    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id7") String id7, @RequestParam("id2") String id2, @RequestParam("id3") String id3, @RequestParam("id4") String id4, @RequestParam("id5") String id5,@RequestParam("id6") String id6)
    {
        ModelAndView mv = new ModelAndView("redirect:/");
        Corona coronaTosave;
        coronaTosave = new Corona();
        coronaTosave.setId(UUID.randomUUID().toString());
        coronaTosave.setCountry(id7);
        coronaRepo.save(coronaTosave);
        coronaTosave.setTcase(id2);
        coronaRepo.save(coronaTosave);
        coronaTosave.setAcase(id3);
        coronaRepo.save(coronaTosave);
        coronaTosave.setRcase(id4);
        coronaRepo.save(coronaTosave);
        coronaTosave.setDeath(id5);
        coronaRepo.save(coronaTosave);
        coronaTosave.setDate(id6);
        coronaRepo.save(coronaTosave);
        mv.addObject("covidlist", coronaRepo.findAll());
        return mv;
    }

    //information from text box are being pushed in the table we created in workbench which is connected to the AWS database, and we are giving UUid to each row in the table.
    @RequestMapping(value = "/edit/", method = RequestMethod.POST)
    public ModelAndView save2(@RequestParam("Country") String Country, @RequestParam("total") String total, @RequestParam("active") String active, @RequestParam("reco") String reco, @RequestParam("death") String death,@RequestParam("date") String date)
    {
        ModelAndView mv = new ModelAndView("redirect:/");
        Corona coronaTosave;
        coronaTosave = new Corona();
        coronaTosave.setId(UUID.randomUUID().toString());
        coronaTosave.setCountry(Country);
        coronaRepo.save(coronaTosave);
        coronaTosave.setTcase(total);
        coronaRepo.save(coronaTosave);
        coronaTosave.setAcase(active);
        coronaRepo.save(coronaTosave);
        coronaTosave.setRcase(reco);
        coronaRepo.save(coronaTosave);
        coronaTosave.setDeath(death);
        coronaRepo.save(coronaTosave);
        coronaTosave.setDate(date);
        coronaRepo.save(coronaTosave);
        mv.addObject("covidlist", coronaRepo.findAll());
        return mv;
    }


    //through this table rows can be deleted, and we are calling the UUid so it selects the exact row from the table.
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete1(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");

        coronaRepo.deleteById(id);
        return mv;
    }







}
