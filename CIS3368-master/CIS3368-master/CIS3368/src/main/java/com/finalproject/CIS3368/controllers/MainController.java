package com.finalproject.CIS3368.controllers;

import com.finalproject.CIS3368.models.Corona;
import com.finalproject.CIS3368.models.CoronaRepo;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    CoronaRepo coronaRepo;
    @RequestMapping( value = "/chart", method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("chart");

        CanvasjsChartData chartDataObject = new CanvasjsChartData();
        List<List<Map<Object, Object>>> canvasjsDataList = chartDataObject.getCanvasjsDataList();
        mv.addObject("dataPointsList", canvasjsDataList);


        ParseAnotherJSONFile();

        return mv;
    }



    private void ParseAnotherJSONFile()
    {
        //data from https://opendata.hawaii.gov/dataset/hawaii-ev-charging-stations-02072013/resource/02eea87e-10f5-4f03-a808-d7b59f98c093
        String url = "https://og-production-open-data-shared-ckan-892364687672.s3.amazonaws.com/hawaii/resources/02eea87e-10f5-4f03-a808-d7b59f98c093/hawaii-ev-charging-stations-02072013-json.json?Signature=bLluBZtSppum7TCqsUSNvBlfPc4%3D&Expires=1605929258&AWSAccessKeyId=AKIAJJIENTAPKHZMIPXQ";

        try
        {
            HttpResponse<String> response = (HttpResponse<String>) Unirest.get(url).asString();
            String json = ((com.mashape.unirest.http.HttpResponse<?>) response).getBody().toString();
            JSONObject jsonObj = new JSONObject(json);

            JSONObject meta = jsonObj.getJSONObject("meta");
            JSONObject view = meta.getJSONObject("view");
            String name = view.getString("name");
            JSONArray approvals = view.getJSONArray("approvals");

            int numberOfApprovals = approvals.length();

            JSONObject firstOne = approvals.getJSONObject(0);
            String state = firstOne.getString("state");

            JSONObject submitter = firstOne.getJSONObject("submitter");
            String submitterName = submitter.getString("displayName");
        }
        catch (Exception ex)
        {

        }

    }


    @RequestMapping ("/")
    public ModelAndView doHome() {
        //calling index.jsp for html page
        ModelAndView mv = new ModelAndView("index");
        //calling information from Events table
        mv.addObject("covidlist", coronaRepo.findAll());



        return mv;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get (@RequestParam("id1") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        String covid = getCovidinfo(id);
        try{
            JSONObject json = new JSONObject(covid);

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
            //String apiKey = "cfc6ae8e";
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

    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id2") String id2, @RequestParam("id3") String id3, @RequestParam("id4") String id4, @RequestParam("id5") String id5,@RequestParam("id6") String id6)
    {
        ModelAndView mv = new ModelAndView("redirect:/");
        Corona coronaTosave;
        coronaTosave = new Corona();
        coronaTosave.setId(UUID.randomUUID().toString());
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

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete1(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");

        coronaRepo.deleteById(id);
        return mv;
    }




}
