/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milospaunovic.sedam.pages.services;

import com.milospaunovic.sedam.entities.Soba;
import com.milospaunovic.sedam.services.SobaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Paun
 */
public class FindSoba {
    @Inject
    private Request request;
    @Property
    private List<Soba> listaSoba;
    @Property
    private Soba soba;
    @Inject
    private SobaDao sobaDao;
    
    Object onActivate(@RequestParameter("ime") String ime){
        if(listaSoba == null){
            listaSoba = new ArrayList<Soba>();
        }
        String response = "<table class=\"navigation\"> <th>" + " ImeSobe\n" + " </th>\n" + " ";
        listaSoba = sobaDao.getListaSobaPoImenu(ime);
        for (Soba s : listaSoba){
            response += ("<tr>\n" + " <td>" + s.getImesobe() + "</td>\n" + " </tr>");
        }
        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }
}
