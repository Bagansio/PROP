package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 *
 * Its a Singleton
 */
public class CtrlItems {
    private String dataset;
    private final CtrlDataFactory  ctrlDataFactory;
    private Map<String,Item> items;
    private static CtrlItems _instance = new CtrlItems();

    private CtrlItems(){
        ctrlDataFactory = CtrlDataFactory.getInstance();
    }

    public static CtrlItems getInstance(){
        return _instance;
    }

    public void setItems(Map<String,Item> items){
        this.items = items;
    }

    public void setDataset(String dataset) {
        this.dataset = ctrlDataFactory.getICtrlData().tempDatasetToNormal(dataset);
        items = null;
    }

    public String[] getDatasets(){
        return ctrlDataFactory.getICtrlData().getDatasets();
    }

    public Map<String,Item> getItems(){
        if(items == null){
            loadItems();
        }
        return items;
    }

    public boolean loadItems(){
        ICtrlData loader = ctrlDataFactory.getICtrlData();
        items = loader.loadItems(dataset);
        return items != null;
    }

    public String[][] searchItems(String itemId, User[] currentUser){
        List<String[]> search = new ArrayList<>();
        for(Item item : items.values()){
            boolean userHas = currentUser[0].getItem(item.getId()) != null || currentUser[1].getItem(item.getId()) != null;
            if(! userHas && (item.getId().contains(itemId)|| item.getTitle().contains(itemId))){
                List<String> itemData = new ArrayList<>();
                itemData.add(item.getId());
                itemData.add(item.getTitle());
                for(String attribute : item.getDoubleAttributes().keySet()){
                    itemData.add(attribute+"-"+item.getDoubleAttributes().get(attribute));
                }
                for(String attribute : item.getStringAttributes().keySet()){
                    itemData.add(attribute+"-"+item.getStringAttributes().get(attribute));
                }
                for(String attribute : item.getIntAttributes().keySet()){
                    itemData.add(attribute+"-"+item.getIntAttributes().get(attribute));
                }
                for(String attribute : item.getSetAttributes().keySet()){
                    String attributeData = attribute + "-";
                    for(String data : item.getSetAttributes().get(attribute)){
                        attributeData += data + ";";
                    }
                    itemData.add(attributeData);
                }
                search.add(itemData.toArray(new String[0]));
            }
        }
        return search.toArray(new String[0][]);
    }
}
