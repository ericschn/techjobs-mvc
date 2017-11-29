package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private ArrayList<HashMap<String, String>> jobList;

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        // model.addAttribute("jobs", jobList);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "/results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        // Selecting search type

        if (searchType.equals("all")) {
            jobList = JobData.findByValue(searchTerm);
        } else {
            jobList = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // Count results

        int numJobs = 0;
        if (!jobList.isEmpty()) {
            numJobs = jobList.size();
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobList);
        model.addAttribute("numJobs", numJobs);
        return "search";
    }

}
