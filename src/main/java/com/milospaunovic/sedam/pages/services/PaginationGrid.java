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
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Paun
 */
public class PaginationGrid {
    @Inject
    private SobaDao sobaDao;
    private int size = 20;
    
    Object onActivate(@RequestParameter("page") int page){
        Class<?> act = null;
        int sizeOfAll = sobaDao.allActiveSizeSoba();
        
        List<Soba> lista = new ArrayList<Soba>();
        
        String response = "<table class=\"navigation\"> <th>\n" + " imeSobe\n" + " </th>\n" + 
                "<th>\n" + "        sprat\n" + "</th>\n" + 
                "<th>\n" + "        internet\n" + "</th>\n" +
                "<th>\n" + "        Djakuzi\n" + "</th>\n" + 
                "<th>\n" + "        TV\n" + "</th>\n"
                + " ";
        lista = sobaDao.loadActiveFromTo(page);
        
        for(Soba s : lista){
            response += (" <tr>\n" + " <td>" + s.getImesobe() + "</td>\n" + 
                    " <td>" + s.getSprat() + "</td>\n" 
                    + " <td>" + s.getInternet() + "</td>\n" 
                    + " <td>" + s.getDjakuzi() + "</td>\n" 
                    + " <td>" + s.getTv() + "</td>\n" 
                    + "</tr>");
            
        }
        response += "</table>";
        float ceil = (float) sizeOfAll / (float) 20;
        int totalPageSizes = (int) Math.ceil(ceil);
        response += "<ul class=\"pagination\">";
        for(int i = 1; i<=totalPageSizes; i++){
            if(page == i){
                response +=("<li class=\"callservice active\"><a href=\"#\">" + i + "</a></li>\n");
                
            }else{
                response += ("<li class=\"callservice\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }
        response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
    
}
