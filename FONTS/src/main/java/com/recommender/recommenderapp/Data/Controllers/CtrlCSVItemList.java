package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Data.Utils.Datasets;
import com.recommender.recommenderapp.Data.Utils.ItemsCSVUtils;
import com.recommender.recommenderapp.Data.Utils.StaticFiles;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVItemList;
import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.*;

/**
 * @author Alex
 *
 */
public class CtrlCSVItemList implements ICtrlCSVItemList {


    /**
     *
     * @param dataset
     * @return Map -> (key, ItemId) value (item itslef)
     */

    public Map<String, Item> getItemList(Datasets dataset) {
        StaticFiles staticFiles = new StaticFiles();
        return staticFiles.getItems(dataset.name());

    }

}